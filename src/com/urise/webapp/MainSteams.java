package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainSteams {
    public static void main(String[] args) {
        System.out.println("Now minValue");
        int[] mas1 = new int[]{1, 2, 3, 3, 2, 3};
        int[] mas2 = new int[]{9, 8};
        int[] mas3 = new int[10];

        printArray(mas1);
        printArray(mas2);
        printArray(mas3);
        System.out.println();

        System.out.println("Now oddOrEven");
        List<Integer> arr1 = getArraylist(1, 2, 3, 3, 2, 3);
        List<Integer> arr2 = getArraylist(9, 8);
        List<Integer> arr3 = getArraylist(5, 2, 3, 8, 3);
        List<Integer> arr4 = getArraylist();

        arr1 = oddOrEven(arr1);
        arr2 = oddOrEven(arr2);
        arr3 = oddOrEven(arr3);
        arr4 = oddOrEven(arr4);

        printList(arr1);
        printList(arr2);
        printList(arr3);
        printList(arr4);
    }
   
    interface Filter<T> {
        Predicate<? super java.lang.Integer> filtration();
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> (a * 10) + b);
    }

    private static void printArray(int[] values) {
        System.out.println(minValue(values));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Integer sum = integers.stream()
                .parallel()
                .reduce(0, Integer::sum);
        return integers.stream()
                .parallel()
                .filter(sum % 2 == 1 ? x -> x % 2 == 0 : x -> x % 2 == 1)
                .collect(Collectors.toList());
    }

    private static List<Integer> getArraylist(Integer... integers) {
        return Arrays.asList(integers);
    }

    private static void printList(List<Integer> integers) {
        for (Integer r : integers) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}

