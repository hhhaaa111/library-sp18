package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private double k;

    public AcceleratingSawToothGenerator(int period,double k) {
        state = 0;
        this.period = period;
        this.k = k;
    }

    public double next(){
        state += 1;
        if(state == period ){
            period = (int) (period * k);
            state = 0;
        }
        return normalize(state % period);
    }

    public double normalize(int x){
        return (double)2 * x / period  - 1;
    }
}
