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
 	import javax.swing.JFileChooser;
 	import java.io.File; 


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
 			mainMenu = new MenuClass(grid, this);

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
 			splitInput(fullBoard);

		// set the size and visibility
 			setSize(770,600);
 			setVisible(true);

 		}

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

 		public void clearBoard()
 		{
 			buttonClass[] clearArr = null;

 			for(int x = 0; x < 9; x++){
 				clearArr = grid.getSubAtCellAt(x);
 				for(int i = 0; i < 9; i++){

 					clearArr[i].setText(" ");
 					clearArr[i].setImmortal(false);
 				}
 			}
 		}

 		public boolean checkPuzzle(int x, int y, int z){

 			if(x <= 0 || x > 9){
 				return false;
 			}
 			if(y <= 0 || y > 9){
 				return false;
 			}
 			if(z <= 0 || z > 9){
 				return false;
 			}

 			return true;

 		}

 		public  void displayLoadedBoard(int a, int b, int c){
 			if(!(checkPuzzle(a, b, c))){
 				System.err.println("INVALID INPUT BRUH " + a + " " + b + " : " + c);
 				return;
 			}

 			buttonClass[] buttonArray = null;

 			buttonClass[][] twoDArray = new buttonClass[3][3];

 			boolean check = checkPuzzle(a, b, c);

 			
 			//For top 3 grids
 				if((a >= 0 && a <= 3) && (b >= 0 && b <= 3)){
 					buttonArray = grid.getSubAtCellAt(0);
 				}
 				else if((a >= 0 && a <= 3) && (b >= 4 && b <= 6)){
 					buttonArray = grid.getSubAtCellAt(1);
 				}
 				else if((a >= 0 && a <= 3) && (b >= 7 && b <= 9)){
 					buttonArray = grid.getSubAtCellAt(2);
 				}

 			//Middle grids
 				else if((a >= 4 && a <= 6) && (b >= 0 && b <= 3)){
 					buttonArray = grid.getSubAtCellAt(3);

 				}

 				else if((a >= 4 && a <= 6) && (b >= 4 && b <= 6)){
 					buttonArray = grid.getSubAtCellAt(4);

 				}

 				else if((a >= 4 && a <= 6) && (b >= 7 && b <= 9)){
 					buttonArray = grid.getSubAtCellAt(5);

 				}

 			//bottom grids

 				else if((a >= 7 && a <= 9) && (b >= 0 && b <= 3)){
 					buttonArray = grid.getSubAtCellAt(6);
 				}
 				else if((a >= 7 && a <= 9) && (b >= 4 && b <= 6)){
 					buttonArray = grid.getSubAtCellAt(7);
 				}

 				else if((a >= 7 && a <= 9) && (b >= 7 && b <=9)){
 					buttonArray = grid.getSubAtCellAt(8);
 				}


 				int xPos = 0;
 				int yPos = 0;


 				xPos = convertXnY(a);
 				yPos = convertXnY(b);




 				for(int i = 0; i < 3; i++)
 				{


 					twoDArray[0][i] = buttonArray[i];

 				}

 				for(int x = 3; x < 6; x++)
 				{

 					int index = x % 3;

 					twoDArray[1][index] = buttonArray[x];


 				}

 				for(int z = 6; z < 9; z++)
 				{



 					int index = z % 3;
 					twoDArray[2][index] = buttonArray[z];

 				}

 				twoDArray[xPos][yPos].setText(Integer.toString(c)); 

 				twoDArray[xPos][yPos].setImmortal(true);
 			
 		}

	//Spliting the input from the file, splits one line at a time
 		public  void splitInput(List<String> board){

		// buttonClass[] buttonArray;
		// buttonArray = grid.getSubAtCellAt(0);

		// buttonArray[0].setText("1");
		// buttonArray[1].setText("2");
		// buttonArray[2].setText("3");
		// buttonArray[3].setText("4");
		// buttonArray[4].setText("5");
		// buttonArray[5].setText("6");
		// buttonArray[6].setText("7");
		// buttonArray[7].setText("8");
		// buttonArray[8].setText("9");


 			String[] tempArray;

 			int[] boardArray = new int[3];

 			for(int i = 0; i < board.size(); i++){

 				tempArray = board.get(i).split(" ");;
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
 			public static List<String> checkAndExtractInput(String args)
 			{

		//System.out.println("THIS WORKKKSSS");
		// initialize variables that will hold info for potential text file
 				String filename = null;
 				List<String> fileInput = new ArrayList<String>();

		// verify if user input has at least 1 argument (meaning user passed text file argument)
		// if (args > 0)
		// {
			// get file name and initialize a File object to prepare for extraction of data
 				filename = args;
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
		// }

		// // return null if there is no text file passed
		// return null;
 			}


 			public static void main(String[] args) throws Exception 
 			{

		// initialize gui application
 				GuiLayout app = new GuiLayout(checkAndExtractInput(String.join("", args)));
 				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 			}
 		}