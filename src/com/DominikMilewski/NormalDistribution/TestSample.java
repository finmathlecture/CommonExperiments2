package com.DominikMilewski.NormalDistribution;

public class TestSample {

	public static void main(String[] args) {

		SampleNormalDistribution normal = new SampleNormalDistribution(10000000,100000, 1, 1);
		double[] gen = normal.DoGenerate();
		
		for(int k = 0;k<gen.length;k++){
		System.out.println(gen[k]);
		
	}

}
}