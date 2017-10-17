import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuClass extends JFrame{


	public MenuClass()
	{


		
	}

	public void createMenu()
	{

		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);

		JMenu fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( 'F' );

        bar.add(fileMenu);


     //  setSize( 500, 200 );
      setVisible( true );

	}




}