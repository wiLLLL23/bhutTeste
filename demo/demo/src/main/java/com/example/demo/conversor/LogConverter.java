package com.example.demo.conversor;

import com.example.demo.entity.LogEntity;
import com.example.demo.model.LogResponse;

public class LogConverter implements ConverterUtil<LogEntity, LogResponse> {

    @Override
    public LogResponse convert(LogEntity a) {
        return LogResponse.builder()
                .id(a.getId())
                .car_id(a.getCar().get_id())
                .data_hora(a.getDataHora())
                .build();
    }
}
