package synthesizer;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public double[] hzmath (char[] s) {
        double[] a = new double[s.length];
        for(int i=0; i<s.length; i++){
            a[i] = 440 * Math.pow(2 , (s[i] - 24) / 12.0);
        }
        return a;
    }

    public boolean contains( char x, double[] key) {
        int left = 0;
        int right = key.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (key[mid] == x) {
                return true;
            }else if(key[mid] < x) {
                left = mid + 1;
            }else
                right = mid - 1;
        }
        return false;
    }

    private char[] white = new char[]{'q', 'w', 'e', 'r', 't','y', 'u', 'i', 'o', 'p', 'z','x','c','v','b','n', 'm'};
    private char[] black = new char[]{'1', '2', '3', '4', '5', '7','8','9','0','a', 's', 'd', 'f','g','h','j', 'k', 'l',' '};
    private double[] whiteKey = hzmath(white);
    private double[] blackKey = hzmath(black);

    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    GuitarString stringA = new GuitarString(CONCERT_A);
    GuitarString stringC = new GuitarString(CONCERT_C);

    public static void main(String[] args) {
        GuitarHero hero = new GuitarHero();

        //while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (hero.contains(key, hero.whiteKey)) {
                    hero.stringA.pluck();
                } else if (hero.contains(key, hero.blackKey)) {
                    hero.stringC.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = hero.stringA.sample() + hero.stringC.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            hero.stringA.tic();
            hero.stringC.tic();
        //}
    }
}
