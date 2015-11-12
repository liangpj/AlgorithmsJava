package Sort;


import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

/**
 * 堆排序算法类
 */
public class HeapSort {

    /**
     * 交换arr[i]和arr[j]的值
     */
    private static void swap(Comparable [] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 维护最大堆的性质
     * @param arr 数组堆
     * @param parent 以下标为parent的堆
     * @param size 堆的大小
     */
    private static  void buildHeapHelp(Comparable [] arr, int parent, int size) {
        int lch = parent*2 + 1; //左子节点index
        int rch = lch + 1; //右子节点index
        int largest = parent;    // 最大节点的index
        if (lch <= size && arr[parent].compareTo(arr[lch]) < 0)
            largest = lch;
        if (rch <= size && arr[largest].compareTo(arr[rch]) < 0)
            largest = rch;
        if (largest != parent) {
            swap(arr, parent, largest);
            buildHeapHelp(arr, largest, size);
        }
    }

    /**
     * 将数组构建成一个最大堆
     * @param arr 要构建的数组
     */
    private static void buildHeap(Comparable [] arr, int lastIndex) {

        for (int parent = (lastIndex-1)/2; parent >= 0; --parent) {
            buildHeapHelp(arr, parent, lastIndex);
        }
    }

    public static void sort(Comparable [] arr) {

        for (int i = 0; i < arr.length-1; ++i) {
            buildHeap(arr, arr.length-1-i);
            swap(arr, 0, arr.length-1-i);
        }
    }

    public static void main(String [] args) {
        Integer [] arr = new Integer[100000];// {3,5,4,2,7,9,8,1,6};
        ///buildHeap(arr, arr.length-1);
        Random random = new Random();
        for(int i = 0; i < arr.length; ++i)
            arr[i] = random.nextInt();
        long callTime = System.currentTimeMillis();
        sort(arr);
        callTime = System.currentTimeMillis() - callTime;
        System.out.println("times : " + callTime/1000 + "s");
    }
}
