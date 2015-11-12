package Sort;

import Sort.HeapSort.*;
import java.util.*;
import static java.lang.System.out;

/**
 * ��������㷨ʵ��
 */
public class SimpleSort {
    private static  <T> void swap(T [] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * ð�������㷨
     * @param arr Ҫ���������
     */
    public static  void bubbleSort(Comparable [] arr) {
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
            // ���û�н��н�����˵���Ѿ��ź���
            if (!flag) break;
        }
    }

    /**
     * ð�������㷨
     * @param arr Ҫ���������
     * @param comparator ��Ƚ���
     * @param <T> ���Ͳ���
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
            // ���û�н��н�����˵���Ѿ��ź���
            if (!flag) break;
        }
    }

    /**
     * ��������
     * @param arr Ҫ���������
     */
    public static void insertSort(Comparable [] arr) {
        for (int i = 1; i < arr.length; ++i) {
            Comparable key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j+1] = arr[j];
                --j;
            }
            arr[j+1] = key;
        }
    }

    /**
     * ��������
     * @param arr Ҫ���������
     * @param comparator �Ƚ���
     * @param <T> ���Ͳ���
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
     * ϣ������
     * @param arr Ҫ���������
     */
    public static  void shellSort(Comparable [] arr) {
        int N = arr.length;
        int k = 1, hibb;

        // �������Hibbard��������
        List<Integer> gaps = new ArrayList<>();
        while(true) {
            hibb = (int)(Math.pow(2, k) - 1);
            if (hibb > N) break;
            gaps.add(hibb);
            ++k;
        }

        for (k = gaps.size()-1; k >= 0; --k) {
            int gap = gaps.get(k);
            for (int i = gap; i < N; ++i) {
                Comparable key = arr[i];
                int j = i;
                while (j >= gap && arr[j-gap].compareTo(key) >= 0) {
                    swap(arr, j, j - gap);
                    j -= gap;
                }
                arr[j] = key;
            }
        }
    }


    public static double time(String alg) {

        Double [] arr = new Double[100000];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = Math.random();

        long callTime = System.currentTimeMillis();
        switch (alg) {
            case "bubbleSort":
                bubbleSort(arr);
                break;
            case "insertSort":
                insertSort(arr);
                break;
            case "shellSort":
                shellSort(arr);
                break;
        }
        callTime = System.currentTimeMillis() - callTime;
        return callTime*1.0/1000;
    }


    public static void main(String [] args) {
        Integer [] arr = new Integer[10];
        Random random = new Random(123);
        for (int i = 0; i < arr.length; ++i)
            arr[i] = random.nextInt(100);

        insertSort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a, b);
            }
        });
        out.println(Arrays.toString(arr));
        //�������100000��Ԫ�أ����������㷨������ʱ��Ϊ
        out.println("shell sort : " + time("shellSort") + "s");
        out.println("insert sort : " + time("insertSort") + "s");
        out.println("bubble sort: " + time("bubbleSort") + "s");
    }
}

