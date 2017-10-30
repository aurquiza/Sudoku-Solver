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
import java.util.ArrayList;
import java.util.List;

public class Grid extends GridLayout
{
	// instance variables
	private Candidate cand;
	private SubGrid gridSections[];
	private SubGrid sideBar;
	private String currentInput;
	private boolean checkCandidates;
	private GuiLayout gui;
	private List<String> updateBoard = new ArrayList<String>();

	//constructor
	// @Param row - how many rows the grid should have
	// @Param col - how many columns the grid should have
	// @Param hgap - what the horizontal gap should be between subgrids
	// @Param vgap - what the vertical gap should be between subgrids
	// @Param numSubGrids - how many sub-grids should the gridlayout contain
	public Grid(int row, int col, int hgap, int vgap, int numSubGrids, GuiLayout g)
	{
		super(row, col, hgap, vgap);
		gui = g;
		currentInput = " ";
		//create the specified number of sub-grids
		createSubGrids(numSubGrids);
		checkCandidates = false;

	 	cand = new Candidate(this);

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

	    //returns the size of the newly updated board
    public int returnUpdateBoard(){
      return updateBoard.size();
    }

    //returns the coordiantes of the board after it has been modified
    public String returnCoordsForUB(int index){
      List<String> storedBoard = updateBoard;

      return storedBoard.get(index);
    }

	// this is a flag that determines if the user is allowed to place candidates
	// outside the possible candidates
	public void setAllowed(boolean b)
	{
		checkCandidates = b;
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
	//returns the cells at a single subgrid
	public buttonClass[] getSubAtCellAt(int subgrid)
	{
		return gridSections[subgrid].getCells();
	}

	// checks if the user has finished filling in the puzzle
	public boolean checkFinish()
	{
		for(int i  = 0; i < 9; i ++)
		{
			buttonClass b[] = gridSections[i].getCells();

			for(int j = 0; j < 9; j++)
			{
				if(b[j].getText() == " ")
					return false;
			}
		}

		return true;
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



      //Checks where the button was pressed on the grid
      public int[] checkGrid(int pos)
      {

        //Switch statement for determning where the button was clicked
        switch(pos)
        {
          case 0: return (new int[]{0,0});
          case 1: return (new int[]{0,3});
          case 2: return (new int[]{0,6});
          case 3: return (new int[]{3,0});
          case 4: return (new int[]{3,3});
          case 5: return (new int[]{3,6});
          case 6: return (new int[]{6,0});
          case 7: return (new int[]{6,3});
          case 8: return (new int[]{6,6});
        }



        return null;
      }


      //Loop through the grid and find the button clicked
      public int [] loopThroughSubGrid(buttonClass[] input)
      { 

        //int array to store the x and y positions
        int [] pos = new int[]{0,0};

        //int array for the grid sections 
        int [] gridArray = checkGrid(input[0].getCellSection());

        //two d array delcared to help determine the x and y easier
        buttonClass [][] twoDArray = new buttonClass[3][3];


        //Turn the 1D array to a 2D array
        for(int i = 0; i < 3; i++)
        {


          twoDArray[0][i] = input[i];

        }

        for(int x = 3; x < 6; x++)
        {

          int index = x % 3;

          twoDArray[1][index] = input[x];


        }

        for(int z = 6; z < 9; z++)
        {



          int index = z % 3;
          twoDArray[2][index] = input[z];

        }


        //Loop through the grid 
        for(int i = 0; i < 3; i++){
          for(int x = 0; x < 3; x++){

            //If the button was found, update the X and Y positions
            if(twoDArray[i][x].getText() == currentInput){
              pos[0] = gridArray[0] + i + 1;
              pos[1] = gridArray[1] + x + 1;
            }
          }
        }

        //Return the int array with the positions
        return pos;

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
							// check what button was pressed
							buttonClass b = (buttonClass) event.getSource();

							// what option was currently chosen? if "?" then just show candidates
							if(getInput() == "?")
							{
								gui.setCandidateList(b.getCandidates());
							}
							else
							{
								boolean flag = true;
								// restore previous candidate
								String prev = b.getText();
								// set new value to the cell
								if(checkCandidates)
								{
									flag = evaluateUserChoice(b);
								}

								// is the user allowed to place that int?
								if(flag)
								{
									b.setCellValue(currentInput);
									// remove new value from the candidate list
									cand.removeCandidate(currentInput, getGridSection(), b);
									cand.restoreCandidate(prev, b);
									if(checkFinish())
									{
										JOptionPane.showMessageDialog( null,
				                  		"Congratulations on completing the puzzle!",
				                 		 "Congrats", JOptionPane.PLAIN_MESSAGE );
									}
								}
								else
								{
									JOptionPane.showMessageDialog( null,
			                  		"Invalid: not in the candidate list",
			                 		 "Error", JOptionPane.PLAIN_MESSAGE );
								}


					          	//button array to keep track of the board as the user interacts with the board
					            buttonClass [] trackBoard;

					            //get the sub grid section that was clicked
					            trackBoard = getSubAtCellAt(b.getCellSection());

					            //return the int array position with the X and Y positions
					            int [] positions = loopThroughSubGrid(trackBoard);

					            //return a string with all the info to put into a List of strings
					            String update = keepTrack(positions[0], positions[1], currentInput);
					            //Put the newly created string into the List of strings
					            appendBoard(update);
							}

						}
					}
				);
				add(cells[i]);
			}
		}


	//convert out 0-8 grid to the 1-9 grid
    public int convertXnY(int pos){

      switch(pos){

       case 1:
       return 0;
       case 2:
       return 1;
       case 3:
       return 2;
       case 4:
       return 0;
       case 5:
       return 1;
       case 6:
       return 2;
       case 7:
       return 0;
       case 8:
       return 1;
       case 9:
       return 2;


     }

     return -1;
   }


   //Convertrs the X, Y, and Current value into a string
    public String keepTrack(int x, int y, String curr)
  	{


     	//String input = Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(c);
   		String input =  Integer.toString(x) + " " + Integer.toString(y) + " " + curr;

    	return input;
  	}

 	//Adds the newly created string to the List of Strings
 	public void appendBoard(String input)
  	{

  	 	updateBoard.add(input);

	}

		// this finds if the button's candidate list will allow the
		// user to insert the current number they've chose
		public boolean evaluateUserChoice(buttonClass b)
		{
			String[] buttonCand = b.getCandidates();
			for(int i = 0; i < 9; i++)
			{
				if(buttonCand[i].equals(currentInput))
				{
					return true;
				}
			}

			return false;
		}

		// returns the subgrid section
		public int getGridSection()
		{
			return SubGridSection;
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
			cells[cells.length - 2].addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						setInput(" ");
						JOptionPane.showMessageDialog( null,
                  		"You are now in Clear Mode.",
                 		 "Clear Mode", JOptionPane.PLAIN_MESSAGE );
					}
				}

			);

			// special initialization for the "?" button
			cells[cells.length - 1] = new buttonClass("?", 11);
			add(cells[cells.length - 1]);
			cells[cells.length - 1].addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						buttonClass b = (buttonClass) event.getSource();
						setInput("?");
						JOptionPane.showMessageDialog( null,
                  		"You are now in Candidate List Display Mode.",
                 		 "Candidate List Display Mode", JOptionPane.PLAIN_MESSAGE );
					}
				}

			);
		}

		// getter method that returns array of cells
		public buttonClass[] getCells()
		{
			return cells;
		}
	}
}