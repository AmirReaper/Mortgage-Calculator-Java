package com.example;

import java.util.List;

/**
 * Utility class for demonstrating generics.
 */
public final class GenericUtils {
    private GenericUtils() { }

    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("❌List cannot be null or empty❌");

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0)
                max = item;
        }
        return max;
    }

    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static double totalPaymentsWildcard(List<? extends Mortgage> mortgages) {
        double total = 0;
        for (Mortgage m : mortgages) {
            total += m.calculateMonthlyPayment();
        }
        return total;
    }
}