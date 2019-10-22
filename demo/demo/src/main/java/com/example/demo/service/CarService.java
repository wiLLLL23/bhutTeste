package com.example.demo.service;

import com.example.demo.component.RemoteWebservice;
import com.example.demo.entity.CarEntity;
import com.example.demo.entity.LogEntity;
import com.example.demo.model.CarRequest;
import com.example.demo.model.CarResponse;
import com.example.demo.model.LogResponse;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.LogRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private ConversionService conversor;
    @Autowired
    private RemoteWebservice remoteWebservice;

    public List<CarResponse> listCars() {
        List<CarResponse> responses = Optional
                .ofNullable(remoteWebservice.getCars().getBody())
                .orElse(Collections.emptyList());
        List<CarEntity> listEntity = responses.parallelStream()
                .map(c -> conversor.convert(c, CarEntity.class))
                .collect(toList());
        carRepository.saveAll(listEntity);
        return responses;
    }

    @Transactional
    public CarResponse createCar(CarRequest carRequest) {
        CarResponse carResponse = remoteWebservice.createCar(carRequest).getBody();
        CarEntity carEntity = conversor.convert(carResponse, CarEntity.class);
        carRepository.saveAndFlush(carEntity);
        logRepository.save(LogEntity.builder()
                .car(carEntity)
                .dataHora(LocalDateTime.now())
                .build());
        return conversor.convert(carEntity, CarResponse.class);
    }

    public List<LogResponse> logs() {
        List<LogEntity> logs = logRepository.findAll();

        return logs.parallelStream()
                .map(l -> conversor.convert(l, LogResponse.class))
                .collect(toList());
    }
}
