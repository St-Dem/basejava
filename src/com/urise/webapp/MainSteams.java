package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class MainSteams {

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
        List<Integer> arr1 = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 2, 3));
        List<Integer> arr2 = new ArrayList<>(Arrays.asList(8, 9));
        List<Integer> arr3 = new ArrayList<>(Arrays.asList(3, 4, 7, 2, 1));
        List<Integer> arr4 = new ArrayList<>();
        List<Integer> arr5 = new ArrayList<>(Arrays.asList(3, 4, 2, 1));


        printList(arr1);
        printList(arr2);
        printList(arr3);
        printList(arr4);
        printList(arr5);
    }

    private static void printArray(int[] mass1) {
        System.out.println(minValue(mass1));
    }

    private static int minValue(int[] values) {
        OptionalInt reduce = Arrays.stream(values).sorted().distinct().reduce((a, b) -> (a * 10) + b);
        return reduce.isPresent() ? reduce.getAsInt() : 0;

    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.size() > 1) {
            return integers.stream().reduce(Integer::sum).get() % 2 == 1
                    ? integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList())
                    : integers.stream().filter(x -> x % 2 == 1).collect(Collectors.toList());
        }
        return integers;
    }

    private static void printList(List<Integer> list) {
        for (Object r : oddOrEven(list)) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}

