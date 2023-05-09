package com.project.mini.peer2peertransaction.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponse<result> {
    private String message;
    private result data;

    public result getData() {
        return data;
    }
}
