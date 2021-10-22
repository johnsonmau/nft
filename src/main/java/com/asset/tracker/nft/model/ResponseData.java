package com.asset.tracker.nft.model;

import org.springframework.http.HttpStatus;

public class ResponseData {

    private HttpStatus status;
    private String description;
    private String result;

    public ResponseData() {
    }

    public ResponseData(HttpStatus status, String description, String result) {
        this.status = status;
        this.description = description;
        this.result = result;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
