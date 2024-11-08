import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items.
        返回一个queue，里面包含俩个queue，被一分为二的体现*/
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        if(items == null || items.isEmpty()) {
            return null;
        }
        int size = items.size();
        Queue<Item> queues2 = new Queue<>();
        while(queues2.size() <= size/2) {
            queues2.enqueue(items.dequeue());
        }
        Queue<Queue<Item>> queues3 = new Queue<>();
        queues3.enqueue(queues2);
        queues3.enqueue(items);
        return queues3;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * 返回一个新的queue里面包含q1和q2被排序好的元素
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *运行时间为N，运行此方法结束后，q1和q2会是空的，所有元素都在要return的queue中
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> result = new Queue<>();
        while(!q1.isEmpty() || !q2.isEmpty()) {
            result.enqueue(getMin(q1,q2));
        }
        return result;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if(items == null ||items.isEmpty()) {
            items = new Queue<>();
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        items = mergeSortedQueues(queues.dequeue(),queues.dequeue());
        return items;
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        Queue<String> c = new Queue<>();
        while (!students.isEmpty()) {
            String s = students.dequeue();
            System.out.println(s);
            c.enqueue(s);//定一个变量=students没用，都是指向同一个的，只能重新copy一个
        }
        Queue<String> b = mergeSort(c);//不能简单的 mergeSort(c)，返回值没地方显示，还是要重新定义一个queue
        while(!b.isEmpty()) {
            System.out.println(b.dequeue());
        }
    }
}
