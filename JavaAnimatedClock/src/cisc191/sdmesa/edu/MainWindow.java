package cisc191.sdmesa.edu;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * @author Tasha Frankie
 * @author Allan Schougaard
 * @otherContributors: None
 * @version 1.0
 * @see Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented
 *      Problem Solving.
 *      https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 */

public class MainWindow extends JFrame
{
	private ClockPanel clockPanel;

	public MainWindow()
	{
		setSize(500, 500);
		clockPanel = new ClockPanel(Color.BLACK, Color.GREEN);
		add(clockPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new MainWindow();
	}
}
