﻿# Neoflex test task
Приложение "Калькулятор отпускных".  
Микросервис на SpringBoot + Java 11 c одним API:  
GET "/calculacte"  
  
Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.  
Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных.  


Простой запрос:  
http://localhost:8081/calculate?averageSalary=120000.00&vacationDays=15  
{"message":"Сумма отпускных в рублях (с вычетом НДФЛ)","payout":53447.40}

Запрос с указанием точного дня ухода в отпуск:  
http://localhost:8081/calculate?averageSalary=120000.00&vacationDays=15&startVacationDate=2023-05-01  
{"message":"Сумма отпускных в рублях (с вычетом НДФЛ)","payout":46321.28}