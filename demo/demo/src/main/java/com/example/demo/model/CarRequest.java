package com.example.demo.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest implements Serializable {

    @NotNull
    private String title;
    @NotNull
    private String brand;
    @NotNull
    private String price;
    @NotNull
    private Integer age;

}
