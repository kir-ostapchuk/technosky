package com.ostapchuk.technosky.entity;

import java.util.Arrays;

public enum Currency {
    USD;

    public static boolean hasCurrency(String currency) {
        return Arrays.stream(values())
              .map(Currency::name)
              .anyMatch(c -> c.equals(currency));
    }
}
