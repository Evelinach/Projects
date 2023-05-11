import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Anika Goel
 * @author Evelina Gritsay
 */

// Company class definition implements interface Analysable
public abstract class Company implements Analysable
{
	// private instance variables of class Company
	private String name;
	private String industry;
	private String sector;
	private double variance;
	protected double historical;
	private double[] stockPrices = new double[252];
	
	private double[] companyInfo = new double[4];
	private List<Double> stockPricesList = new ArrayList<>(252);
	
	public List<Double>  getStockPricesList()
	{
		return stockPricesList;
	}

	public void setCompanyInfo(double marketCap, double PERatio, double EPS,
			double FScore)
	{
		companyInfo[0] = marketCap;
		companyInfo[1] = PERatio;
		companyInfo[2] = EPS;
		companyInfo[3] = FScore;
	}

	public void readTestResults(String fileName)
	{
		File file = new File(fileName);
		Scanner input = null;
		int i = 0;
		try
		{
			// create a new Scanner object passing the file
			input = new Scanner(file);
			// check if the file has data

			while (input.hasNext())
			{
				// store the data in String line
				double stockPrice = input.nextDouble();
				stockPrices[i] = stockPrice;
				stockPricesList.add((double) stockPrice );
				// System.out.println(stockPrices[i]);

				i++;
			}
			// close input file
			input.close();
			historicalReturns(stockPrices[0],
					stockPrices[stockPrices.length - 1], 0, stockPrices.length);

		}
		// catch and handle exceptions printing error message to console output
		catch (FileNotFoundException exception)
		{
			System.out
					.println("ERROR: cannot open file or file does not exist.");
		}

	}

	public double[] getStockPrices()
	{
		return stockPrices;
	}

	// constructor passing parameter theName
	public Company(String theName)
	{
		name = theName;
		sector = "Technology";
		industry = "Semiconductor";
	}

	public String getName()
	{
		return this.name;
	}

	public void historicalAnalysis()
	{
		System.out.println(String.format("\n%20s %20s %20s", "Company:", "|",
				"Historical Analysis:"));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%20s %40s %5s", this.getName(),
				"Variance-", this.getVariance()));
		System.out.println(
				String.format("\n%65s %5s", "Historical Returns-", historical));

	}

	public void financialAnalysis()
	{
		System.out.println(String.format("\n%20s %20s %20s", "Company:", "|",
				"Financial Analysis:"));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%20s %40s %5s", this.getName(),
				"F-Score-", this.getFScore()));

	}

	public void statisticalAnalysis()
	{
		System.out.println(String.format("\n%20s %20s %20s", "Company:", "|",
				"Statistical Analysis:"));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%20s %40s %5s", this.getName(),
				"Market Cap-", this.getMarketCap()));
		System.out.println(String.format("\n%60s %5s", "EPS-", this.getEPS()));
		System.out.println(
				String.format("\n%60s %5s", "PE Ratio-", this.getPERatio()));

	}

	public void totalAnalysis(Company[] array)
	{
		System.out.println(String.format("\n%20s %20s %20s %20s %20s %20s %20s",
				"Company:", "|", "Historical Analysis", "|",
				"Financial Analysis", "|", "Statistical Analysis"));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------"
						+ "-------------------------------------------------------------------------------------------"));
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(String.format(
					"\n%20s %40s %2s %30s %2s %40s %2s", array[i].getName(),
					"Variance:", array[i].getVariance(), "F-Score:",
					array[i].getFScore(), "Market Cap:",
					array[i].getMarketCap()));
			System.out.println(String.format("\n%60s %2s %70s %2s",
					"Historical Returns:", array[i].getHistReturns(), "EPS:",
					array[i].getEPS()));
			System.out.println(String.format("\n%140s %2s", "PE Ratio:",
					array[i].getPERatio()));
			System.out.println(String.format("%s",
					"-------------------------------------------------------------"
							+ "-------------------------------------------------------------------------------------------"));
		}

	}

	public double getMarketCap()
	{
		return companyInfo[0];
	}

	public double getPERatio()
	{
		return companyInfo[1];
	}

	public double getEPS()
	{
		return companyInfo[2];
	}

	public double getFScore()
	{
		return companyInfo[3];
	}

	public void historicalReturns(double initialPrice, double finalPrice,
			int dividendIssued, int holdingPeriod)
	{
		historical = (finalPrice - initialPrice + dividendIssued)
				/ holdingPeriod;
	}

	public void variance(double[] stockPrice)
	{
		double sum = 0;
		double avg = 0;
		int length = stockPrice.length;

		for (int i = 0; i < length; i++)
		{
			sum += stockPrice[i];
		}
		avg = sum / stockPrice.length;

		for (int j = 0; j < length; j++)
		{
			sum += (stockPrice[j] - avg) / length;
		}
		variance = Math.sqrt(sum);

	}

	public double getHistReturns()
	{
		return historical;
	}

	public double getVariance()
	{
		variance(getStockPrices());
		return variance;
	}

	protected void completeAnalysis()
	{
		System.out.println(String.format("\n%20s %20s %20s", "Company:", "|",
				this.getName()));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%23s %40s %5s",
				"Historical Analysis:", "Variance:", this.getVariance()));
		System.out.println(
				String.format("\n%70s %5s", "Historical Returns:", historical));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%23s %40s %5s",
				"Financial Analysis:", "F-Score:", this.getFScore()));
		System.out.println(String.format("%s",
				"-------------------------------------------------------------------"
						+ "-------------------------------"));
		System.out.println(String.format("\n%25s %30s %5s",
				"Statistical Analysis:", "Market Cap:", this.getMarketCap()));
		System.out.println(String.format("\n%60s %5s", "EPS:", this.getEPS()));
		System.out.println(
				String.format("\n%60s %5s", "PE Ratio:", this.getPERatio()));

	}

	public String toString()
	{
		return "Company name: " + this.getName() + "\nCompany Sector: " + sector
				+ "\nCompany Industry: " + industry + "\nMarket cap: "
				+ getMarketCap() + "\nPE Ratio: " + getPERatio();
	}

}