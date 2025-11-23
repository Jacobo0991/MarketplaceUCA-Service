package com.marketplace.backend.utils;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Component
public class PrettyUtils {

    private final PrettyTime prettyTime = new PrettyTime(new Locale("es"));

    public String generateTimeAgo(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return prettyTime.format(date);
    }
}
