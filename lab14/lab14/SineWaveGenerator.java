package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SineWaveGenerator implements Generator {
	private double frequency;
	private int state;	

	public SineWaveGenerator(double frequency) {
		state = 0;//初始化state变量为0，表示从第一个样本开始生成
		this.frequency = frequency;//frequency频率，T=1/f
	}

	//每次调用这个方法，都会生成正弦波的下一个样本，return返回的是y
	public double next() {
		state = (state + 1);
		double period = StdAudio.SAMPLE_RATE / frequency;//period周期，StdAudio.SAMPLE_RATE 是采样率，表示每秒钟生成的样本数量（通常是 44,100 或 22,050）
		//得到每个周期内需要的样本数，即周期的样本数
		return Math.sin(state * 2 * Math.PI / period);
	}
}

/* How it works (requires some EE16 knowledge): Sample rate is 44100 Hz.
 * 
 * So that means for a 440 Hz note, we want our sine wave to go through
 * 100.2272 samples. By default, sin has a period of 2*pi. So our 0th
 * sample should correspond to t = 0, out 1st sample should correspond to
 * 2*pi/100.2272, our 2nd sample should be 2*pi/100.2272 * 2, ...
 * up to 2*pi/100.2272 * 100. 
 */