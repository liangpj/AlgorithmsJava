package Sort;

import java.util.Arrays;
import java.util.Random;
import static java.lang.System.out;
import static java.lang.System.setErr;

public class QuickSort {

    final private static int CUTOFF = 10; //一般5 <= CUTOFF <= 15效果比较好

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 选择arr[low] arr[hig] arr[mid]的中位数作为主元, 并且把主元赋值给arr[hig-1]
    private static void adjustPivot(Comparable [] arr, int low, int hig) {
        int mid = low + (hig - low) / 2;
        int tmp = 0;

        if (arr[low].compareTo(arr[mid]) > 0)
            swap(arr, low, mid);

        if (arr[hig].compareTo(arr[mid]) < 0)
            swap(arr, hig, mid);

        if (arr[low].compareTo(hig) > 0)
            swap(arr, low, hig);

        //swap(arr, mid, hig);
        swap(arr, mid, hig-1);
    }

    /**
     * 使用快速排序算法对数组arr进行排序
     * @param arr 要排序的数组
     * @param fromIndex 要排序的起始位置
     * @param endIndex  要排序的结束位置
     */
    public static void quickSort(Comparable [] arr, int fromIndex, int endIndex) {

        if (endIndex <= fromIndex)
            return;

        // 调整主元，即将arr[endIndex]调整为arr{fromIndex, midIndex, endIndex}的中位数
        adjustPivot(arr, fromIndex, endIndex);
        int pivot = endIndex-1;
        int left = fromIndex-1, right = endIndex+1;
        while (true) {
            while (arr[++left].compareTo(arr[pivot]) < 0) ;
            while (arr[--right].compareTo(arr[pivot]) > 0) ;
            if (left < right)
                swap(arr, left, right);
            else
                break;
        }
        swap(arr, left, pivot);

        quickSort(arr, fromIndex, left-1);
        quickSort(arr, left+1,  endIndex);
    }

    /**
     * 使用shell 排序算法将数组进行排序
     * @param arr 要排序的数组
     * @param fromIndex 起始位置
     * @param endIndex 结束位置
     */
    public static void shellSort(Comparable [] arr, int fromIndex, int endIndex)
    {
        int N = endIndex - fromIndex + 1;   //元素的个数
        int h = 1;

        // 计算增量
        while (true) {
            int tmp = 3*h + 1;
            if (tmp > N) break;
            h = tmp;
        }

        while (h > 0) {
            for (int i = fromIndex + h; i <= endIndex; i ++) {
                Comparable key = arr[i];
                int j = i;
                while(j >= fromIndex+h && arr[j].compareTo(arr[j-h]) < 0) {
                    swap(arr, j, j - h);
                    j -= h;
                }
                arr[j] = key;
            }
            h = (h-1)/3;
        }
    }

    public static double time(String alg, int N) {
        Random random = new Random(123);
        Integer [] arr = new Integer[N];
        for (int i = 0; i < N; ++i)
            arr[i] = random.nextInt();

        long callTime = 0; // = System.currentTimeMillis();
        switch (alg) {
            case "quickSort" :
                callTime = System.currentTimeMillis();
                quickSort(arr, 0, N-1);
                callTime = System.currentTimeMillis() - callTime;
                break;
            case "shellSort" :
                callTime = System.currentTimeMillis();
                shellSort(arr, 0, N-1);
                callTime = System.currentTimeMillis() - callTime;
                break;
            case "sort" :
                callTime = System.currentTimeMillis();
                sort(arr, 0, N-1);
                callTime = System.currentTimeMillis() - callTime;
                break;
            default:
                break;
        }
        return callTime*1.0/1000;
    }

    public static void sort(Comparable [] arr, int fromIndex, int endIndex) {

        // 如果数组中的元素大于100，使用快速排序
        if (endIndex - fromIndex >= CUTOFF) {

            adjustPivot(arr, fromIndex, endIndex);
            Comparable pivot = arr[endIndex-1];
            int left = fromIndex-1, right = endIndex+1;
            while (true) {
                while (arr[++left].compareTo(pivot) < 0)  ;
                while (arr[--right].compareTo(pivot) > 0) ;

                if (left < right)
                    swap(arr, left, right);
                else
                    break;
            }
            swap(arr, left, endIndex-1);

            sort(arr, fromIndex, left - 1);
            sort(arr, left + 1, endIndex);
        } else {
            shellSort(arr, fromIndex, endIndex);
        }
    }

    public static void main(String [] args) {

        Random random = new Random();
        int N = random.nextInt(1000000);
        out.println("N = " + N);
        out.println("quick sort : " + time("quickSort", N) + "s");
        out.println("shell sort : " + time("shellSort", N) + "s");
        out.println("quick sort + shell sort : " + time("sort", N) + "s");
    }

}
