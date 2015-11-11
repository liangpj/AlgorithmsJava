package BaseAlg;

import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class Stack<Item> implements Iterable<Item>{
    private class Node {
        Item item;
        Node next;
    }
    private Node top;   //栈顶
    private int N = 0;      //栈的大小

    /**
     * 将元素添加到栈中
     * @param item 要添加的元素
     */
    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = top;
        top = node;
        ++N;
    }

    /**
     * 删除最近添加的元素
     * @return 删除的元素
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
     * 判断栈是否为空
     * @return 如果栈为空返回true, 否者返回false
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * 求栈的大小
     * @return 栈的大小
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
