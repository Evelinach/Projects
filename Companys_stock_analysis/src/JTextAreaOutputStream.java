
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * @author Evelina Gritsay
 * @version 1.0
 * @see https://stackoverflow.com/questions/14706674/system-out-println-to-jtextarea
 * Example
 * The following example creates frame with text area and redirects System.out to it
 */

// JTextAreaOutputSream class redirects System.out to text area
public class JTextAreaOutputStream extends OutputStream
{
	private final JTextArea destination;

	public JTextAreaOutputStream(JTextArea destination)
	{
		if (destination == null)
			throw new IllegalArgumentException("Destination is null");

		this.destination = destination;
	}

	@Override
	public void write(byte[] buffer, int offset, int length) throws IOException
	{
		final String text = new String(buffer, offset, length);
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				destination.append(text);
			}
		});
	}

	@Override
	public void write(int b) throws IOException
	{
		write(new byte[] { (byte) b }, 0, 1);
	}

}