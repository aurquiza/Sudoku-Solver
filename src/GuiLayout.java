import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiLayout extends JFrame 
{
	private Container mainContainer;
	private MenuClass mainMenu = new MenuClass();
	private Grid grid;

	//private JPanel p;
	SubGrid n = new SubGrid(new GridLayout(0,1), false, 0, 10);

	private JMenuBar bar;
	public GuiLayout()
	{

		super("CS 342 Sudoku-Solver");
		grid = new Grid(3,5,7,7,9);

		mainContainer = getContentPane();
		//mainContainer.setLayout(new BorderLayout());
		mainContainer.setLayout(grid);

		initSubGrids();
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		addMenus();

		//mainContainer.add(n, BorderLayout.EAST);
		// GridLayout x = new GridLayout(4,1);
		// p = new JPanel(x,false);
		

		// JButton c = new JButton("testing");
		// JButton d = new JButton("testing");
		// JButton a = new JButton("testing");
		// p.add(b);
		// p.add(c);
		// p.add(d);
		// p.add(a);

		setSize(500,500);
		setVisible(true);

	}

	private void addMenus()
	{
		bar.add(mainMenu.getFileMenu());
		bar.add(mainMenu.getHelpMenu());
		bar.add(mainMenu.getHintMenu());
	}

	public void initSubGrids()
	{
		SubGrid sGrids[] = grid.getSubGrids();

		for(int i = 0; i < sGrids.length; i++)
		{
			mainContainer.add(sGrids[i]);
		}
	}


	public static void main(String[] args) 
	{
		GuiLayout app = new GuiLayout();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}