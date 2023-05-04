package ru.avtukhov.vacationcalculator.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.avtukhov.vacationcalculator.dto.Response;
import ru.avtukhov.vacationcalculator.service.days.DaysCalculationServiceImpl;
import ru.avtukhov.vacationcalculator.service.vacation.PayCalculatorServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final PayCalculatorServiceImpl payCalculatorServiceImpl;
    private final DaysCalculationServiceImpl daysCalculationServiceImpl;

    @GetMapping("/calculate")
    public Response getVacationPay(
            @RequestParam("averageSalary") @Valid @PositiveOrZero BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") @Valid @Positive int vacationDays,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate
    ) {
        if (startVacationDate.isPresent()) {
            vacationDays = daysCalculationServiceImpl.calculateDays(vacationDays, startVacationDate.get());
        }
        return payCalculatorServiceImpl.getPayCalculation(averageSalaryPerYear, vacationDays);
    }
}
