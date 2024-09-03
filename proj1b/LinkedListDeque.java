public class LinkedListDeque<T> implements Deque<T> {
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

    public LinkedListDeque(){
       sentinel = new Node(null, null, null);
       sentinel.prev = sentinel;
       sentinel.next = sentinel;
       size = 0;
    }

    @Override
    public void addFirst(T item){
       Node<T> first = new Node(item, sentinel, sentinel.next);
       sentinel.next = first;
       sentinel.next.prev = first;
       size++;
    }

    @Override
    public void addLast(T item){
       Node<T> last = new Node(item,sentinel.next,sentinel);
       sentinel.prev = last;
       sentinel.next.prev = last;
       size++;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
      }//如果是错误的，会直接输出false，不需要写if，else了

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
       Node<T> p = sentinel.next;
       while(size != 0){
           System.out.println(p.item + " ");
           p = p.next;
           size--;
       }
    }

    @Override
    public T removeFirst(){
        if(size == 0)
            return null;
        Node<T> firstNode = sentinel.next;
        T a = (T) firstNode.item;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;
        firstNode.prev = null;
        firstNode.next = null;
        return a;
    }

    @Override
    public T removeLast(){
       if(size == 0)
           return null;
       Node<T> lastNode = sentinel.prev;
       T a = (T) lastNode.item;
       lastNode.prev.next = sentinel;
       sentinel.prev = lastNode.prev;
       size--;
       lastNode.prev = null;
       lastNode.next = null;
       return a;
    }

    @Override
    //迭代
    public T get(int index){
      int t = index + 1;
      Node<T> p = sentinel.next;
      while(t!=0){
          p = p.next;
          t--;
      }
      return p.item;
    }

    //递归
    public T getRecursive(int index){
         Node<T> current = sentinel.next;
         if(index == 0){
               return (T)current.item;
           }
           return getRecursive(index - 1);
        }
 }