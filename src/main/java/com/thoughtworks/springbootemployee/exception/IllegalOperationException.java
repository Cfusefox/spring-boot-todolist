package com.thoughtworks.springbootemployee.exception;

public class IllegalOperationException extends Exception{
    private static String illegalOperationData= "could not find data";

    public static String getIllegalOperationData() {
        return illegalOperationData;
    }
}
