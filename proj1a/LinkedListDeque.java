public class LinkedListDeque<T> {
    public class Node<T> {
        public Node prev;
        public T item;
        public Node<T> next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    Node sentinel;
    int size;

    public LinkedListDeque() {
       sentinel = new Node(null, null, null);
       sentinel.prev = sentinel;
       sentinel.next = sentinel;
       size = 0;
    }

    public void addFirst(T item) {
       Node first = new Node(item, sentinel, sentinel.next);
       sentinel.next = first;
       sentinel.next.prev = first;
       size++;
    }

    public void addLast(T item) {
       Node last = new Node(item, sentinel.next, sentinel);
       sentinel.prev = last;
       sentinel.next.prev = last;
       size++;
    }

    public boolean isEmpty() {
        return size == 0;
      }//如果是错误的，会直接输出false，不需要写if，else了

    public int size() {
        return size;
    }

    public void printDeque() {
       Node p = sentinel.next;
       while(size != 0) {
           System.out.println(p.item + " ");
           p = p.next;
           size--;
       }
    }

    public T removeFirst() {
        if(size == 0)
            return null;
        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;
        firstNode.prev = null;
        firstNode.next = null;
        return firstNode.item;
    }

    public T removeLast() {
       if(size == 0)
           return null;
       Node lastNode = sentinel.prev;
       lastNode.prev = sentinel;
       sentinel.prev = lastNode.prev;
       size--;
       lastNode.prev = null;
       lastNode.next = null;
       return lastNode.item;
    }

    //迭代
    public T get(int index) {
      int t = index + 1;
      Node p = sentinel.next;
      while(t!=0) {
          p = p.next;
          t--;
      }
      return p.item;
    }

    //递归
    public T getRecursive(int index) {
       private T getRecursivehelper(Node current, int index) {
           current = sentinel.next;
           this.index = index;
           if(index == 0) {
               return current.item;
           }
           return getRecursivehelper(current.next, index-1);
        }
    }
}