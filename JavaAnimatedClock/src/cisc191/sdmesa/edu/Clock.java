package cisc191.sdmesa.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author Tasha Frankie
 * @author Allan Schougaard
 * @author Student Evelina Gritsay updated TODO sections
 * @otherContributors: None
 * @version 1.0
 * @see Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented
 *      Problem Solving.
 *      https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 */

public class Clock implements ActionListener
{
	private int counter = 0;
	private Timer timer;

	public Clock()
	{
		// create Swing Timer to update each second
		timer = new Timer(1000, this);
		// set the initial delay to zero
		timer.setInitialDelay(0);
		timer.start();

	}

	public String getTime()
	{
		String minutes = counter / 60 > 9 ? "" + counter / 60
				: "0" + counter / 60;
		String seconds = counter % 60 > 9 ? "" + counter % 60
				: "0" + counter % 60;
		return minutes + ":" + seconds;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// update counter
		this.counter++;

	}

}
