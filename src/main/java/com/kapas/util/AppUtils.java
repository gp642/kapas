package com.kapas.util;

import java.util.concurrent.ThreadLocalRandom;

public class AppUtils {

    private AppUtils() {

    }

    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
