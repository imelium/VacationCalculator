package ru.avtukhov.vacationcalculator.service.days;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class DaysCalculationServiceImpl implements DaysCalculationService {

    public final static int CURRENT_YEAR = LocalDate.now().getYear();

    @Override
    public int calculateDays(int vacationDays, LocalDate startVacation) {
        Set<LocalDate> holidays = new HashSet<>(getHolidays());

        List<LocalDate> paidVacationDates = Stream.iterate(startVacation, date -> date.plusDays(1))
                .limit(vacationDays)
                .filter(date -> !holidays.contains(date)).toList();

        return paidVacationDates.size();
    }


    public static List<LocalDate> getHolidays() {
        String holidaysStr = loadHolidaysFromConfig();
        return Arrays.stream(holidaysStr.split(","))
                .map(holiday -> {
                    String[] parts = holiday.split("/");
                    int month = Integer.parseInt(parts[0]);
                    int day = Integer.parseInt(parts[1]);
                    return LocalDate.of(CURRENT_YEAR, month, day);
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }


    private static String loadHolidaysFromConfig() {
        return System.getProperty("holidays", "1/1,1/2,1/3,1/4,1/5,1/6,1/7,1/8,2/23,3/8,5/1,5/9,6/12,11/4");
    }
}
