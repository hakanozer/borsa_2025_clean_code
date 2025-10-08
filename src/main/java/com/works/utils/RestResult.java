package com.works.utils;

import com.works.models.Result;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class RestResult {

    public static ResponseEntity<Result> success(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Success");
        result.setResult(obj);
        return ResponseEntity.ok(result);
    }

    public static ResponseEntity<Result> fail(Object obj, HttpStatusCode statusCode) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("Fail");
        result.setResult(obj);
        return ResponseEntity.status(statusCode).body(result);
    }

}
