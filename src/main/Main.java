package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(daysBeforeBirthday());
    }

    public static int daysBeforeBirthday() {

        while (true) {
            var userDate = scanner.nextLine();

            if (userDate.equals("end")) {
                break;
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate todayDate = LocalDate.now();
            LocalDate birthdayDate;
            try {
                birthdayDate = LocalDate.parse(userDate, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Дата введена не правильно, попробуйте пожалуйста ещё раз в формате dd-MM-yyyy (01-01-1990)");
                continue;
            }

            var dayOfYear = todayDate.getDayOfYear();
            int dayOfYearBirthday;
            dayOfYearBirthday = birthdayDate.withYear(todayDate.getYear()).getDayOfYear();

            if (dayOfYear < dayOfYearBirthday) {
                return dayOfYearBirthday - todayDate.getDayOfYear();

            } else if (dayOfYear == dayOfYearBirthday) {
                return 0;

            } else {
                if (!todayDate.isLeapYear()) {
                    return 365 - dayOfYear + birthdayDate.withYear(todayDate.getYear() + 1).getDayOfYear();
                } else {
                    return 366 - dayOfYear + birthdayDate.withYear(todayDate.getYear() + 1).getDayOfYear();
                }
            }
        }

        return 0;
    }
}
