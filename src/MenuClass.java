import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuClass extends JFrame{

   JMenu fileMenu;
   JMenu helpMenu;
   JMenu hintMenu;

   public MenuClass()
   {
      createMenu();
      
   }

   public void createMenu()
   {

      fileMenu = new JMenu( "File" );
      helpMenu = new JMenu("Help");
    hintMenu = new JMenu("Hint");

      fileMenuItems();
      HelpMenuItems();
      HintMenuItems();

   }


   public void fileMenuItems()
  {

      JMenuItem loadPuzzle = new JMenuItem( "Load Puzzle" );
      fileMenu.add( loadPuzzle );
      loadPuzzle.addActionListener(
      new ActionListener() 
      {  // anonymous inner class

        public void actionPerformed( ActionEvent event )
        {
          JOptionPane.showMessageDialog( MenuClass.this,
            "This is for loading in a puzzle",
            "Load Puzzle", JOptionPane.PLAIN_MESSAGE );
        }
      }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem storePuzzle = new JMenuItem( "Store Puzzle" );
      fileMenu.add( storePuzzle );
      storePuzzle.addActionListener(
         new ActionListener() 
         {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
              {
              JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for Storing a puzzle",
                  "Store Puzzle", JOptionPane.PLAIN_MESSAGE );
              }
         }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem exit = new JMenuItem( "EXIT" );
      fileMenu.add( exit );
      exit.addActionListener(
         new ActionListener() 
         {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               System.exit(0);
            }
         }  // end anonymous inner class
      ); // end call to addActionListener

   }


   public void HelpMenuItems()
  {

      JMenuItem howTo = new JMenuItem( "Sudoku Rules" );
      helpMenu.add( howTo );
      howTo.addActionListener(
        new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is how to play Sudoku: ",
                  "Sudoku Rules", JOptionPane.PLAIN_MESSAGE );
            }
        }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem interfce = new JMenuItem( "Interface How To" );
      helpMenu.add( interfce );
      interfce.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is how to use ther Interface ",
                  "Interface How To", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem about = new JMenuItem( "About" );
      about.setMnemonic( 'a' );
      helpMenu.add( about );
      about.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "Eric Leon (eleon23) Jakub Glebocki (JGlebo2) Alexis Urquiza (aurqui7) \n",
                  "about", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener
   }


   public void HintMenuItems()
  {

      JMenuItem check = new JMenuItem( "Check-fill" );
      hintMenu.add( check );
      check.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is to check if it is fill ",
                  "Check-fill", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


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


      JMenuItem hSingleAlgo = new JMenuItem( "Hidden Single Algorithm" );
      hintMenu.add( hSingleAlgo );
      hSingleAlgo.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the hidden single Algorithm",
                  "hSingleAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem LCanditateAlgo = new JMenuItem( "Locked Candidate Algorithm" );
      hintMenu.add( LCanditateAlgo );
      LCanditateAlgo.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the Locked Candidate Algorithm",
                  "LCanditateAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener


      JMenuItem nakePairsAlgo = new JMenuItem( "Naked Pairs Algorithm" );
      hintMenu.add( nakePairsAlgo );
      nakePairsAlgo.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for the Nake Pairs Algorithm",
                  "nakePairsAlgo", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener

       
    JMenuItem fillAll = new JMenuItem( "Naked Pairs Algorithm" );
      hintMenu.add( fillAll );
      fillAll.addActionListener(
         new ActionListener() {  // anonymous inner class
            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for filling all four algorithms ",
                  "fillAll", JOptionPane.PLAIN_MESSAGE );
            }
         }  // end anonymous inner class
      ); // end call to addActionListener
   }


   public JMenu getFileMenu()
   {
      return fileMenu;
   }

   public JMenu getHelpMenu()
   {
      return helpMenu;
   }

   public JMenu getHintMenu(){
      return hintMenu;
   }

}