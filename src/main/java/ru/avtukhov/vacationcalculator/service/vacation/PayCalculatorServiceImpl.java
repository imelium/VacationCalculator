package ru.avtukhov.vacationcalculator.service.vacation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avtukhov.vacationcalculator.dto.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PayCalculatorServiceImpl implements PayCalculatorService {

    private static final double AVERAGE_WORKING_DAYS = 29.3; // The average number of working days per month.
    private static final double NDFL = 0.13;


    @Override
    public Response getPayCalculation(BigDecimal averageSalaryPerYear, int vacationDays) {
        Objects.requireNonNull(averageSalaryPerYear, "averageSalaryPerYear cannot be null");

        BigDecimal averageEarningsPerDay = averageSalaryPerYear
                .divide(BigDecimal.valueOf(AVERAGE_WORKING_DAYS), 2, RoundingMode.HALF_EVEN);

        BigDecimal totalPayWithoutNDFL = averageEarningsPerDay.multiply(BigDecimal.valueOf(vacationDays));

        BigDecimal amountNDFL = totalPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL))
                .setScale(0, RoundingMode.HALF_UP);

        BigDecimal totalPay = totalPayWithoutNDFL.subtract(amountNDFL);

        return new Response("Сумма отпускных в рублях (с вычетом НДФЛ)", totalPay);
    }
}
