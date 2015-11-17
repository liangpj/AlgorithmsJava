package Sort;


import java.util.Arrays;

public class MergeSort {

    /**
     * 将两个排好序的数组arr[p, q-1] 和 arr[q, r]归并成一个排好序的数组arr[p, r]
     * @param arr 数组
     * @param p 左边数组的起始位置
     * @param q 左边数组的结束位置但不包含
     * @param r 右边数组的结束位置
     */
    private static  void merge(Comparable [] arr, int p, int q, int r) {
        int leftN = q - p;  //左边数组元素个个数
        int rightN = r - q + 1;     //右边数组元素的个数

        // 临时开辟两个数组
        Comparable [] left = new Comparable[leftN];
        Comparable [] right = new Comparable[rightN];

        System.arraycopy(arr, p, left, 0, leftN);
        System.arraycopy(arr, q, right, 0, rightN);

        int i = 0, j = 0, k = p;
        while (i < leftN && j < rightN) {
            if (left[i].compareTo(right[j]) < 0)
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }

        while (i < leftN)
            arr[k++] = left[i++];
        while (j < rightN)
            arr[k++] = right[j++];
    }

    /**
     * 将数组长度为length的有序序列归并并一个长度为2*length的有序序列
     * @param arr arr
     * @param length 长度为length是有序的
     */
    private static void mergetSeq(Comparable [] arr, int length) {
        int i = 0;
        for (; i <= arr.length - 2*length; i += 2*length)
            merge(arr, i, i + length, i + 2*length-1);

        if (i + length < arr.length)  // 只剩下一个子序列
            merge(arr, i, i+length, arr.length-1);
    }

    /**
     *将数组arr[fromIndex, endIndex]进行排序
     * @param arr 要排序的数组
     * @param fromIndex 数组的起始位置
     * @param endIndex  数组的结束位置
     */
    public static void mergeSort(Comparable [] arr, int fromIndex, int endIndex) {
        if (fromIndex < endIndex) {
            int mid = fromIndex + (endIndex - fromIndex) / 2;
            mergeSort(arr, fromIndex, mid);
            mergeSort(arr, mid + 1, endIndex);
            merge(arr, fromIndex, mid+1, endIndex);
        }
    }

    /**
     * 使用非递归实现归并排序
     * @param arr 要排序的数组
     */
    public static void mergeSort(Comparable [] arr) {
        int length = 1;  //刚开始时，有序序列为1
        while (length < arr.length) {
            mergetSeq(arr, length);
            length *= 2;
        }
    }

    public static void sort(Comparable [] arr)  {
        mergeSort(arr, 0, arr.length-1);
    }

    public static void main(String [] args) {
        Integer arr [] = new Integer[]{7,3,1,4,6,8,2,5,9};
        sort(arr);
//        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
