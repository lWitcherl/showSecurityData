package com.antonsikora;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ParserImp extends Thread {
    private String part;
    private Document doc;
    private ReadableByteChannel readableByteChannel;
    private FileOutputStream fileOutputStream;
    private DataEntity dataEntity;

    ParserImp(String part){
        this.part=part;
    }

    private void getWeb(String src ){  // method parsing web page
        if(src==null){
            dataEntity.workFlag=false;
            return;
        }
        try {
            doc= Jsoup.connect(src).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements el = doc.getElementsByClass("screenshot-image") ;// getting url page
        String href = el.toString().split( "\" " )[1].substring(5) ;
        System.out.println(href);


        try {
            downloadUsingNIO(href, part+"\\files\\"+dataEntity.getProgress()+".png");
        } catch (IOException e) {
            System.out.println(e);
            dataEntity.setException();
        }

    }
    public void run(){
        dataEntity = DataEntity.getDataEntity();
        while (DataEntity.workFlag){
            getWeb(dataEntity.getURL());
        }
    }

    private void downloadUsingNIO(String urlStr, String file) throws IOException {//method for saved img
        readableByteChannel = Channels.newChannel(new URL(urlStr).openStream());
        fileOutputStream = new FileOutputStream(file);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
        readableByteChannel.close();
    }

}