
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;

/**
 * @author Evelina Gritsay
 * @version 1.0
 * @see Gaddis, T. (2015). Starting out with Java: From control structures
 *      through
 *      objects. Addison-Wesley.
 * 
 */

// RadioButtonListener class definition inherits from RuntimeException and
// implements ActionListener
public class RadioButtonListener extends RuntimeException
		implements ActionListener
{
	// private instance variables
	private CompanyStockAnalysisView companyStockAnalysisView;

	public RadioButtonListener(
			CompanyStockAnalysisView companyStockAnalysisView)
	{
		// initialize the parameters to the private instance variables of this
		// object
		this.companyStockAnalysisView = companyStockAnalysisView;

	}

	// override actionPerformed method of the ActionListener Class
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		List<Double> scores = new ArrayList<>();
		try
		{

			companyStockAnalysisView.textArea.setText("");
			AbstractButton aButton = (AbstractButton) actionEvent.getSource();
			System.out.println("Selected: " + aButton.getText());

			String company = aButton.getText();
			company = company.toUpperCase();

			Company[] companyArray = new Company[3];
			boolean flag = false;

			if (companyStockAnalysisView.jRadioButton1.isSelected())
			{
				Company advancedMicroDevices = new AMD();
				advancedMicroDevices
						.readTestResults("amdHistoricalStockPrice.txt");
				companyArray[0] = advancedMicroDevices;
				companyStockAnalysisView.graph1
						.setScores(advancedMicroDevices.getStockPricesList());
				companyStockAnalysisView.graph2
				.setScores(scores);
				companyStockAnalysisView.graph3
				.setScores(scores);
			}

			if (companyStockAnalysisView.jRadioButton2.isSelected())
			{
				Company nvidia = new Nvidia();
				nvidia.readTestResults("nvdaHistoricalStockPrice.txt");
				companyArray[0] = nvidia;
				companyStockAnalysisView.graph2
						.setScores(nvidia.getStockPricesList());
				companyStockAnalysisView.graph1
				.setScores(scores);
				companyStockAnalysisView.graph3
				.setScores(scores);
			}

			if (companyStockAnalysisView.jRadioButton3.isSelected())
			{
				Company intel = new Intel();
				intel.readTestResults("intcHistoricalStockPrice.txt");
				companyArray[0] = intel;
				companyStockAnalysisView.graph3
						.setScores(intel.getStockPricesList());
				companyStockAnalysisView.graph1
				.setScores(scores);
				companyStockAnalysisView.graph2
				.setScores(scores);

			}
			// the program will do a complete analysis on the selected company
			if (companyStockAnalysisView.jRadioButton4.isSelected())
			{
				// if none of options are selected, we do an analysis
				// for all
				// three companies
				System.out
						.println("Doing an analysis for all three companies.");
				Company amd = new AMD();
				amd.readTestResults("amdHistoricalStockPrice.txt");
				companyArray[0] = amd;
				Company nvda = new Nvidia();
				nvda.readTestResults("nvdaHistoricalStockPrice.txt");

				companyArray[1] = nvda;
				Company intc = new Intel();
				intc.readTestResults("intcHistoricalStockPrice.txt");
				companyStockAnalysisView.graph1
				.setScores(amd.getStockPricesList());
				companyStockAnalysisView.graph2
				.setScores(nvda.getStockPricesList());
				companyStockAnalysisView.graph3
				.setScores(intc.getStockPricesList());

				companyArray[2] = intc;
				flag = true;

			}

			// as long as the user has not entered an invalid company
			// option,
			// the program will go through a switch-case for the analysis
			// selection
			if (flag == false)
			{
				System.out.println(
						"Please select the analysis you want to do (Historical, Statistical, or Financial or, All Analysis): ");

				if (companyStockAnalysisView.jRadioButton5.isSelected())
				{
					companyArray[0].historicalAnalysis();
				}

				if (companyStockAnalysisView.jRadioButton6.isSelected())
				{
					companyArray[0].statisticalAnalysis();
				}

				if (companyStockAnalysisView.jRadioButton7.isSelected())
				{
					companyArray[0].financialAnalysis();
				}
				// the program will do a complete analysis on the selected
				// company
				if (companyStockAnalysisView.jRadioButton8.isSelected())
				{
					companyArray[0].completeAnalysis();
				}
			}
			else
			{

				if (companyStockAnalysisView.jRadioButton5.isSelected())
				{

					if (companyStockAnalysisView.jRadioButton4.isSelected())
					{
						for (int i = 0; i < companyArray.length; i++)
						{
							companyArray[i].historicalAnalysis();
						}
					}
					else
					{
						Company advancedMicroDevices = new AMD();
						advancedMicroDevices
								.readTestResults("amdHistoricalStockPrice.txt");
						companyArray[0] = advancedMicroDevices;
						companyArray[0].historicalAnalysis();

					}
				}

				if (companyStockAnalysisView.jRadioButton6.isSelected())
				{

					if (companyStockAnalysisView.jRadioButton4.isSelected())
					{
						for (int i = 0; i < companyArray.length; i++)
						{
							companyArray[i].statisticalAnalysis();
						}
					}
					else
					{
						Company nvidia = new Nvidia();
						nvidia.readTestResults("nvdaHistoricalStockPrice.txt");
						companyArray[0] = nvidia;
						companyArray[0].statisticalAnalysis();

					}
				}

				if (companyStockAnalysisView.jRadioButton7.isSelected())
				{

					if (companyStockAnalysisView.jRadioButton4.isSelected())
					{
						for (int i = 0; i < companyArray.length; i++)
						{
							companyArray[i].financialAnalysis();
						}
					}
					else
					{
						Company intel = new Intel();
						intel.readTestResults("intcHistoricalStockPrice.txt");
						companyArray[0] = intel;
						companyArray[0].financialAnalysis();

					}
				}
				// the program will do a complete analysis on the selected
				// company
				if (companyStockAnalysisView.jRadioButton8.isSelected())
				{
					// System.out.println(
					// "Doing an analysis for all three companies.");
					Company amd = new AMD();
					amd.readTestResults("amdHistoricalStockPrice.txt");
					companyArray[0] = amd;
					Company nvda = new Nvidia();
					nvda.readTestResults("nvdaHistoricalStockPrice.txt");

					companyArray[1] = nvda;
					Company intc = new Intel();
					intc.readTestResults("intcHistoricalStockPrice.txt");

					companyArray[2] = intc;
					companyArray[0].totalAnalysis(companyArray);
					// companyArray[0].completeAnalysis();
				}

			}

			System.out.println(companyArray[0].toString());

		}
		// catch and handle exceptions
		catch (Exception exception)
		{
			// print out runtime errors
			System.out.println(exception.getMessage());
		}

	}

}
