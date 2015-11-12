package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static java.lang.System.out;

/**
 * ��
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
     * ����һ��Ĭ�ϴ�С�Ķѣ�Ĭ��Ϊ��С��
     */
    public Heap() {
        arr = (T[]) new Comparable[20];
    }

    /**
     * ����һ����ʼ����Ϊcap�Ķѣ�Ĭ��Ϊ��С��
     *
     * @param cap ��ʼ����
     */
    public Heap(int cap) {
        arr = (T[]) new Comparable[cap];
    }

    /**
     * �Ƚ���
     *
     * @param comparator
     */
    public Heap(Comparator comparator) {
        this();
        this.comparator = comparator;
    }

    /**
     * ��ȡ�ѵĴ�С
     *
     * @return �ѵĴ�С
     */
    public int size() {
        return N;
    }

    /**
     * ���ѵĴ�С���µ���Ϊmax
     *
     * @param max �ѵĴ�С
     */
    private void resize(int max) {
        T[] tmp = (T[]) new Comparable[max];
        // �������е�Ԫ�ؿ���������tmp��ȥ
        System.arraycopy(arr, 0, tmp, 0, N);
        arr = tmp;
    }

    /**
     * �ж϶��Ƿ�Ϊ��
     *
     * @return ���Ϊ�շ���true�����򷵻�false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * ��Ԫ�ز��뵽����
     *
     * @param item
     */
    public void insert(T item) {
        if (N == arr.length) resize(2 * N);
        int i = N;
        arr[i] = item;  //�����Ƚ�Ԫ�ز��뵽�ѵĺ���
        //������
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
     * ��ȡ���еĵ�һ��Ԫ��
     * @return ����Ѵ���Ԫ����Żص�һ��Ԫ�أ����߷���null
     */
    public T getFirst() {
        if (N > 0) return arr[0];
        else
            return null;
    }

    /**
     * ɾ���ѵĵ�һ��Ԫ��
     * @return ����Ѳ�Ϊ���򷵻ض��е�һ��Ԫ�أ����򷵻�null
     */
    public T deleteFirst() {
        T elem = null;
        if (N > 0) {
            elem = arr[0];
            arr[0] = arr[N-1];
            arr[N-1] = null; --N;
            // ������
            int parent = 0, ch;
            while ((parent+1)*2 <= N) {
                ch = 2*parent + 1;  //����
                //�����Һ���
                if ((ch + 1) < N ) {
                    // �ҳ����Һ��ӽ�С�Ľ��
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
        // ����һ������
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
