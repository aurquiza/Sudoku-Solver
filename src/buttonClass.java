/*
   Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
   NetID: aurqui7, eleon23, jglebo2

   - button class that will represent a single "cell" in the puzzle

   - The extension of the JButton adds what position a cell belongs to in the sub-grid as
     well as the potential candidates the cell contains

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class buttonClass extends JButton
{
	//instance variables
	private int cellSection;
	private String candidates[];
	private boolean immortal = false;

	// constructor
	// @Param s - string that will be displayed on the gui
	// @Param cellSec - what position the cell belongs to in a subgrid
	public buttonClass(String s, int cellSec)
	{
		super(s);
		cellSection = cellSec;
		candidates = new String[9];
		for(int i = 0; i < 9; i++)
		{
			candidates[i] = Integer.toString(i + 1);
		}
	}

	// returns the cell section for the button
	public int getCellSection()
	{
		return cellSection;
	}

	// sets the cell's value unless the immortal flag is set
	public void setCellValue(String val)
	{
		if(!immortal)
		{
			setText(val);
		}
		
	}

	// this determines if changes are allowed to be made to the cell
	public void setImmortal(boolean x)
	{
		immortal = x;
	}

	// check if the cell can be modified
	public boolean getImmortal()
	{
		return immortal;
	}

	// print out candidates the cell currently has
	public void showCandidates()
	{
		System.out.print("Candidates: ");
		for(int i = 0; i < 9; i++)
		{
			System.out.print(candidates[i] + " ");
		}
		System.out.println();
	}

	// return the candidates the cell currently has
	public String[] getCandidates()
	{
		return candidates;
	}

	// adds a specific candidate to the list of candidates for a cell
	public void addCandidate(String c)
	{
		if(c != " ")
			candidates[Integer.parseInt(c) - 1] = c;
	}

	// reset the candidate list to have all candidates ranging from 1 to 9
	public void resetCellCands()
	{
		candidates = new String[]{"1","2","3","4","5","6","7","8","9"};
	}
}