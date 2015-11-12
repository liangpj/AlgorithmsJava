package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static java.lang.System.out;

/**
 * 堆
 */
public class Heap<T extends Comparable<T>> {

    private T [] arr;
    private int N = 0;
    private Comparator comparator = null;

    private void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 创建一个默认大小的堆，默认为最小堆
     */
    public Heap() {
        arr = (T[]) new Comparable[20];
    }

    /**
     * 创建一个初始容量为cap的堆，默认为最小堆
     *
     * @param cap 初始容量
     */
    public Heap(int cap) {
        arr = (T[]) new Comparable[cap];
    }

    /**
     * 比较器
     *
     * @param comparator
     */
    public Heap(Comparator comparator) {
        this();
        this.comparator = comparator;
    }

    /**
     * 获取堆的大小
     *
     * @return 堆的大小
     */
    public int size() {
        return N;
    }

    /**
     * 将堆的大小重新调整为max
     *
     * @param max 堆的大小
     */
    private void resize(int max) {
        T[] tmp = (T[]) new Comparable[max];
        // 将数组中的元素拷贝到数组tmp上去
        System.arraycopy(arr, 0, tmp, 0, N);
        arr = tmp;
    }

    /**
     * 判断堆是否为空
     *
     * @return 如果为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 将元素插入到堆中
     *
     * @param item
     */
    public void insert(T item) {
        if (N == arr.length) resize(2 * N);
        int i = N;
        arr[i] = item;  //首先先将元素插入到堆的后面
        //调整堆
        while (i > 0) {
            if( (comparator != null && comparator.compare(arr[(i-1)/2], arr[i]) > 0)
                    || (comparator==null && arr[(i-1)/2].compareTo(arr[i]) > 0) ) {
                swap(arr, (i-1)/2, i);
                i = (i-1)/2;
            } else
                break;
        }
        ++N;
    }

    /**
     * 获取堆中的第一个元素
     * @return 如果堆存在元素则放回第一个元素，否者返回null
     */
    public T getFirst() {
        if (N > 0) return arr[0];
        else
            return null;
    }

    /**
     * 删除堆的第一个元素
     * @return 如果堆不为空则返回堆中第一个元素，否则返回null
     */
    public T deleteFirst() {
        T elem = null;
        if (N > 0) {
            elem = arr[0];
            arr[0] = arr[N-1];
            arr[N-1] = null; --N;
            // 调整堆
            int parent = 0, ch;
            while ((parent+1)*2 <= N) {
                ch = 2*parent + 1;  //左孩子
                //存在右孩子
                if ((ch + 1) < N ) {
                    // 找出左右孩子较小的结点
                    if ((comparator!=null && comparator.compare(arr[ch], arr[ch+1]) > 0 )
                            || (comparator==null && arr[ch].compareTo(arr[ch+1])>0)) {
                        ch = ch+1;
                    }
                }
                if ((comparator != null && comparator.compare(arr[parent], arr[ch]) > 0) ||
                        (comparator==null && arr[parent].compareTo(arr[ch]) > 0)) {
                    swap(arr, parent, ch);
                    parent = ch;
                } else
                    break;
            }
        }
        return elem;
    }
    public static void main(String [] args) {
        // 创建一个最大堆
        Heap<Integer> heap = new Heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a);
            }
        });

        int [] arr = new int[30];
        Random random = new Random();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt(50);
            heap.insert(arr[i]);
        }
        Arrays.sort(arr);

        out.println(Arrays.toString(arr));
        while (!heap.isEmpty())
            out.print(heap.deleteFirst() + " ");
    }
}
