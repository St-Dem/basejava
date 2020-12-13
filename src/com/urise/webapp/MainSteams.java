package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainSteams {
    private static Stream<Integer> stream;

    public static void main(String[] args) {
        System.out.println("Now minValue");
        printArray(new int[]{1, 2, 3, 3, 2, 3});
        printArray(new int[]{9, 8});
        printArray(new int[10]);
        System.out.println();

        System.out.println("Now oddOrEven");
        printList(getArraylist(1, 2, 3, 3, 2, 3));
        printList(getArraylist(8, 9));
        printList(getArraylist(3, 4, 7, 2, 1));
        printList(new ArrayList<>());
        printList(getArraylist(3, 4, 2, 1));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> (a * 10) + b);
    }

    private static void printArray(int[] mass1) {
        System.out.println(minValue(mass1));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        stream = integers.stream()
                .parallel();

        return integers.stream()
                .parallel()
                .reduce(0, Integer::sum) % 2 == 1
                ? stream
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList())
                : stream
                .filter(x -> x % 2 == 1)
                .collect(Collectors.toList());
    }

    private static List<Integer> getArraylist(Integer... a) {
        return Arrays.asList(a);
    }

    private static void printList(List<Integer> list) {
        for (Integer r : oddOrEven(list)) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}

