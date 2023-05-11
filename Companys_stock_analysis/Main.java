import java.util.Scanner;

class Main {

  public static void main(String[] args) {
    // getting user input for company to analyze and which analysis
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the company's stock symbol ('AMD' for AMD, 'NVDA' for Nvidia, or 'INTC' for Intel): ");
		String company = scanner.next();
		company = company.toUpperCase();
		
		Company[] companyArray = new Company[3];
		boolean flag = false;

		//switch-case for creating company object
		switch (company)
		{
			case "AMD":
				Company advancedMicroDevices = new AMD();
				companyArray[0] = advancedMicroDevices;
				break;
			case "NVDA":
				Company nvidia = new Nvidia();
				companyArray[0] = nvidia;
				break;
			case "INTC":
				Company intel = new Intel();
				companyArray[0] = intel;
				break;
			default:
				// if none of options are selected, we do an analysis for all three companies
				System.out.println("None of the above options have been chosen. Doing an analysis for all three companies.");
				Company amd = new AMD();
				companyArray[0] = amd;
				Company nvda = new Nvidia();
				companyArray[1] = nvda;
				Company intc = new Intel();	
				companyArray[2] = intc;
				flag = true;
				break;
		}
		
		// as long as the user has not entered an invalid company option, 
		// the program will go through a switch-case for the analysis selection
		if (flag == false)
		{
			System.out.println("Please enter the analysis you want to do (H for historical, S for statistical, or F for financial): ");
			String analysis = scanner.next();
			analysis = analysis.toUpperCase();
			
			switch (analysis)
			{
				case "H":
					companyArray[0].historicalAnalysis();
					break;
				case "F":
					companyArray[0].financialAnalysis();
					break;
				case "S":
					companyArray[0].statisticalAnalysis();
					break;
				default:
					// if the user enters an invalid analysis, the 
					// program will do a complete analysis on the selected company
					companyArray[0].completeAnalysis();
					break;
			}
		}
		else {
			companyArray[0].totalAnalysis(companyArray);
		}
		
		System.out.println(companyArray[0].toString());
	}
}