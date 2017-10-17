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
        fileMenu.setMnemonic( 'F' );
        fileMenuItems();

    }

    public void fileMenuItems(){



        JMenuItem loadPuzzle = new JMenuItem( "Load Puzzle" );
      	loadPuzzle.setMnemonic( 'A' );
        fileMenu.add( loadPuzzle );

        loadPuzzle.addActionListener(

         new ActionListener() {  // anonymous inner class

            // display message dialog when user selects About...
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog( MenuClass.this,
                  "This is for loading in a puzzle",
                  "Load Puzzle", JOptionPane.PLAIN_MESSAGE );
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