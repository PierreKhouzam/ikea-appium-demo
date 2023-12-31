package com.appiumdemo.utils;


import com.appiumdemo.engine.BaseDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.Duration;
import java.util.Base64;


public class VideoRecords {

    public static void startRecording() {
        try {
            BaseDriver.getCurrentDriver().startRecordingScreen(new AndroidStartScreenRecordingOptions().withVideoSize("1280x720").withTimeLimit(Duration.ofSeconds(200)));
            Logs.info("Started recording");
        } catch (Exception e) {
            Logs.error("Failed to start recording" + e.getMessage());
        }
    }

    public static void stopAndSaveRecording(String fileName) {
        try {
            String video = BaseDriver.getCurrentDriver().stopRecordingScreen();
            byte[] decode = Base64.getDecoder().decode(video);
            FileUtils.writeByteArrayToFile(new File(Constants.videoRecordsPath, fileName + ".mp4"), decode);
            Logs.info("Stopped recording and clip is downloaded");
        } catch (Exception e) {
            Logs.error("Failed to stop/download recording" + e.getMessage());
        }
    }
}

