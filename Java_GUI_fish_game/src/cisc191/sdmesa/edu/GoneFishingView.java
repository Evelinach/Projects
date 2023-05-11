package cisc191.sdmesa.edu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * @author Evelina Gritsay
 * @version 1.0
 * @see Gaddis, T. (2015). Starting out with Java: From control structures
 *      through
 *      objects. Addison-Wesley.
 * 
 */
// GoneFishingView Class definition
public class GoneFishingView extends JFrame
{

	// private instance variables of the class GoneFishingView
	private JSlider triesRemainingSlider;
	private JSlider fishRemainingSlider;
	private GoneFishingModel model;
	private JFrame window;
	private int maxFish = 10;
	private int maxTries = 30;

	public GoneFishingView(GoneFishingModel model)
	{
		// assign parameter model private instance variable model
		this.model = model;
		// Window width in pixels
		final int WINDOW_WIDTH = 900;
		// Window height in pixels
		final int WINDOW_HEIGHT = 300;
		// Create a window
		window = new JFrame();
		window.setLayout(new FlowLayout());
		// Set the title
		window.setTitle("Gone Fishing");
		// Set the size of the window
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// Specify what happens when the close button is clicked
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a panel
		JPanel panel = new JPanel(new GridLayout(model.DIMENSION, model.DIMENSION));
		// for loops to button components to the panel
		for (int row = 1; row <= model.DIMENSION; row++)
		{
			for (int col = 1; col <= model.DIMENSION; col++)
			{
				// create a new fishingButton and pass the row and column
				FishingButton fishingButton = new FishingButton(row - 1,
						col - 1);
				// create a new FishingButtonListener passing the model, this
				// view and FishingButton
				FishingButtonListener listener = new FishingButtonListener(
						model, this, fishingButton);

				fishingButton.setText("?");
				// fishingButton.setSize(100, 200);
				fishingButton.addActionListener(listener);
				panel.add(fishingButton);

			}
		}

		// Create a label to display instructions. To reference a label
		JLabel messageLabel = new JLabel("Click on the buttons to fish");
		// Add the label to the panel
		window.add(messageLabel);
		// messageLabel.setVisible(true);
		// panel.setLocation(100, 0);
		// set the position, min values and max values of the slider objects
		triesRemainingSlider = new JSlider(JSlider.VERTICAL, 0, maxTries,
				model.getTriesRemaining());
		fishRemainingSlider = new JSlider(JSlider.VERTICAL, 0, maxFish,
				model.getFishRemaining());
		// set the slider object tick spaces and label attributes
		triesRemainingSlider.setMajorTickSpacing(10);
		fishRemainingSlider.setMajorTickSpacing(5);
		triesRemainingSlider.setPaintTicks(true);
		triesRemainingSlider.setPaintLabels(true);
		fishRemainingSlider.setPaintTicks(true);
		fishRemainingSlider.setPaintLabels(true);
		// create labels for the slider objects to display on the window
		JLabel triesLabel = new JLabel("Tries");
		JLabel fishLabel = new JLabel("Fish");
		// add panel, labels and sliders to the window
		window.add(panel);
		window.add(triesRemainingSlider);
		window.add(triesLabel);
		window.add(fishRemainingSlider);
		window.add(fishLabel);

		// Create a label to display the author
		JLabel messageAuthor = new JLabel("Programmed by: Evelina Gritsay");
		// Add the label to the window
		window.add(messageAuthor);

		pack();
		// Display the window
		window.setVisible(true);

	}

	public static void main(String[] args)
	{
		// create a new instance of the GoneFishingView passing the model as an
		// argument
		new GoneFishingView(new GoneFishingModel());
	}

	public void updateUI()
	{
		// update the value of the sliders for the fish remaining and tries
		// remaining in the model
		triesRemainingSlider.setValue(model.getTriesRemaining());
		fishRemainingSlider.setValue(model.getFishRemaining());

		// call the model fishWin method to return boolean and check if the fish
		// Won the game
		if (model.fishWin())
		{
			// Display a new message box showing Game Over - Fishes Win!
			JOptionPane.showMessageDialog(window, "Game Over - Fishes Win!");
		}
		// call the model fishWin method to return boolean and check if the fish
		// the player the game
		if (model.playerWins())
		{
			// Display a new message box showing Game Over - You Win!
			JOptionPane.showMessageDialog(window, "Game Over - You Win!");
		}
	}

}
