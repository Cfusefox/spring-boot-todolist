package com.thoughtworks.springbootemployee.exception;

public class NoSuchDataExceptopn extends Exception{
    private static String noSuchData= "could not find data";

    public static String getNoSuchData() {
        return noSuchData;
    }
}
