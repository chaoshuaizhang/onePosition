package cn.shopin.oneposition.test;

/**
 * Created by zcs on 2017/5/4.
 */

public class Testttt {
    public static void main(String[] args) {
        String[] arr1 = {"a", "b", "c"};
        String[] arr2 = {"c", "b", "a"};
        String[] arr3 = new String[6];
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i];
        }
        int j = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int i1 = 0; i1 < arr2.length; i1++) {
                if (arr1[i1].equals(arr2[i])) {

                }
                if (!arr1[i1].equals(arr2[i]) && i1 == arr2.length - 1) {//不等 并且是 最后一个
                    arr3[arr1.length + j] = arr2[i];
                    j++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(arr3[i]);
        }
    }
}
