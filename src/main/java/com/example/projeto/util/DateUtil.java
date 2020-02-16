package com.example.projeto.util;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {


    public DateUtil() {
    }

    public String formatLocalDateTimeToDataBaseStyle(LocalDateTime time){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time);
    }
}
