package BaseAlg;

import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class Stack<Item> implements Iterable<Item>{
    private class Node {
        Item item;
        Node next;
    }
    private Node top;   //ջ��
    private int N = 0;      //ջ�Ĵ�С

    /**
     * ��Ԫ����ӵ�ջ��
     * @param item Ҫ��ӵ�Ԫ��
     */
    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = top;
        top = node;
        ++N;
    }

    /**
     * ɾ�������ӵ�Ԫ��
     * @return ɾ����Ԫ��
     */
    public Item pop() {
        Item item = null;
        if (N > 0) {
            Node tmp = top.next;
            item = tmp.item;
            top.next = tmp.next;
            tmp = null;
            --N;
        }
        return item;
    }

    /**
     * �ж�ջ�Ƿ�Ϊ��
     * @return ���ջΪ�շ���true, ���߷���false
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * ��ջ�Ĵ�С
     * @return ջ�Ĵ�С
     */
    int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            Node curr = top.next;
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
        Stack<String> s = new Stack<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) { // input: to be or not to - be - - that - - - is
            String item = in.next();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                out.print(s.pop() + " ");
        }
        out.println("( " + s.size() + " left on stack )");
    }
}
