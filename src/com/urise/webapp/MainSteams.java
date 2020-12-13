package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainSteams {
    private static Stream<Integer> stream;

    public static void main(String[] args) {
        int[] mass1 = {1, 2, 3, 3, 2, 3};
        int[] mass2 = {9, 8};
        int[] mass3 = new int[10];

        System.out.println("Now minValue");
        printArray(mass1);
        printArray(mass2);
        printArray(mass3);
        System.out.println();

        System.out.println("Now oddOrEven");
        List<Integer> arr1 = getArraylist(1, 2, 3, 3, 2, 3);
        List<Integer> arr2 = getArraylist(8, 9);
        List<Integer> arr3 = getArraylist(3, 4, 7, 2, 1);
        List<Integer> arr4 = new ArrayList<>();
        List<Integer> arr5 = getArraylist(3, 4, 2, 1);


        printList(arr1);
        printList(arr2);
        printList(arr3);
        printList(arr4);
        printList(arr5);
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

