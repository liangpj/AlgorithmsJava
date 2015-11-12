package Sort;


import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

/**
 * �������㷨��
 */
public class HeapSort {

    /**
     * ����arr[i]��arr[j]��ֵ
     */
    private static void swap(Comparable [] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * ά�����ѵ�����
     * @param arr �����
     * @param parent ���±�Ϊparent�Ķ�
     * @param size �ѵĴ�С
     */
    private static  void buildHeapHelp(Comparable [] arr, int parent, int size) {
        int lch = parent*2 + 1; //���ӽڵ�index
        int rch = lch + 1; //���ӽڵ�index
        int largest = parent;    // ���ڵ��index
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
     * �����鹹����һ������
     * @param arr Ҫ����������
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
