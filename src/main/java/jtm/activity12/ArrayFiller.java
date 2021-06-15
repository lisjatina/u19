package jtm.activity12;

import java.util.Random;

import static jtm.activity12.ArrayFillerManager.array;

public class ArrayFiller implements Runnable {

	int latency; // required latency time (in miliseconds) to simulate real
					// environment
	int minValue, maxValue; // min and max allowed values for array cells
	int from, to; // range which should be filled by this filler
	Random random; // Pseudo-random generator

	public ArrayFiller(int latency, int minValue, int maxValue) {
		// from this constructor call another constructor with more
		// parameters and fill missing
		// values with fixed literals
		this(latency,  minValue,  maxValue, 0, array.length -1);
	}

	public ArrayFiller(int latency, int minValue, int maxValue, int from, int to) {
	this.latency = latency;
	this.minValue = minValue;
	this.maxValue = maxValue;
	this.to = to;
	this.from = from;
	random = new Random();
	}

	@Override
	public void run() {
		// when invoked, put filler to sleep for required amount of latency
		// then fill ArrayFillerManager.array from..to cells with random values
		// in
		// minValue..maxValue range
		try {
			Thread.sleep(latency);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = from; i <=to ; i++) {
		array[i] = random.nextInt(maxValue - minValue + 1) + minValue;
		}
	}
}
