package BaseAlg;


import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class Queue<Item> implements Iterable<Item>{

    private class Node {
        Item item;
        Node next;
    }
    private Node first;  // ָ��������ӵĽڵ��Ԫ��
    private Node last;   // ָ�������ӵĽڵ��Ԫ��
    private int N;

    /**
     * ���һ��Ԫ��
     * @param item Ҫ��ӵ�Ԫ��
     */
    void enqueque(Item item) { // ������β���Ԫ��
        Node node = new Node();
        node.item = item;
        if (N == 0) { // ����Ϊ��
            first = last = node;
        } else  {
            last.next = node;
            last = node;
        }
        ++N;
    }

    /**
     * ɾ��������ӵ�Ԫ��
     * @return ������в�Ϊ���򷵻�ɾ����Ԫ�أ����򷵻�null
     */
    Item dequeue() { // ɾ����ͷ��Ԫ��
        Item item = null;
        if (N > 0) {
            item = first.item;
            Node tmp = first;
            first = first.next;
            tmp = null;
            if (first == null) { // ����Ϊ��
                last = null;
            }
            --N;
        }
        return item;
    }

    /**
     * �ж϶����Ƿ�Ϊ��
     * @return �������Ϊ�շ���true, ���򷵻�false
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * ��������е�Ԫ�ص�����
     * @return ���ض��еĴ�С
     */
    int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Node curr = first;
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Item next() {
                Item item = curr.item;
                curr = curr.next;
                return item;
            }
        };
    }

    public static void main(String [] args) {
        Queue<String> q = new Queue<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) { // input: to be or not to - be - - that - - - is
            String item = in.next();
            if (!item.equals("-"))
                q.enqueque(item);
            else if (!q.isEmpty())
                out.print(q.dequeue() + " ");
        }
        out.println("( " + q.size() + " left on Queue )");
    }
}
