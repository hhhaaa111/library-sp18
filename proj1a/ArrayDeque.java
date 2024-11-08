public class ArrayDeque<T> {
    private T[] items;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {
        T[] a = (T[])new Object[size+1];
        a[0] = item;
        System.arraycopy(items,1,a,1,size);
        items = a;
        size++;
    }

    public void resize(int size) {
        T[] a = (T[]) new Object[size*2];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }

    public void addLast(T item) {
       if(size == items.length) {
          resize(size);
       }
       items[size] = item;
       size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
       for(int i = 0; i < size; i++) {
           System.out.print(items[i]+" ");
       }
    }

    public T removeFirst() {
       if(size == 0) {
           return null;
       }
       T[] a = (T[]) new Object[size-1];
       T first = items[0];
       System.arraycopy(items,1,a,0,size-1);
       items = a;
       size--;
       return first;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T[] a = (T[]) new Object[size-1];
        T last = items[size-1];
        System.arraycopy(items,0,a,0,size-1);
        items = a;
        size--;
        return last;
    }

    public T get(int index) {
       if(index < 0 || index >= size) {
           return null;
       }
       return items[index];
    }
}
