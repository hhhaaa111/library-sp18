public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    @Override
    public void addFirst(T item){
        T[] a = (T[])new Object[size+1];
        a[0] = item;
        System.arraycopy(a,1,a,1,size);
        items = a;
        size++;
    }

    public void resize(int size){
        T[] a = (T[]) new Object[size*2];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }

    @Override
    public void addLast(T item){
        if(size == items.length){
            resize(size);
        }
        items[size] = item;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(items[i]+" ");
        }
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T[] a = (T[]) new Object[size-1];
        T first = items[0];
        System.arraycopy(items,1,a,0,size-1);
        items = a;
        size--;
        return first;
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T[] a = (T[]) new Object[size-1];
        T last = items[size-1];
        System.arraycopy(items,0,a,0,size-1);
        items = a;
        size--;
        return last;
    }

    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return items[index];
    }

}