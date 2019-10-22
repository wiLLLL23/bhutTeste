package com.example.demo.controller;

import com.example.demo.model.CarRequest;
import com.example.demo.model.CarResponse;
import com.example.demo.model.LogResponse;
import com.example.demo.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("api")
@SwaggerDefinition(schemes = SwaggerDefinition.Scheme.HTTPS)
public class CarController {

    @Autowired
    private CarService service;

    @ApiOperation(value = "List cars", response = CarResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of cars", response = CarResponse.class)
    })
    @GetMapping("listCars")
    public ResponseEntity<List<CarResponse>> listCars() throws Exception {
        return ResponseEntity.ok(service.listCars());
    }

    @ApiOperation(value = "Create car", response = CarResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Car created", response = CarResponse.class)
    })
    @PostMapping("createCar")
    public ResponseEntity<CarResponse> createCar(@RequestBody @Valid CarRequest carRequest) throws Exception {
        return ResponseEntity.ok(service.createCar(carRequest));
    }

    @ApiOperation(value = "Logs", response = CarResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of logs", response = LogResponse.class)
    })
    @GetMapping("logs")
    public ResponseEntity<List<LogResponse>> logs() throws Exception {
        return ResponseEntity.ok(service.logs());
    }
}
