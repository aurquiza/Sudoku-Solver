import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuClass extends JFrame{

	JMenu fileMenu;
	JMenuBar bar;

	public MenuClass()
	{
		createMenu();
		
    }

    public void createMenu()
    {


		bar = new JMenuBar();
		setJMenuBar(bar);

		fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( 'F' );

        bar.add(fileMenu);




    }

    public JMenu getFileMenu()
    {
    	return fileMenu;
    }
    public JMenuBar getBar()
    {
    	return bar;
    }




}