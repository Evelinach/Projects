/**
 * @author Anika Goel
 * @author Evelina Gritsay
 */

public interface Analysable
{

	void historicalReturns(double initialPrice, double finalPrice,
			int dividendIssued, int holdingPeriod);

	void variance(double[] stockPrice);

	void historicalAnalysis();

	void financialAnalysis();

	void statisticalAnalysis();

}
