package zerobase.weather.testutil;

import zerobase.weather.domain.Diary;

import java.time.LocalDate;

public class TestObjectFactory {

    public static Diary createDiary(String weather, String icon, double temperature, String text, LocalDate date) {
        return Diary.builder()
                .weather(weather)
                .icon(icon)
                .temperature(temperature)
                .text(text)
                .date(date)
                .build();
    }

}
