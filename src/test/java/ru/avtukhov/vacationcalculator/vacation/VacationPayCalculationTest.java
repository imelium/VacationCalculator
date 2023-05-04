package ru.avtukhov.vacationcalculator.vacation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.avtukhov.vacationcalculator.VacationCalculatorApplicationTests;
import ru.avtukhov.vacationcalculator.service.vacation.PayCalculatorServiceImpl;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class VacationPayCalculationTest extends VacationCalculatorApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @InjectMocks
    private PayCalculatorServiceImpl payCalculatorService;

    @Test
    void simpleQueryTest() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/calculate")
                .param("averageSalary", String.valueOf(30500.00))
                .param("vacationDays", String.valueOf(30))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payout").value(BigDecimal.valueOf(27168.80).stripTrailingZeros()))
                .andReturn();

    }

    @Test
    void queryWithDateTest() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/calculate")
                .param("averageSalary", String.valueOf(30500.00))
                .param("vacationDays", String.valueOf(30))
                .param("startVacationDate","2023-05-01")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payout").value(BigDecimal.valueOf(25357.88).stripTrailingZeros()))
                .andReturn();

    }
    
}