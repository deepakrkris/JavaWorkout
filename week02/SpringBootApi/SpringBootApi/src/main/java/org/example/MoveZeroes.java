package org.example;

public class MoveZeroes {
    public static int[] moveZeroes(int[] arr) {
        int leftMostZ = 0;

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] != 0) {
                arr[leftMostZ++] = arr[index];
            }
        }

        while (leftMostZ < arr.length) {
            arr[leftMostZ] = 0;
            leftMostZ++;
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] result = moveZeroes(new int[]{
                1, 2, 0, 4, 3, 0, 5, 0
        });
        for (int i : result)
            System.out.println(i);
    }
}
