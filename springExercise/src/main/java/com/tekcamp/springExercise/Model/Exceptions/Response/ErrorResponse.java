package com.tekcamp.springExercise.Model.Exceptions.Response;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement(name = "error")
public class ErrorResponse {
    //Build out object to return fields
    private Timestamp timestamp;
    private int status;
    private String errReason;
    private String errMessage;
    private String path;

    public ErrorResponse(Timestamp timestamp, int status, String errReason, String errMessage, String path) {
        this.errReason = errReason;
        this.timestamp = timestamp;
        this.status = status;
        this.errMessage = errMessage;
        this.path = path;
    }

    @Override
    public String toString(){
        return "Time:"+ timestamp + " Status Code:" + status + " " + errReason + " Message: " + errMessage + " at " + path;
    }
}
