package com.example.fetchexchangerate.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ForexResponse {
    private String date;
    private Double usd;

    public ForexResponse(String date, Double usd) {
        this.date = date;
        this.usd = usd;
    }
}
