import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIProject extends JFrame implements ActionListener
{
	final int FRAME_WIDTH = 500;
	final int FRAME_HEIGHT = 90;
	JButton button1 = new JButton("Increment");
	JButton button2 = new JButton("Decrement");
	JTextField result = new JTextField(8);

	public GUIProject()
	{
		super("Increment Decrement");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);

		setLayout(new FlowLayout());
		result.setText("0");

		add(button1);
		add(result);
		add(button2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object widget = event.getSource();

		if (widget == button1)
		{

			int input = Integer.parseInt(result.getText());
			++input;
			result.setText("" + input);
		}
		if (widget == button2)
		{

			int input = Integer.parseInt(result.getText());
			--input;
			result.setText("" + input);
		}

	}

	public static void main(String[] args)
	{
		GUIProject frame = new GUIProject();
		frame.setVisible(true);

	}
}
