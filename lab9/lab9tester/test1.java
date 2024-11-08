package lab9tester;

import lab9.BSTMap;

import java.util.Set;
import java.util.TreeSet;

public class test1 {
    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);


        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Orange");
        System.out.println(treeSet); // 输出 [Apple, Banana, Orange]，按字母顺序排序
    }
}
