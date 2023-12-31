package com.appiumdemo.utils;

import java.io.*;
import java.util.Properties;

public class Emulators {


    //Method to start emulator programmatically
    public static void startEmulator() {
        new Thread(() -> {
            try {
                FileInputStream input = new FileInputStream(Constants.emulatorsConfig);
                Properties emulatorsConfig = new Properties(System.getProperties());
                emulatorsConfig.load(input);

                String emulatorPath = emulatorsConfig.getProperty("emulator.path");
                String emulatorName = emulatorsConfig.getProperty("emulator.name");

                if (emulatorPath != null && emulatorName != null) {
                    ProcessBuilder pb = new ProcessBuilder(emulatorPath, "-avd", emulatorName);
                    pb.inheritIO(); // Redirect output to the console
                    pb.start();
                } else {
                    Logs.error("Emulator properties not provided in the config file!");
                }
            } catch (IOException e) {
                Logs.error("Error starting emulator " + e.getMessage());
            }
        }).start();
    }

    //Method to detect if any emulator is currently opened (adb devices)
    public static String detectRunningEmulator() {
        try {
            ProcessBuilder pb = new ProcessBuilder("adb", "devices");
            Process pr1 = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(pr1.getInputStream()));
            String line;
            String result = "";
            while ((line = in.readLine()) != null) {
                if (line.equals("")) continue; // Skip empty lines
                if (!line.contains("List of devices attached")) {
                    result = line.split("\\s+")[0]; // Extracting the emulator/device ID
                    break;
                }
            }
            return result;
        } catch (IOException e) {
            Logs.error("Error while detecting adb devices " + e.getMessage());
            return ""; // Return an empty string in case of error
        }
    }

    public static String startOrReturnEmulator() throws InterruptedException {
        String runningEmulator = detectRunningEmulator();
        if (runningEmulator.equals("")) {
            startEmulator(); // Run the emulator if not running
            Thread.sleep(10000);
            return detectRunningEmulator();
        } else {
            return runningEmulator;
        }
    }

    public static void stopEmulator() {
        try {
            String runningEmulator = detectRunningEmulator();
            if (!runningEmulator.equals("")) {
                ProcessBuilder pb = new ProcessBuilder("adb", "-s", runningEmulator, "emu", "kill");
                Process process = pb.start();
                int exitCode = process.waitFor(); // Wait for the process to finish

                if (exitCode == 0) {
                    Logs.info("Emulator stopped " + runningEmulator);
                } else {
                    Logs.error("Unable to stop the emulator " + runningEmulator);
                }
            } else {
                Logs.error("Emulator ID is empty");
            }
        } catch (IOException | InterruptedException e) {
            Logs.error("Error while stopping the emulator " + e.getMessage());
        }
    }
}
