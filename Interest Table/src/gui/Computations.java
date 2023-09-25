package gui;

public class Computations extends InterestTableGUI 
{
	
	
	static public double calcSimpleInterest(double rate, double principal, double years)
	{
		double simpleInterest = principal + (principal * (rate/100)*years);
		return simpleInterest;
	}
	
	static public double calcCompoundInterest(double rate, double principal, double years)
	{
		double compoundInterest = principal * Math.pow((1 + rate/100),years);
		return compoundInterest;
	}

}
