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
	int cellSection;
	int candidates[];
	boolean immortal = false;
	// constructor
	// @Param s - string that will be displayed on the gui
	// @Param cellSec - what position the cell belongs to in a subgrid
	public buttonClass(String s, int cellSec)
	{
		super(s);
		cellSection = cellSec;
	}

	public int getCellSection()
	{
		return cellSection;
	}

	public void setCellValue(String val)
	{
		setText(val);
	}

	public void setImmortal(boolean x){
		immortal = x;
	}

}