package com.mk.myspaceweb.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static LocalDateTime getCurrentUtcTime() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
    }
}
