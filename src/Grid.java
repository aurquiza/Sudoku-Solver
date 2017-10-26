/*
   Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
   NetID: aurqui7, eleon23, jglebo2

   - This class consists of code that initializes a grid using the GridLayout class

   - The extensions made from the gridlayout class is that it now takes in another interger parameter
     that determines how many sub-grids the gridlayout should have

   - The grid is manually set to create 9 cells for each subgrid

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid extends GridLayout
{
	// instance variables
	private SubGrid gridSections[];
	private SubGrid sideBar;
	private String currentInput;

	//constructor
	// @Param row - how many rows the grid should have
	// @Param col - how many columns the grid should have
	// @Param hgap - what the horizontal gap should be between subgrids
	// @Param vgap - what the vertical gap should be between subgrids
	// @Param numSubGrids - how many sub-grids should the gridlayout contain
	public Grid(int row, int col, int hgap, int vgap, int numSubGrids)
	{
		super(row, col, hgap, vgap);
		currentInput = "-1";
		//create the specified number of sub-grids
		createSubGrids(numSubGrids);

		//sideBarLayout = new GridLayout(0,1);
		sideBar = new SubGrid(new GridLayout(0,1), true, 0, 11);
	}

	// create sub-grids using SubGrid class
	// @Param numSubGrids - the number of sub-grids that should be created
	private void createSubGrids(int numSubGrids)
	{	
		// create an array of object "SubGrids" determined by the parameter
		gridSections = new SubGrid [numSubGrids];

		// loop through the array initializing each Sub-Grid
		for(int i = 0; i < numSubGrids; i++)
		{	
			// initialize SubGrid
			GridLayout newGrid = new GridLayout(3,3,0,0);
			gridSections[i] = new SubGrid(newGrid, false, i, 9);
		}
	}

	// sets input for what the a button's string should be set to
	// when clicked on
	public void setInput(String val)
	{
		currentInput = val;
	}

	// gets input of the current value a button should be assigned to
	public String getInput()
	{
		return currentInput;
	}

	//get the side bar
	public SubGrid getSideBar()
	{
		return sideBar;
	}

	// gets a subgrid at the specified index
	public SubGrid getPanelAt(int index)
	{
		return gridSections[index];
	}

	// gets the size of the grid sections array
	public int getNumOfGrids()
	{
		return gridSections.length;
	}

	// getter method that returns an array of sub-grids
	public SubGrid[] getSubGrids()
	{
		return gridSections;
	}

	// getter method that acts like a 2d array to access buttons within the
	// subgrid class
	public buttonClass[] getSubAtCellAt(int subgrid)
	{

		return gridSections[subgrid].getCells();
	}

/*
	Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
	NetID: aurqui7, eleon23, jglebo2

	- This class consists of code that creates a JPanel that will hold a specified number of cells

	- The extension of the JPanel is used to add features such as an array of buttons that each will
	  hold information about a specific cell as well as an integer that specifies a sub-grid's number
*/
	private class SubGrid extends JPanel
	{
		// instance variables
		private int SubGridSection;
		private buttonClass cells[];

		// constructor
		// @Param grid - Layout manager that will be used for the JPanel
		// @Param flag - distinction between creating subgrid for the sudoku grid or input button grid
		// @Param section - number that will be given to the specified subgrid
		// @Param numOfCells - number of cells that should be in the sub-grid
		public SubGrid(GridLayout grid, boolean flag, int section, int numOfCells)
		{	
			// call super constructor
			super(grid);

			//assign grid number
			SubGridSection = section;
			cells = new buttonClass[numOfCells];

			// true, meaning create the input buttons for cells of the sudoku grid
			// false, meaning create sub-grid for the sudoku grid
			if(flag)
				createInputButtons();
			else
				createCells();
			

		}

		// create and add cells to the JPanel
		private void createCells()
		{
			//initialize each cell and add to the JPanel
			for(int i = 0; i < cells.length; i++)
			{
				cells[i] = new buttonClass(" ", SubGridSection);
				cells[i].addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							buttonClass b = (buttonClass) event.getSource();
							b.setCellValue(currentInput);
						}
					}
				);
				add(cells[i]);
			}
		}

		// create buttons for the sidebar of the gui
		private void createInputButtons()
		{	
			// loops through 9 of the 11 buttons created
			for(int i = 0; i < cells.length - 2; i++)
			{
				cells[i] = new buttonClass(Integer.toString(i + 1), i + 1);
				cells[i].addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							buttonClass b = (buttonClass) event.getSource();
							setInput(Integer.toString(b.getCellSection()));
						}
					}
				);
				add(cells[i]);
			}

			// special initialization for the "X" button
			cells[cells.length - 2] = new buttonClass("X", 10);
			add(cells[cells.length - 2]);

			// special initialization for the "?" button
			cells[cells.length - 1] = new buttonClass("?", 11);
			add(cells[cells.length - 1]);
		}

		// getter method that returns array of cells
		public buttonClass[] getCells()
		{
			return cells;
		}
	}
}