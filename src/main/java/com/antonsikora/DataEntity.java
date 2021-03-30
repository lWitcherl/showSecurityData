package com.antonsikora;

import java.io.IOException;
import java.util.ArrayDeque;

public class DataEntity {
    /** entity changing information for parser-threads**/
    private ArrayDeque<String> dequeURL;
    private static DataEntity dataEntity;
    private int  progress =0;
    public static boolean workFlag = true;
    private boolean exception =false;

    private DataEntity(ArrayDeque<String> dequeURL) {
        this.dequeURL = dequeURL;
    }

    public DataEntity() {
    }
    synchronized public void setException(){
        exception = true;
    }
    public boolean getException(){
        return exception;
    }
    synchronized public int getProgress() {
        return progress++;
    }
    public int getProgressForBar() { return progress; }

    synchronized public String getURL() {
        return dequeURL.poll();
    }

    public static DataEntity getDataEntity() {
        return dataEntity;
    }

    public static void setDataEntity(ArrayDeque<String> dequeURL) {
        DataEntity.dataEntity = new DataEntity( dequeURL);
    }
}
