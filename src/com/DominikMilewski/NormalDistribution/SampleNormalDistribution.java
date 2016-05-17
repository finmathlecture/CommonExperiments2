package com.DominikMilewski.NormalDistribution;
import java.util.*;

public class SampleNormalDistribution {
	static int n;
	static double mu;
	static double sigma;
	static int maxit;
	
	//Constructor with maximal iteration number, needed number of normal random numbers, mean and variance
	public SampleNormalDistribution(int maxit,int n,double mean,double var){
		this.n = n;
		this.mu = mean;
		this.sigma = var;
		this.maxit = maxit;
	}
	
//***Main Method to generate normal random numbers with the Acceptance-Rejection Method********************************	
public static double[] DoGenerate(){
	
	double[] Exp = GenerateExp(maxit,1.0);
	double[] Uni = GenerateUniform(maxit);
	double[] tempres = new double[maxit];
	int numberofaccept=0;
	int numberofrej =0;
	for(int i=0;i<maxit;i++){
		
		if(numberofaccept >= n){
			break;
		}
		
		double temp = AcceptReject(Uni[i], Exp[i]);
			if(temp==-1){
				tempres[numberofaccept]=temp;
				numberofrej+=1;}
			else{
				tempres[numberofaccept]=temp;
				numberofaccept+=1;
			}	
	}
	
	double[] res = new double[numberofaccept];
	int k=0;
	for(int i=0;i<tempres.length;i++){		
		if(tempres[i]>0){
			res[k]=tempres[i];
			k=k+1;
		}
	}
	double[] ber = GenerateBernouli(numberofaccept);
	for(int i=0;i<res.length;i++){
		if(ber[i]==0){
			res[i] = mu + Math.sqrt(sigma)*res[i];
		}
		else if(ber[i] == 1){
			res[i] = -res[i];
			res[i] = mu+Math.sqrt(sigma)*res[i];
		}
		
	}

  return res;
}
//********************************************************************************************

	
//************Draw exponential random numbers****************
public static double[] GenerateExp(int n,double lambda){
		
		double[] tempExp = new double[n];
		for(int i=0;i<n;i++){
			double temp = Math.random();
			tempExp[i] = (-1.0/lambda)*Math.log(1-temp);
		}
		
		return tempExp;
}

//************Draw Bernoulli distributed random numbers****************
public static double[] GenerateBernouli(int n){
	
	double[] tempBer = new double[n];
	for(int i=0;i<n;i++){
		Random rand = new Random();
		tempBer[i] = rand.nextInt(2);
	}
	return tempBer;
	
}
//************Draw uniform distributed random numbers on [0,1] ****************
public static double[] GenerateUniform(int n){
	double[] tempBer = new double[n];
	for(int i=0;i<n;i++){
		Random rand = new Random();
		tempBer[i] = rand.nextDouble();
	}
	return tempBer;
	
}
	
//Check if the condition u <= f/C*g is satisfied and accept or reject the generated number
public static double AcceptReject(double Uniform,double exp){
	
	if(Uniform < Math.exp(-0.5*(exp-1)*(exp-1))){
		
		return exp;
	}
	else
		return -1.0;


}
}
