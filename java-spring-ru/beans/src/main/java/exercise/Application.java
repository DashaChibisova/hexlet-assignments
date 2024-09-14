package exercise;

import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

// BEGIN
@Configuration
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @Scope("prototype")
    public Daytime getDay() {
        var time = LocalDateTime.now().getHour();
        if (time >= 6 && time < 22) {
            return new Day();
        } else return new Night();
    }
    // END
}
