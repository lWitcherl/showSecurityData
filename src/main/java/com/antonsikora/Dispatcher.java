package com.antonsikora;

import java.io.File;
import java.util.ArrayDeque;

public class Dispatcher {
    private static String startURL,partDisk = "C:\\Users\\Public\\Downloads";
    private static int numberThread = 1,character = 1;

    public static void start() {
        try {new File(partDisk+"\\files").mkdir();}catch (Exception e ){}//creating folder for output
        DataEntity.setDataEntity((ArrayDeque<String>) GenerationUrl.generationURL(startURL,character));

        for(int i = 0; i < numberThread; i++) new ParserImp(partDisk).start();
    }

    public static int getCharacter() {
        return character;
    }

    public static void setCharacter(int character) {
        Dispatcher.character = character;
    }

    public static void setStartURL(String startURL) {
        Dispatcher.startURL = startURL;
    }


    public static void setPartDisk(String partDisk) {
        Dispatcher.partDisk = partDisk;
    }


    public static void setNumberThread(int numberThread) {
        Dispatcher.numberThread = numberThread;
    }
}
