import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextArea;

/**
 * @author Anika Goel
 * @author Evelina Gritsay
 */
public class CompanyStockAnalysisView extends JFrame
{
	public JTextArea textArea;
	private JFrame window;
	private JButton jButton;
	public JRadioButton jRadioButton1;
	public JRadioButton jRadioButton2;
	public JRadioButton jRadioButton3;
	public JRadioButton jRadioButton4;
	public JRadioButton jRadioButton5;
	public JRadioButton jRadioButton6;
	public JRadioButton jRadioButton7;
	public JRadioButton jRadioButton8;
	public GraphPanel graph1;
	public GraphPanel graph2;
	public GraphPanel graph3;

	public CompanyStockAnalysisView()
	{
		// Window width in pixels
		final int WINDOW_WIDTH = 1800;
		// Window height in pixels
		final int WINDOW_HEIGHT = 1200;
		// Create a window
		window = new JFrame();

		// FlowLayout manager divides the window
		window.setLayout(new FlowLayout());
		// Set the title
		window.setTitle("Company's Stock Analysis");
		// Set the size of the window
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// Specify what happens when the close button is clicked
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Declaration of object of JRadioButton class
		jRadioButton1 = new JRadioButton();
		jRadioButton2 = new JRadioButton();
		jRadioButton3 = new JRadioButton();
		jRadioButton4 = new JRadioButton();
		jRadioButton5 = new JRadioButton();
		jRadioButton6 = new JRadioButton();
		jRadioButton7 = new JRadioButton();
		jRadioButton8 = new JRadioButton();

		// Declaration of object of JButton class and initialization of object
		// of JButton" class
		jButton = new JButton("Click");

		JLabel label = new JLabel(
				"Please select the company's stock symbol ('AMD' for AMD, 'NVDA' for Nvidia, or 'INTC' for Intel): ");

		// Declaration of object of ButtonGroup class and initialization of
		// object of ButtonGroup class.
		ButtonGroup G1 = new ButtonGroup();
		ButtonGroup G2 = new ButtonGroup();

		// setText(...) function is used to set text of radio button.
		// Setting text of jRadioButton
		jRadioButton1.setText("AMD");
		jRadioButton2.setText("NVDA");
		jRadioButton3.setText("INTC");
		jRadioButton4.setText("ALL Companies");

		jRadioButton5.setText("Historical");
		jRadioButton6.setText("Statistical");
		jRadioButton7.setText("Financial");
		jRadioButton8.setText("All Analysis");

		// Adding jRadioButtons on JFrame
		window.add(jRadioButton1);
		window.add(jRadioButton2);
		window.add(jRadioButton3);
		window.add(jRadioButton4);

		window.add(jRadioButton5);
		window.add(jRadioButton6);
		window.add(jRadioButton7);
		window.add(jRadioButton8);
		// Adding jRadioButtonsin a Button Group G1
		G1.add(jRadioButton1);
		G1.add(jRadioButton2);
		G1.add(jRadioButton3);
		G1.add(jRadioButton4);

		jRadioButton1.setSelected(true);
		// Adding jRadioButtonsin a Button Group G2
		G2.add(jRadioButton5);
		G2.add(jRadioButton6);
		G2.add(jRadioButton7);
		G2.add(jRadioButton8);
		jRadioButton5.setSelected(true);

		// create a text area, specifying the rows and columns
		textArea = new JTextArea(100, 100);
		textArea.setEditable(false);
		JTextAreaOutputStream out = new JTextAreaOutputStream(textArea);
		System.setOut(new PrintStream(out));

		// create a new FishingButtonListener passing the model, this
		// view and FishingButton
		RadioButtonListener radioActionListener = new RadioButtonListener(this);

		jRadioButton1.addActionListener(radioActionListener);
		jRadioButton2.addActionListener(radioActionListener);
		jRadioButton3.addActionListener(radioActionListener);
		jRadioButton4.addActionListener(radioActionListener);
		jRadioButton5.addActionListener(radioActionListener);
		jRadioButton6.addActionListener(radioActionListener);
		jRadioButton7.addActionListener(radioActionListener);
		jRadioButton8.addActionListener(radioActionListener);

		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		// p1.add(label);
		p1.add(jRadioButton1);
		p1.add(jRadioButton2);
		p1.add(jRadioButton3);
		p1.add(jRadioButton4);

		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(jRadioButton5);
		p2.add(jRadioButton6);
		p2.add(jRadioButton7);
		p2.add(jRadioButton8);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		p.add(textArea);

		List<Double> scores = new ArrayList<>();

		JLabel label1 = new JLabel("Graph for Company AMD: ");
		JLabel label2 = new JLabel("Graph for Company Nvidia: ");
		JLabel label3 = new JLabel("Graph for Company Intel: ");
		JLabel axisLabel1 = new JLabel("Y: Stock Price VS. X: Time");
		JLabel axisLabel2 = new JLabel("Y: Stock Price VS. X: Time");
		JLabel axisLabel3 = new JLabel("Y: Stock Price VS. X: Time");

		JPanel panelGraph1 = new JPanel();
		panelGraph1.setLayout(new BoxLayout(panelGraph1, BoxLayout.Y_AXIS));
		graph1 = new GraphPanel(scores);
		graph1.setPreferredSize(new Dimension(500, 300));
		panelGraph1.add(label1);
		panelGraph1.add(axisLabel1);
		panelGraph1.add(graph1);

		JPanel panelGraph2 = new JPanel();
		panelGraph2.setLayout(new BoxLayout(panelGraph2, BoxLayout.Y_AXIS));
		graph2 = new GraphPanel(scores);
		graph2.setPreferredSize(new Dimension(500, 300));

		panelGraph2.add(label2);
		panelGraph2.add(axisLabel2);
		panelGraph2.add(graph2);

		JPanel panelGraph3 = new JPanel();
		panelGraph3.setLayout(new BoxLayout(panelGraph3, BoxLayout.Y_AXIS));
		graph3 = new GraphPanel(scores);
		graph3.setPreferredSize(new Dimension(500, 300));

		panelGraph3.add(label3);
		panelGraph3.add(axisLabel3);
		panelGraph3.add(graph3);

		window.add(panelGraph1);
		window.add(panelGraph2);
		window.add(panelGraph3);
		window.add(label);
		window.add(p1);
		window.add(p2);
		window.add(p);

		// Display the window
		window.setVisible(true);

	}

	public static void main(String[] args)
	{
		// create a new instance of the CompanyStockAnalysisView
		new CompanyStockAnalysisView();
	}

}
