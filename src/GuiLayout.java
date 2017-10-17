import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiLayout extends JFrame 
{


	private Container mainContainer;
	private buttonClass mainGrid = new buttonClass();

	private MenuClass mainMenu = new MenuClass();

	public GuiLayout(){

		super("CS 342 Project 3");
		mainContainer = getContentPane();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.add(mainMenu.getBar());
		
		


		setSize(500,500);
		setVisible(true);


	}


	public static void main(String[] args) 
	{

		GuiLayout app = new GuiLayout();

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





		
	}

}
