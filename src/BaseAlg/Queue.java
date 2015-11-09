package BaseAlg;


import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class Queue<Item> implements Iterable<Item>{

    private class Node {
        Item item;
        Node next;
    }
    private Node first;  // 指向最早添加的节点的元素
    private Node last;   // 指向最近添加的节点的元素
    private int N;

    /**
     * 添加一个元素
     * @param item 要添加的元素
     */
    void enqueque(Item item) { // 向链表尾添加元素
        Node node = new Node();
        node.item = item;
        if (N == 0) { // 链表为空
            first = last = node;
        } else  {
            last.next = node;
            last = node;
        }
        ++N;
    }

    /**
     * 删除最早添加的元素
     * @return 如果队列不为空则返回删除的元素，否则返回null
     */
    Item dequeue() { // 删除链头的元素
        Item item = null;
        if (N > 0) {
            item = first.item;
            Node tmp = first;
            first = first.next;
            tmp = null;
            if (first == null) { // 队列为空
                last = null;
            }
            --N;
        }
        return item;
    }

    /**
     * 判断队列是否为空
     * @return 如果队列为空返回true, 否则返回false
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * 计算队列中的元素的数量
     * @return 返回队列的大小
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
