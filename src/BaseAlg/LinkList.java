package BaseAlg;

import java.util.Iterator;

import static java.lang.System.out;

public class LinkList<Item> implements Iterable<Item>{

    /**
     * ����һ��Ƕ��������ʾ������������
     */
    private class Node {
        Item item;
        Node next;
    }

    private Node head; // �����ͷ���
    private int size;  // ����Ĵ�С

    /**
     * ����Ĺ��캯��,����һ������ͷ��������
     */
    LinkList() {
        this.head = new Node();
        this.size = 0;
    }

    /**
     * ���Ԫ�ص�����ͷ��
     * @param item Ҫ��ӵ�Ԫ��
     */
    public void addFirst(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = head.next;
        head.next = node;
        ++size;
    }

    /**
     * ��Ԫ����ӵ������β��
     * @param item Ҫ��ӵ�Ԫ��
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
     * ɾ����K��Ԫ��
     * @param k ��k��Ԫ��
     * @return ���kС������Ĵ�С�򷵻�ɾ����Ԫ�أ����򷵻�null;
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
     * ��ȡ����Ԫ�صĸ���
     * @return ����Ĵ�С
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
