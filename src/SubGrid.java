import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SubGrid extends JPanel
{
	private int SubGridSection;
	private buttonClass cells[];

	public SubGrid(GridLayout grid, boolean flag, int section, int numOfCells)
	{
		super(grid, flag);
		SubGridSection = section;

		cells = new buttonClass[numOfCells];
		createCells();
	}

	private void createCells()
	{
		for(int i = 0; i < cells.length; i++)
		{
			cells[i] = new buttonClass(" ", SubGridSection);
			add(cells[i]);
		}
	}
}