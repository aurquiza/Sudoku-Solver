/*
	CS 342 Programming Project 3: Sudoku-Solver

	Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
	NetID: aurqui7, eleon23, jglebo2
	
	- This program is a gui simulation of the game Sudoku, where the user must fill in numbers for each cell in a subgrid
 	  in an order where each subgrid contains one single unique number in that column, row and subgrid.

 	- The GuiLayout class consists of the code to initialize the gui as well as the main static method.

 	- The user will play sudoku and be given numerous options and info such as inputing their own puzzle or saving a current one onto a text file

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class GuiLayout extends JFrame 
{

	// instance variables to help initialize all necessary aspects of the gui
	private Container mainContainer;
	private MenuClass mainMenu;
	private Grid grid;
	private JMenuBar bar;
	private List<String> fullBoard = new ArrayList<String>();

	private JPanel sudokuGrid; 
	private JPanel masterPanel;
	private JPanel inputButtons;


	public GuiLayout(List<String> input)
	{
		super("CS 342 Sudoku-Solver");
		//create new grid that will create 9 subgrids and the sidebar
		grid = new Grid(3,3,7,7,9);
		//create menus that will hold the different options the user can execute
		mainMenu = new MenuClass(grid);

		//create the master panel as well as the sub-panels that
		//will added to the master panel
		masterPanel = new JPanel(new BorderLayout());
		sudokuGrid = new JPanel(grid);
		inputButtons = new JPanel(new GridLayout());
		inputButtons.add(grid.getSideBar());

		// add sub-panels to the master panel
		masterPanel.add(sudokuGrid, BorderLayout.WEST);
		masterPanel.add(inputButtons, BorderLayout.EAST);

		// have the container hold information about what is on the content pane
		// as well as add the master panel to the content pane
		mainContainer = getContentPane();
		mainContainer.add(masterPanel);
		initSubGrids();
		
		// create the menu bar and add the required menus to it
		bar = new JMenuBar();
		setJMenuBar(bar);
		addMenus();

		// store input file into an arraylist 
		fullBoard  = input;
		splitInput();
		
		// set the size and visibility
		setSize(770,600);
		setVisible(true);

	}

	public void displayLoadedBoard(int a, int b, int c){

		int x = a;
		int y = b;
		int number = c;

		System.out.println("coordinates: " + x + ", " + y + " Number: " + number );
		

		
	}

	//Spliting the input from the file, splits one line at a time
	public void splitInput(){


		String[] tempArray;

		int[] boardArray = new int[3];

		for(int i = 0; i < fullBoard.size(); i++){

			tempArray = fullBoard.get(i).split(" ");;
			boardArray = new int[tempArray.length];

				
			boardArray[0] = Integer.parseInt(tempArray[0]);
			boardArray[1] = Integer.parseInt(tempArray[1]);
			boardArray[2] = Integer.parseInt(tempArray[2]);

		
			displayLoadedBoard(boardArray[0], boardArray[1], boardArray[2]);
			System.out.println();
			
		}
	}

	// print array list to verify if input was stored
	public void printInputList()
	{
		if( fullBoard != null)
			for(int i = 0; i < fullBoard.size(); i++)
				System.out.println(fullBoard.get(i));
	}

	// add menus to the menu bar
	private void addMenus()
	{
		bar.add(mainMenu.getFileMenu());
		bar.add(mainMenu.getHelpMenu());
		bar.add(mainMenu.getHintMenu());
	}

	// add subgrids created by calling the Grid class
	public void initSubGrids()
	{
		// add each subgrid to the contentpane
		for(int i = 0; i < grid.getNumOfGrids(); i++)
			sudokuGrid.add(grid.getPanelAt(i));
	}

	// check for input of a text file on the command line and store data into an array list if there is
	// @Param args - input from the command line
	public static List<String> checkAndExtractInput(String[] args)
	{
		// initialize variables that will hold info for potential text file
		String filename = null;
		List<String> fileInput = new ArrayList<String>();

		// verify if user input has at least 1 argument (meaning user passed text file argument)
		if (args.length > 0)
		{
			// get file name and initialize a File object to prepare for extraction of data
			filename = args[0];
			File file = new File(filename);
			System.out.println(filename);

			//try and catch statement that checks if file exists
			try
			{
				// create buffered reader object
				FileReader fileReader = new FileReader(filename);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				// initialize string to grab one line at a time from text file
				String line = null;

				// add each line in the text file to the array list
				while ((line = bufferedReader.readLine()) != null) 
					fileInput.add(line);

				// close buffered reader
				bufferedReader.close();

			}
			catch(FileNotFoundException e)
			{
				//exit if file was not found
				System.out.println("ERROR: FILE NOT FOUND BRUHH");
				System.exit(0);
			}
			catch(IOException e)
			{
				System.exit(0);
			}

			return fileInput;
		}

		// return null if there is no text file passed
		return null;
	}


	public static void main(String[] args) throws Exception 
	{
		// initialize gui application
		GuiLayout app = new GuiLayout(checkAndExtractInput(args));
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}