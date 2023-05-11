package cisc191.sdmesa.edu;

import javax.swing.JButton;

/**
 * @author Evelina Gritsay
 * @version 1.0
 * @see Gaddis, T. (2015). Starting out with Java: From control structures
 *      through
 *      objects. Addison-Wesley.
 * 
 */

// FishingButton Class definition inherits from JButton
public class FishingButton extends JButton
{
	// private instance variables for row and column for the FishingBottom
	private int row;
	private int column;

	// FishingButton constructor with parameter row and column
	public FishingButton(int row, int column)
	{
		// initialize the text of the FishingButton to a question mark
		this.setText("?");
		// initialize the row and column of the FishingButton
		this.row = row;
		this.column = column;
	}

	// getter method getRow to return this FishingButton's row
	public int getRow()
	{
		return row;
	}

	// getter method getColumn to return this FishingButton's column
	public Integer getColumn()
	{
		return column;
	}

}
