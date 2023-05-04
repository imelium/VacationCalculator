package ru.avtukhov.vacationcalculator.service.days;

import java.time.LocalDate;

public interface DaysCalculationService {

    int calculateDays(int vacationDays, LocalDate startVacation);

}
