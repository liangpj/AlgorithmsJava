package BaseAlg;

import java.util.Iterator;

import static java.lang.System.out;

public class LinkList<Item> implements Iterable<Item>{

    /**
     * 定义一个嵌套类来表示抽象数据类型
     */
    private class Node {
        Item item;
        Node next;
    }

    private Node head; // 链表的头结点
    private int size;  // 链表的大小

    /**
     * 链表的构造函数,创建一个带有头结点的链表
     */
    LinkList() {
        this.head = new Node();
        this.size = 0;
    }

    /**
     * 添加元素到链表头中
     * @param item 要添加的元素
     */
    public void addFirst(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = head.next;
        head.next = node;
        ++size;
    }

    /**
     * 将元素添加到链表的尾端
     * @param item 要添加的元素
     */
    public void addLast(Item item) {
        Node node = new Node();
        node.item = item;
        Node tmp = head;
        while(tmp.next != null) tmp = tmp.next;
        tmp.next = node;
        ++size;
    }

    /**
     * 删除第K个元素
     * @param k 第k个元素
     * @return 如果k小于链表的大小则返回删除的元素，否则返回null;
     */
    public Item delete(int k)  {
        Item item = null;
        if(size > 0 && k <= size) {
            Node tmp = head, node;
            for (int i = 0; i < k-1 && tmp != null;  ++i) tmp = tmp.next;
            node = tmp.next;
            item = node.item;
            tmp.next = node.next;
            node = null;
            --size;
        }
        return item;
    }

    /**
     * 获取链表元素的个数
     * @return 链表的大小
     */
    public int getSize() {return size;}

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node curr = head.next;
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
            public void remove() {}
        };
    }

    public static void main(String [] args) {
        String [] str = new String[] {"to", "be", "or", "not", "to", "be", "that", "is"};
        LinkList<String> list = new LinkList<>();
        for(String s : str)
            list.addFirst(s);

        for (String s : list) {
            out.print(s + " ");
        }
        out.println();

        out.println(list.delete(4));
    }
}
