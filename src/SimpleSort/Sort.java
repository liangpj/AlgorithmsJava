package SimpleSort;

import java.util.*;
import static java.lang.System.out;

public class Sort {
    private static  <T> void swap(T [] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 冒泡排序算法
     * @param arr 要排序的算
     * @param <T> 泛型参数，但该类需要继承Comparable
     */
    public static <T extends Comparable> void bubbleSort(T [] arr) {
        int N = arr.length;
        boolean flag = false;

        for (int i = 0; i < arr.length; ++i) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                    flag = true;
                }

            }
            // 如果没有进行交换，说明已经排好序
            if (!flag) break;
        }
    }

    /**
     * 冒泡排序算法
     * @param arr 要排序的数组
     * @param comparator 类比较器
     * @param <T> 泛型参数
     */
    public static <T> void bubbleSort(T [] arr, Comparator comparator) {
        int N = arr.length;
        boolean flag = false;

        for (int i = 0; i < arr.length; ++i) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (comparator.compare(arr[j], arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                    flag = true;
                }

            }
            // 如果没有进行交换，说明已经排好序
            if (!flag) break;
        }
    }

    /**
     * 插入排序
     * @param arr 要排序的数组
     */
    public static <T extends  Comparable> void insertSort(T [] arr) {
        for (int i = 1; i < arr.length; ++i) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j+1] = arr[j];
                --j;
            }
            arr[j+1] = key;
        }
    }

    /**
     * 插入排序
     * @param arr 要排序的数组
     * @param comparator 比较器
     * @param <T> 泛型参数
     */
    public static <T> void insertSort(T [] arr, Comparator comparator) {
        for (int i = 1; i < arr.length; ++i) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j+1] = arr[j];
                --j;
            }
            arr[j+1] = key;
        }
    }

    /**
     * 希尔排序
     * @param arr 要排序的数组
     */
    public static <T extends Comparable> void shellSort(T [] arr) {

    }

    public static int time(String alg) {

        Double [] arr = new Double[100000];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = Math.random();

        long callTime = System.currentTimeMillis();
        if (alg.equals("bubbleSort")) bubbleSort(arr);
        if (alg.equals("insertSort")) insertSort(arr);
        callTime = System.currentTimeMillis() - callTime;
        return (int)callTime/1000;
    }

    public static void main(String [] args) {
        Integer [] arr = new Integer[10];
        Random random = new Random(123);
        for (int i = 0; i < arr.length; ++i)
            arr[i] = random.nextInt(100);

        insertSort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a);
            }
        });
        out.println(Arrays.toString(arr));
    }
}
