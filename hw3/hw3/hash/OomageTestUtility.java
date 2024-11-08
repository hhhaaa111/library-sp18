package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        if(N == 0) return false;
        int left = N / 50;
        int right = (int)(N / 2.5);
        List<Oomage>[] hashes = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            hashes[i] = new ArrayList<>();
        }

        for ( Oomage o : oomages){
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            hashes[bucketNum].add(o);
        }

        for(int i = 0; i< M; i++){
            if(hashes[i].size() < left || hashes[i].size() > right){
                return false;
            }
        }
        return true;
    }
}
