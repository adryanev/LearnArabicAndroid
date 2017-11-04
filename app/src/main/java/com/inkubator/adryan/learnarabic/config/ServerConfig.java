package com.inkubator.adryan.learnarabic.config;

/**
 * Created by adryanev on 04/10/17.
 */

public class ServerConfig {

    private static String server = "http://192.168.8.102/learn-arabic";
    public static final String WEB_SERVER = server+"/web/";
    public static final String API_ENDPOINT = server+"/api/v1/";
    public static final String IMAGE_FOLDER = WEB_SERVER+"uploads/images/";
    public static final String SUARA_FOLDER = WEB_SERVER+"uploads/suara/";
    public static final String YOUTUBE_API = "AIzaSyANa76xjvX_6SlWtjtBmq27qR1v3h4j37I";
}
