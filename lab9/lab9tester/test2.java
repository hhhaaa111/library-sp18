package lab9tester;

import lab9.BSTMap;

public class test2 {

    public static void main(String[] args) {
        BSTMap<String, Integer> map = new BSTMap<>();

        // 插入一些键值对
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("date", 4);

        // 打印初始大小
        System.out.println("Initial size: " + map.size()); // 应输出 4

        // 测试删除存在的键
        System.out.println("Removing 'banana': " + map.remove("banana")); // 应输出 2
        System.out.println("New size: " + map.size()); // 应输出 3
        System.out.println("Contains 'banana': " + map.keySet().contains("banana")); // 应输出 false

        // 测试删除不存在的键
        System.out.println("Removing 'orange' (non-existing key): " + map.remove("orange")); // 应输出 null
        System.out.println("Size after attempting to remove non-existing key: " + map.size()); // 应输出 3

        // 测试带值删除
        System.out.println("Removing 'cherry' with correct value: " + map.remove("cherry", 3)); // 应输出 3
        System.out.println("Size after removing 'cherry': " + map.size()); // 应输出 2
        System.out.println("Contains 'cherry': " + map.keySet().contains("cherry")); // 应输出 false

        // 测试带错误值的删除
        System.out.println("Removing 'date' with incorrect value: " + map.remove("date", 5)); // 应输出 null
        System.out.println("Size after failed removal: " + map.size()); // 应输出 2
        System.out.println("Contains 'date': " + map.keySet().contains("date")); // 应输出 true

        // 最后再删除剩余的键
        System.out.println("Removing 'apple': " + map.remove("apple")); // 应输出 1
        System.out.println("Size after removing 'apple': " + map.size()); // 应输出 1

        System.out.println("Removing 'date': " + map.remove("date")); // 应输出 4
        System.out.println("Final size: " + map.size()); // 应输出 0

        System.out.println("All tests passed.");
    }
}


