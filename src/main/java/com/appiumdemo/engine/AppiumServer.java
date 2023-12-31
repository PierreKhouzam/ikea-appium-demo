package com.appiumdemo.engine;

import com.appiumdemo.utils.Constants;
import com.appiumdemo.utils.Logs;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServer {
    static AppiumDriverLocalService server;

    private static void setInstance() {

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .usingPort(getAvailablePort())
                .withLogFile(new File(Constants.appiumLogsPath))
                .withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait,gestures");
        server = AppiumDriverLocalService.buildService(builder);
    }

    private static AppiumDriverLocalService getInstance() {
        if (server == null) {
            setInstance();
        }
        return server;
    }

    public static void start() {
        getInstance().start();
        Logs.info("Appium server started on " + server.getUrl());
    }

    public static void stop() {
        if (server != null) {
            getInstance().stop();
            Logs.info("Appium server stopped");
        }
    }

    //Method to get available port to use later
    public static int getAvailablePort() {
        int port = 4723;

        try {
            ServerSocket serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
            serverSocket.close();
        } catch (IOException e) {
            Logs.error("Error while getting an available port: " + e.getMessage());
        }
        return port;
    }
}
