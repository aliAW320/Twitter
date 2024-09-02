package com.twiter.Twiter12.Utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class  UniqueGenerator {
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());
    public static synchronized long generateUniqueID() {
        return counter.getAndIncrement();
    }
    public static synchronized String getLoginToken() {
        return UUID.randomUUID().toString().replace("-", "") + String.valueOf(generateUniqueID());
    }

}