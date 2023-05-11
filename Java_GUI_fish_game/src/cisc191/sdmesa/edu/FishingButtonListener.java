package cisc191.sdmesa.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Evelina Gritsay
 * @version 1.0
 * @see Gaddis, T. (2015). Starting out with Java: From control structures
 *      through
 *      objects. Addison-Wesley.
 * 
 */

// FishingButtonLister class definition inherits from RuntimeException and
// implements ActionListener
public class FishingButtonListener extends RuntimeException
		implements ActionListener
{
	// private instance variables for the Model, View and fishingButton
	private GoneFishingModel goneFishingModel;
	private GoneFishingView goneFishingView;
	private FishingButton fishingButton;

	public FishingButtonListener(GoneFishingModel goneFishingModel,
			GoneFishingView goneFishingView, FishingButton fishingButton)
	{
		// initialize the parameters to the private instance variables of this
		// object
		this.goneFishingModel = goneFishingModel;
		this.goneFishingView = goneFishingView;
		this.fishingButton = fishingButton;
	}

	// override actionPerformed method of the ActionListener Class
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			// call the model fishAt method passing the row and column of the
			// button clicked and return a boolean to determine if this button
			// contained a fish
			boolean isFish = goneFishingModel.fishAt(fishingButton.getRow(),
					fishingButton.getColumn());
			// if the button contains a fish
			if (isFish)
			{
				// set the test of the button to fish
				fishingButton.setText("Fish");
			}
			else
			{
				// set the test of the button to X denoting that there is no
				// fish in this button
				fishingButton.setText("X");
			}
			// display the button from being clicked on again after it was
			// already clicked on
			fishingButton.setEnabled(false);
			// call the goneFishingView method updateUI which will update the
			// slider's value based on the changes in the model
			goneFishingView.updateUI();

		}
		// catch and handle exceptions
		catch (Exception exception)
		{
			// print out runtime errors
			System.out.println(exception.getMessage());
		}

	}

}
