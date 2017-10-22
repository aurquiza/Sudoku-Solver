
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

public class GuiLayout extends JFrame 
{
	private Container mainContainer;
	private MenuClass mainMenu = new MenuClass();
	private Grid grid;
	
	SubGrid n = new SubGrid(new GridLayout(0,1), false, 0, 10);
	private JMenuBar bar;
	List<String> fullBoard = new ArrayList<String>();

	public GuiLayout(List<String> input)
	{

		super("CS 342 Sudoku-Solver");
		
		grid = new Grid(3,5,7,7,9);

		mainContainer = getContentPane();
		//mainContainer.setLayout(new BorderLayout());
		mainContainer.setLayout(grid);
		fullBoard  = input;
		printInputList();
		initSubGrids();
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		addMenus();
		
		setSize(500,500);
		setVisible(true);

	}

	public void printInputList(){
		for(int i = 0; i < fullBoard.size(); i++){
			System.out.println(fullBoard.get(i));
		}
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


	public static void main(String[] args) throws Exception 
	{

		String filename = null;
		List<String> fileInput = new ArrayList<String>();

		if (0 < args.length) {
			filename = args[0];
			File file = new File(filename);
			System.out.println(filename);
		}

		try{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				fileInput.add(line);
			}

			fileInput.toArray(new String[fileInput.size()]);

			GuiLayout app = new GuiLayout(fileInput);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			bufferedReader.close();

		}
		catch(FileNotFoundException e){
			System.out.println("ERROR: FILE NOT FOUND BRUHH");
			System.exit(0);
		}

		

		

		

		

	}

}