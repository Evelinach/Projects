package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
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

public class ClockPanel extends JPanel implements ActionListener
{
	private final Font font = new Font("Ink Free", Font.BOLD, 20);
	private Color background;
	private Color clockColor;

	private int locationX = 100, locationY = 100;
	private int directionX = 1, directionY = 1;

	private int clockHeight;
	private int clockWidth;

	private Clock clock;
	private Timer animationTimer;

	public ClockPanel(Color background, Color clockColor)
	{
		this.background = background;
		this.clockColor = clockColor;

		clock = new Clock();

		// create a Swing Timer to update 60 Hz (1000/60)
		animationTimer = new Timer((1000 / 60), this);
		// set the initial delay to zero
		animationTimer.setInitialDelay(0);
		// start animation timer
		animationTimer.start();

	}

	private void updateClockSize(Graphics g)
	{
		// calculate the clock height and width
		clockHeight = g.getFontMetrics().getAscent();
		clockWidth = g.getFontMetrics().stringWidth(clock.getTime());
	}

	private void moveClock()
	{
		// define the boundaries
		int rightWall = this.getWidth() - clockWidth;
		int leftWall = 0;
		int topWall = 0 + clockHeight;
		int bottomWall = this.getHeight();

		// Calculate a new location
		locationX = locationX + directionX;
		locationY = locationY + directionY;

		// if ball hits left wall or the right wall, change the x direction
		if (locationX <= leftWall || locationX >= rightWall)
		{
			directionX = -directionX;
		}

		// if ball hits top or bottom walls, change the y direction
		if (locationY >= bottomWall || locationY <= topWall)
		{
			directionY = -directionY;
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		// draw the background color
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());

		// draw the clock
		g.setFont(font);
		updateClockSize(g);
		g.setColor(clockColor);
		g.drawString(clock.getTime(), locationX, locationY);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// call the moveClock() method to move the clock so it bounces off of
		// each wall when it hits
		moveClock();
		// repaint the ClockPanel to animate the clock
		this.repaint();

	}

}
