package ru.avtukhov.vacationcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Response {

    private String message;
    private BigDecimal payout;

}
