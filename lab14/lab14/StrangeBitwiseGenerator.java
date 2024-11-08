package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next(){
        state += 1;
        int weirdState = state & (state >>> 3) % period;
        return normalize(weirdState);
    }

    public double normalize(int x){
        return (double)2 * x / period  - 1;
    }
}
