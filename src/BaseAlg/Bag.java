package BaseAlg;

import java.util.Iterator;
import java.util.Scanner;

// ����
public class Bag<Item> implements Iterable<Item>{

    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private int N;

    /**
     * ���Ԫ��
     * @param item Ҫ��ӵ�Ԫ��
     */
    void add(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        ++N;
    }

    /**
     * �жϱ����Ƿ�Ϊ��
     * @return �������Ϊ���򷵻�true, ���򷵻�false;
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * ���ر����Ĵ�С
     * @return �����Ĵ�С
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
        Bag<String> bag = new Bag<>();

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.next();
            bag.add(str);
        }
        for (String s : bag)
            System.out.print(s + " ");
        System.out.println();
    }
}
