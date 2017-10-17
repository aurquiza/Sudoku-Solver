import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiLayout extends JFrame 
{
	private Container mainContainer;
	private buttonClass mainGrid = new buttonClass("This is a button");

	private MenuClass mainMenu = new MenuClass();
	private GridLayout grid;

	private JMenuBar bar;

	public GuiLayout(){

		super("CS 342 Project 3");
		grid = new GridLayout(1,1);

		mainContainer = getContentPane();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setLayout(grid);

		mainContainer.add(mainGrid);
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		addMenus();


		setSize(500,500);
		setVisible(true);


	}

	private void addMenus()
	{
		bar.add(mainMenu.getFileMenu());
		bar.add(mainMenu.getHelpMenu());
		bar.add(mainMenu.getHintMenu());

	}



	public static void main(String[] args) 
	{

		GuiLayout app = new GuiLayout();

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





		
	}

}
