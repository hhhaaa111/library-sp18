/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < asciis.length; i++) {
            max = Math.max(max, asciis[i].length());
        }
        int index = 0;
        while(index <= max) {
            sortHelperLSD(asciis, index);
            index += 1;
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.破坏性的counting sort方法，对于array，string要转化为数字，变成index
     * @param asciis Input array of Strings输入array of string
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        //按照末尾数分类，再按照末尾数排序,index = 0,从后往前第0个
        int[] counts = new int[256];
        for (int i = 0; i < asciis.length; i++) {
            if(asciis[i].length() - 1 - index >= 0 ){
            int asc = (int) asciis[i].charAt(asciis[i].length() - 1 - index);
            counts[asc]++;
           }else{
                counts[0]++;
            }
        }
        int[] starts = new int[256];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            if(asciis[i].length() - 1 - index >= 0 ){
            int num = (int) asciis[i].charAt(asciis[i].length() - 1 - index);
            String item = asciis[i];
            int place = starts[num];
            sorted[place] = item;
            starts[num] += 1;
            }else{
                String item = asciis[i];
                int place = starts[0];
                sorted[place] = item;
                starts[0] += 1;
            }
        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
