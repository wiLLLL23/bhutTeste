package com.example.demo.config;

import com.example.demo.conversor.ConverterUtil;
import com.example.demo.conversor.LogConverter;
import com.example.demo.entity.CarEntity;
import com.example.demo.model.CarRequest;
import com.example.demo.model.CarResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConverterConfig {

    @Primary
    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new ConverterUtil<CarEntity, CarResponse>() {
        });
        service.addConverter(new ConverterUtil<CarResponse, CarEntity>() {
        });
        service.addConverter(new ConverterUtil<CarRequest, CarEntity>() {
        });
        service.addConverter(new LogConverter());
        return service;
    }
}
