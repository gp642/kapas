package com.example.kapas.util;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private Utility() {

    }

    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
