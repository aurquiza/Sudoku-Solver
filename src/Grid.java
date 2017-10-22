import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid extends GridLayout
{
	private SubGrid gridSections[];

	public Grid(int row, int col, int hgap, int vgap, int numSubGrids)
	{
		super(row, col, hgap, vgap);
		createSubGrids(numSubGrids);

	}

	public SubGrid[] getSubGrids()
	{
		return gridSections;
	}

	private void createSubGrids(int numSubGrids)
	{
		gridSections = new SubGrid [numSubGrids];

		for(int i = 0; i < numSubGrids; i++)
		{
			GridLayout newGrid = new GridLayout(3,3,0,0);
			gridSections[i] = new SubGrid(newGrid, false, i, 9);
		}
	}

}