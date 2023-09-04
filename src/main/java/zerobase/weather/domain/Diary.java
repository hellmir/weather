package zerobase.weather.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String weather;
    private String icon;
    private double temperature;
    private String text;
    private LocalDate date;

    public void setDateWeather(DateWeather dateWeather){
        weather = dateWeather.getWeather();
        icon = dateWeather.getIcon();
        temperature = dateWeather.getTemperature();
        date = dateWeather.getDate();
    }

    @Builder
    private Diary(String weather, String icon, double temperature, String text, LocalDate date) {

        this.weather = weather;
        this.icon = icon;
        this.temperature = temperature;
        this.text = text;
        this.date = date;

    }

}
