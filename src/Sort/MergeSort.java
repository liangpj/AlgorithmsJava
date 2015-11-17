package Sort;


import java.util.Arrays;

public class MergeSort {

    /**
     * �������ź��������arr[p, q-1] �� arr[q, r]�鲢��һ���ź��������arr[p, r]
     * @param arr ����
     * @param p ����������ʼλ��
     * @param q �������Ľ���λ�õ�������
     * @param r �ұ�����Ľ���λ��
     */
    private static  void merge(Comparable [] arr, int p, int q, int r) {
        int leftN = q - p;  //�������Ԫ�ظ�����
        int rightN = r - q + 1;     //�ұ�����Ԫ�صĸ���

        // ��ʱ������������
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
     * �����鳤��Ϊlength���������й鲢��һ������Ϊ2*length����������
     * @param arr arr
     * @param length ����Ϊlength�������
     */
    private static void mergetSeq(Comparable [] arr, int length) {
        int i = 0;
        for (; i <= arr.length - 2*length; i += 2*length)
            merge(arr, i, i + length, i + 2*length-1);

        if (i + length < arr.length)  // ֻʣ��һ��������
            merge(arr, i, i+length, arr.length-1);
    }

    /**
     *������arr[fromIndex, endIndex]��������
     * @param arr Ҫ���������
     * @param fromIndex �������ʼλ��
     * @param endIndex  ����Ľ���λ��
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
     * ʹ�÷ǵݹ�ʵ�ֹ鲢����
     * @param arr Ҫ���������
     */
    public static void mergeSort(Comparable [] arr) {
        int length = 1;  //�տ�ʼʱ����������Ϊ1
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
