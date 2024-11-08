import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BTSMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    public class Node<K,V>{
        private K key;
        private V value;
        private Node<K,V> left;
        private Node<K,V> right;

        public Node(K key, V value, Node<K,V> left, Node<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node<K,V> root;
    public int size;
    private Set<K> keySet = new HashSet<>();

    public BTSMap() {
       root = null;
       size = 0;
    }

    /*public BTSMap(Node<K,V> root) {
        this.root = root;
        keySet.add(root.key);
    }*/

    @Override
    public void clear(){
       root = null;
       keySet.clear();
    }

    public Node<K,V> find(Node<K,V> T, K key){
        Node<K,V> node = T;
        if(node == null){
            return null;
        }else if(node.key.compareTo(key) == 0){
            return node;
        }else if(node.key.compareTo(key) < 0){
            return find(node.left,key);
        }else
            return find(node.right,key);
    }

    @Override
    public boolean containsKey(K key) {
        return find(root,key) != null;
    }

    @Override
    public V get(K key) {
       Node<K,V> node = find(root,key);
       if(node == null){
           return null;
       }else
           return node.value;
    }



    @Override
    public int size() {
        return size;
    }

    public Node<K,V> insert(Node<K,V> T,K key, V value){
        Node<K,V> node = T;
        if(node == null){
            node = new Node(key,value,null,null);
        }else if(node.key.compareTo(key) < 0) {
            node.left = insert(node.left,key, value);
        }else
            node.right = insert(node.right,key, value);
        size++;
        keySet.add(key);
        return node;
    }

    @Override
    //学号：12345，成绩：88；之后想改成绩为77
    public void put(K key, V value) {
        Node<K,V> node = find(root,key);
        if(node == null){
            root = insert(root,key,value);
        }else {
            node.value = value;
        }
    }

    //返回key
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        Node<K,V> node = find(root,key);
        if(size == 0) {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }else
            root = remove(root,key);
        return node.value;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }

    public Node<K,V> remove(Node<K,V> T, K k){
        if(T == null) {
            return null;
        }
        int cmp = T.key.compareTo(k);
        if( cmp < 0) {
            T.left = remove(T.left, k);
        }else if(cmp > 0) {
            T.right = remove(T.right, k);
        }else {
            //0个子类和1个子类
            if(T.left == null) return T.right;
            if(T.right == null) return T.left;
            //2个子类
            Node<K,V> newroot = findMin(T.right);
            T.key = newroot.key;
            T.value = newroot.value;
            T.right = remove(T.right, newroot.key);
        }
        size--;
        return T;
    }

    private Node<K,V> findMin(Node<K,V> T){
        while(T.left != null){
            T = T.left;
        }
        return T;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}