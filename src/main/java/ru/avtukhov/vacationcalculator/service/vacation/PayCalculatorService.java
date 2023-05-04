package ru.avtukhov.vacationcalculator.service.vacation;


import ru.avtukhov.vacationcalculator.dto.Response;

import java.math.BigDecimal;

public interface PayCalculatorService {

    Response getPayCalculation(BigDecimal averageSalaryPerYear,
                                       int vacationDays);
}
