package list;

public class LockDList extends DList {

    public void lockNode(DListNode node) {
        ((LockDListNode) node).locked = true;
    }

    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }

    public LockDList() {
        head = newNode(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public void remove(DListNode node) {
        if (node == null || node == head || ((LockDListNode)node).locked == true);
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    public static void main(String[] args) {
        LockDList list = new LockDList();
        System.out.println(list.head);
        System.out.println(list.isEmpty());
        list.insertBack(1);
        list.insertBack(2);
        //list.insertFront(0);
        //list.insertFront(-1);
        System.out.println(list);
        System.out.println(list.size);
        list.lockNode(list.head.prev);
        System.out.println("lock the last element");
        System.out.println(((LockDListNode)list.head.prev).locked);
        System.out.println("remove the last element");
        list.remove(list.head.prev);
        System.out.println(list.size);
        System.out.println(list);
        System.out.println("remove the first element");
        list.remove(list.head.next);
        System.out.println(list.size);
        System.out.println(list);
        list.remove(list.head.next);
        System.out.println(list.size);
        System.out.println(list);

    }

}