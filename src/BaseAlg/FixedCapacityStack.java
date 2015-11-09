package BaseAlg;


import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class FixedCapacityStack<Item> implements Iterable<Item>{

    private Item [] a;   // stack entries
    private int N;      // size

    /**
     * ����һ����ʼ����Ϊcap�Ŀ�ջ
     * @param cap ջ������
     */
    FixedCapacityStack(int cap) {
        // ע��java����������һ����������
        // ��ˣ���Ҫ����װ��
        a = (Item []) new Object[cap];
        N = 0;
    }

    /**
     * �ж�ջ�Ƿ�Ϊ��
     * @return ���ջΪ���򷵻�true�����򷵻�false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * ��ջ�Ĵ�С
     * @return ջ�Ĵ�С
     */
    public int size() {
        return N;
    }

    /**
     * ����ջ�����Ĵ�С
     * @param max ջ������
     */
    private void resize(int max) {
        // ����СΪN <= max ��ջ�ƶ���һ���µĴ�СΪmax��������
        Item [] tmp = (Item []) new Object[max];
        //for (int i = 0; i < N; ++i)
        //tmp[i] = a[i];
        System.arraycopy(a, 0, tmp, 0, N);
        a = tmp;
    }

    /**
     * ���Ԫ�ص�ջ��
     * @param item Ҫ��ӵ�Ԫ��
     */
    public void push(Item item) {
        // ��Ԫ��Ҫ��ջ��
        if (a.length == N) resize(2*N);
        a[N++] = item;
    }

    /**
     * ��ջ��ɾ��Ԫ��
     * @return ��ɾ����Ԫ��
     */
    public Item pop() {
        Item item = a[--N];
        a[N] = null; // �����������
        if (N > 0 && N == a.length / 4) resize(a.length/2);
        return item;
    }



    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i  = N;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return a[--i];
            }
        };
    }

    public static void main(String [] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String item = in.next();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                out.print(s.pop() + " ");
        }
        out.println("( " + s.size() + " left on stack )");

    }


}
