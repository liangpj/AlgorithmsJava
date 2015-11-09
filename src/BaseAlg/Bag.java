package BaseAlg;

import java.util.Iterator;
import java.util.Scanner;

// 背包
public class Bag<Item> implements Iterable<Item>{

    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private int N;

    /**
     * 添加元素
     * @param item 要添加的元素
     */
    void add(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        ++N;
    }

    /**
     * 判断背包是否为空
     * @return 如果背包为空则返回true, 否则返回false;
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回背包的大小
     * @return 背包的大小
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
