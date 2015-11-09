package BaseAlg;


import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class FixedCapacityStack<Item> implements Iterable<Item>{

    private Item [] a;   // stack entries
    private int N;      // size

    /**
     * 创建一个初始容量为cap的空栈
     * @param cap 栈的容量
     */
    FixedCapacityStack(int cap) {
        // 注意java并不允许创建一个泛型数组
        // 因此，需要类型装换
        a = (Item []) new Object[cap];
        N = 0;
    }

    /**
     * 判断栈是否为空
     * @return 如果栈为空则返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 求栈的大小
     * @return 栈的大小
     */
    public int size() {
        return N;
    }

    /**
     * 调整栈容量的大小
     * @param max 栈的容量
     */
    private void resize(int max) {
        // 将大小为N <= max 的栈移动到一个新的大小为max的数组中
        Item [] tmp = (Item []) new Object[max];
        //for (int i = 0; i < N; ++i)
        //tmp[i] = a[i];
        System.arraycopy(a, 0, tmp, 0, N);
        a = tmp;
    }

    /**
     * 添加元素到栈中
     * @param item 要添加的元素
     */
    public void push(Item item) {
        // 将元素要入栈顶
        if (a.length == N) resize(2*N);
        a[N++] = item;
    }

    /**
     * 从栈顶删除元素
     * @return 被删除得元素
     */
    public Item pop() {
        Item item = a[--N];
        a[N] = null; // 避免对象游离
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
