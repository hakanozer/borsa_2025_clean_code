package com.works.models;

import lombok.Data;

@Data
public class Result {

    private boolean success;
    private String message;
    private Object result;

}
