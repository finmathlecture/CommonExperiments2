/*
 * (c) Copyright Christian P. Fries, Germany. All rights reserved. Contact: email@christianfries.com.
 *
 * Created on 12.10.2013
 */

package net.finmath.experiments.montecarlo;

import net.finmath.experiments.montecarlo.randomnumbers.HaltonSequence;

/**
 * A simple class illustrating a Monte-Carlo integration using a Halton sequence (low discrepancy sequence)
 * 
 * @author Christian Fries
 */
public class MonteCarloIntegrationWithQuasiRandomNumbersExperiment {


	/**
	 * Main program to run the experiment.
	 * 
	 * @param args Arguments, not used
	 */
	public static void main(String[] args) {
		int numberOfSimulations = 10000000;
		
		
		double pi = getMonteCarloApproximationOfPi(numberOfSimulations);
		System.out.println("Simulation with n = " + numberOfSimulations + " resulted in approximation of pi = " + pi);

		System.out.println("Approximation error is                        = " + Math.abs(pi-Math.PI));
		System.out.println("Theoretical order of the Monte-Carlo error is = " + 1.0/Math.sqrt(numberOfSimulations));
	}

	/**
	 * Calculates an approximation of pi via Monte-Carlo integration.
	 * 
	 * @param numberOfSimulations The number of elements to use from the random number sequence.
	 * @return An approximation of pi.
	 */
	public static double getMonteCarloApproximationOfPi(int numberOfSimulations) {
		long numberOfPointsInsideUnitCircle = 0;
		for(int i=0; i<numberOfSimulations; i++) {
			double x = 2.0 * (HaltonSequence.getHaltonNumber(i, 2) - 0.5);
			double y = 2.0 * (HaltonSequence.getHaltonNumber(i, 3) - 0.5);
			if(x*x + y*y < 1.0) numberOfPointsInsideUnitCircle++;
		}
		
		double areaOfUnitCircle = 4.0 * (double)numberOfPointsInsideUnitCircle / (double)numberOfSimulations;
		
		// The theoretical area of a circle is pi r^2. Hence we have:
		double pi = areaOfUnitCircle;

		return pi;
	}
}
