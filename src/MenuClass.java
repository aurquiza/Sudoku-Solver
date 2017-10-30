/*
   Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
   NetID: aurqui7, eleon23, jglebo2
   
   - This class consists of Menus that will be added to the menu bar of the gui application

   - All menus and sub-menus are created here and each sub-menu is created in the appropriate
     method to avoid confusion and clutter

   - each sub-menu is added to the appropriate menu and has its event handler created here 
*/

   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.JFileChooser;
   import java.io.File;   
   import java.util.ArrayList;
   import java.util.List;
   import java.io.PrintWriter;
   import java.io.FileNotFoundException;


   public class MenuClass extends JFrame{

   // initialize instances
      JMenu fileMenu;
      JMenu helpMenu;
      JMenu hintMenu;
      Grid grid;  
      GuiLayout GUI;
      private List<String> board = new ArrayList<String>();
      
      JFileChooser fileChooser = new JFileChooser();
   // constructor 
   // @Param setGrid - Will hold object as a reference where the menus can modify cells for algorithms, input files, etc.
      public MenuClass(Grid setGrid, GuiLayout board)
      {
         grid = setGrid;
         GUI = board;
         createMenu();

      }

   // create new menus and call methods to create and add sub-menus
      public void createMenu()
      {
      // create menus
         fileMenu = new JMenu( "File" );
         helpMenu = new JMenu("Help");
         hintMenu = new JMenu("Hint");

      //create and add sub-menus
         fileMenuItems();
         HelpMenuItems();
         HintMenuItems();

      }

   // create sub-menus for the menu item and create event-handlers for each one
      public void fileMenuItems()
      {

      // load puzzle sub-menu will prompt user to add pre-existing puzzle
         JMenuItem loadPuzzle = new JMenuItem( "Load Puzzle" );
         fileMenu.add( loadPuzzle );
         loadPuzzle.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // prompt user to input text file containing new sudoku puzzle
            public void actionPerformed( ActionEvent event )
            {
               //Create button Array and inputfile
               buttonClass[] buttonArray;
               String[] inputfile;

               //Choose a file to upload
               JFileChooser fileChooser = new JFileChooser();
               //let the user select a file
               fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
               int result = fileChooser.showOpenDialog(fileMenu);

               //if it is a valid file
               if (result == JFileChooser.APPROVE_OPTION) {
                 File selectedFile = fileChooser.getSelectedFile();
                 GUI.clearBoard();
                 board = GUI.checkAndExtractInput(selectedFile.getName());
                 GUI.splitInput(board);
                 //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
              }
           }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // store puzzle sub-menu will store the state of the board into a text file
         JMenuItem storePuzzle = new JMenuItem( "Store Puzzle" );
         fileMenu.add( storePuzzle );
         storePuzzle.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // gets current state of the board and stores that state into a text file
            public void actionPerformed( ActionEvent event )
            {
   
               JFileChooser fileStore = new JFileChooser();
               fileStore.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
               fileStore.setMultiSelectionEnabled(false);

               int val = fileStore.showSaveDialog(null);

               if(val == JFileChooser.APPROVE_OPTION){

                  //select where to store the new file
                  File f = fileStore.getSelectedFile();
                  String path = f.getAbsolutePath();
                  
                  //try and catch
                  try(PrintWriter out = new PrintWriter(f)){

                     //get the size of the original board
                     int size = GUI.returnSize();
                     //get the size of the updated board as the user interacts with it
                     int updatedBoardSize = grid.returnUpdateBoard();

                     //loop through the original board and print out info
                     for(int i = 0; i < size; i++){

                        if(i % 3 == 0 && i != 0){
                           out.println();
                        }
                        out.println(GUI.returnCoords(i));

                     }

                     //loop through the updated board and print out info
                     for(int x = 0; x < updatedBoardSize; x++){
                        if(x % 3 == 0 && x != 0){
                           out.println();
                        }

                        out.println(grid.returnCoordsForUB(x));
                     }
                    
                  
               }

                  catch(FileNotFoundException x){

                     System.out.println("ERROR");

                  }

               }


            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // exit sub-menu will exit the program
         JMenuItem exit = new JMenuItem( "EXIT" );
         fileMenu.add( exit );
         exit.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // exit the program
            public void actionPerformed( ActionEvent event )
            {
               System.exit(0);
            }
         }  // end anonymous inner class
      ); // end call to addActionListener

      }

   // create the sub-menus for the Help menu and add event-handlers for each one
      public void HelpMenuItems()
      {

      //Sudoku Rules displays information about how to play the game
         JMenuItem howTo = new JMenuItem( "Sudoku Rules" );
         helpMenu.add( howTo );
         howTo.addActionListener(
        new ActionListener() {  // anonymous inner class
            // display message dialog when user selects Sudoku Rules...
         public void actionPerformed( ActionEvent event )
         {
            JOptionPane.showMessageDialog( MenuClass.this,
                "Fill a number in to every cell in the grid, using the numbers 1 to 9\n" +
                          "You can only use each number once in each row, each column, and in each of the 3×3 boxes",
                  "Sudoku Rules", JOptionPane.PLAIN_MESSAGE );
         }
        }  // end anonymous inner class
      ); // end call to addActionListener


      // interface how to sub-menu will have information on how to use gui
      // interface
         JMenuItem interfce = new JMenuItem( "Interface How To" );
         helpMenu.add( interfce );
         interfce.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // display message dialog when user selects Interface How To...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                                 "You click on one of the buttons on the side panel (1-9 or X)and then you click on one" +
                          " of the buttons where you would like that number to go(in one of the 3x3 boxes)\n" +
                          "File: \n" +
                          "     -Load Puzzle, Allow the user to Load the puzzle,\n" +
                          "     -Store Puzzle, store the puzzle\n" +
                          "     -Exit- Exits the game\n" +
                          "Help: \n" +
                          "     -Sudoku Rules, Lists the rules of the Sudoku game\n" +
                          "     -Interface how to-  use the program,\n" +
                          "     -About,  Lists names of authors & netid's\n" +
                          "Hint:\n" +
                          "     -Check-fill which fills in as many blank cells as possible\n" +
                          "     -Four different algorithms- to help out the player(single, hidden single etc)\n" +
                          "     -Fill-all, fills in a many blank cells as possible using all four algorithms",
                  "Interface How To", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // About sub-menu will have information about authors of the program. to
      // with grading
         JMenuItem about = new JMenuItem( "About" );
         about.setMnemonic( 'a' );
         helpMenu.add( about );
         about.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "Authors: \n" +
                  "Eric Leon (eleon23)\n" + 
                  "Jakub Glebocki (JGlebo2)\n" +
                  "Alexis Urquiza (aurqui7)\n",
                  "About", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener
      }


   // create sub-menus for the Hint menu as well as add event-handlers for each one
      public void HintMenuItems()
      {

      // check-fill sub-menu is a check box that will give the user an error if number they
      // attempted to inser is not in the candidate list for that cell
         JMenuItem check = new JMenuItem( "Check-fill" );
         hintMenu.add( check );
         check.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is to check if it is fill ",
                  "Check-fill", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // Single-Algorithm sub-menu searches each cell to see if there is only one candidate
         JMenuItem singleAlgo = new JMenuItem( "Single Algorithm" );
         hintMenu.add( singleAlgo );
         singleAlgo.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the Single Algorithm ",
                  "Single Algorithm", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // Hidden-Single-Algorithm sub-menu will check each cell and its appropriate
      // row, column and box to determine if there it has a unique candidate
         JMenuItem hSingleAlgo = new JMenuItem( "Hidden Single Algorithm" );
         hintMenu.add( hSingleAlgo );
         hSingleAlgo.addActionListener(
         new ActionListener() {  // anonymous inner class
            //run hidden-single algorithm
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the hidden single Algorithm",
                  "hSingleAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // Locked Candidate Algorithm sub-menu will look at different cells in a cell's row
      // and column to exclude candidates in that specific cell
         JMenuItem LCanditateAlgo = new JMenuItem( "Locked Candidate Algorithm" );
         hintMenu.add( LCanditateAlgo );
         LCanditateAlgo.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // run locked candidate algorithm
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the Locked Candidate Algorithm",
                  "LCanditateAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      // naked pairs algorithm
         JMenuItem nakePairsAlgo = new JMenuItem( "Naked Pairs Algorithm" );
         hintMenu.add( nakePairsAlgo );
         nakePairsAlgo.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the Nake Pairs Algorithm",
                  "nakePairsAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


       // fill all algorithm
         JMenuItem fillAll = new JMenuItem( "Fill All" );
         hintMenu.add( fillAll );
         fillAll.addActionListener(
            new ActionListener() 
         {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for filling all four algorithms ",
                  "Fill All", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener
      }

   // getter method that returns file menu
      public JMenu getFileMenu()
      {
         return fileMenu;
      }

   // getter method that returns help menu
      public JMenu getHelpMenu()
      {
         return helpMenu;
      }

   // getter method that returns hint menu
      public JMenu getHintMenu(){
         return hintMenu;
      }

   }