import java.util.Arrays;

public class TestLSD {
    public static void main(String[] args) {
        RadixSort a = new RadixSort();
        String[] b = {"pineapple","apple", "banana", "orange"};
        RadixSort.sort(b);
        System.out.println(Arrays.toString(b));
    }
}
