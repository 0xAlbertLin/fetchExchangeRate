package com.example.fetchexchangerate.dto;

import lombok.Data;

@Data
public class ForexRequest {
    private String startDate;
    private String endDate;
    private String currency;
}
