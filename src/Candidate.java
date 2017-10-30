/*
	Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
	NetID: aurqui7, eleon23, jglebo2

	- This class consists of methods that help manipulate data for candidates in a cell.
	  It is mostly used to update candidates based on what the user does on the board.

*/
public class Candidate
{
	private Grid grid;

	// constructor
	public Candidate(Grid refGrid)
	{
		grid = refGrid;
	}

	// restores a candidate that was initially deleted 
	public void restoreCandidate(String cand, buttonClass cell)
	{
		//NOTE: FIGURE OUT A WAY TO RESTORE ROW,COLUMN, AND SUBGRID CANDIDATES AS WELL
		cell.addCandidate(cand);
	}

	// this resets the every cell to the original state where all numbers 1-9
	// are potential candidates. This method is only used when the user chooses a new
	// board in the middle of another puzzle
	public void resetCandidates()
	{
		// grab each subgrid in the grid then grab each candidate list in
		// every cell and reset it back to 1-9
		for(int i = 0; i < 9; i++)
		{
			buttonClass cellArray[] = grid.getSubAtCellAt(i);
			for(int j = 0; j < 9; j++)
			{
				cellArray[j].resetCellCands();
			}
		}
	}

	// when a cell is updated by a number the row, column and subgrid
	// it belongs to has those group appropriately updated
	public void removeCandidate(String cand, int section, buttonClass cell)
	{
		buttonClass cellArray[] = grid.getSubAtCellAt(section);

		//deletes candidate from the subgrid the button belongs to
		for(int i = 0; i < 9; i++)
		{
			String cellCandidates[] = cellArray[i].getCandidates();
			deleteCandidate(cand, cellCandidates);
		}

		//find row and column button belongs to
		buttonClass[][] subgrid2d = convertArray(cellArray);
		int col = -1,row = -1;
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				if(subgrid2d[x][y] == cell)
				{
					col = y;
					row = x;
				}

		//get subgrids that should have candidate deleted
		int[] arr = determineSubgrids(section);

		//get rid of candidates from same row and same column
		buttonClass[][] subRow1 = convertArray(grid.getSubAtCellAt(arr[0]));
		buttonClass[][] subRow2 = convertArray(grid.getSubAtCellAt(arr[1]));
		buttonClass[][] subCol1 = convertArray(grid.getSubAtCellAt(arr[2]));
		buttonClass[][] subCol2 = convertArray(grid.getSubAtCellAt(arr[3]));
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
			{
				if(x == row)
				{
					deleteCandidate(cand, subRow2[x][y].getCandidates());
					deleteCandidate(cand, subRow1[x][y].getCandidates());
				}
				if(y == col)
				{
					deleteCandidate(cand, subCol2[x][y].getCandidates());
					deleteCandidate(cand, subCol1[x][y].getCandidates());
				}
			}
	}

	// delete all candidates from a button
	public void deleteAllCandidates(String[] cellCandidates)
	{
		for(int i = 0; i < 9; i++)
			cellCandidates[i] = " ";
	}

	// delete one specific candidate from a button
	public void deleteCandidate(String cand, String[] cellCandidates)
	{
		for(int i = 0; i < 9; i++)
			if(cellCandidates[i].equals(cand))
				cellCandidates[i] = " ";

	}

	// convert a 1d button array into a 2d button array
	private buttonClass[][] convertArray(buttonClass[] arr)
	{
		buttonClass[][] newArr = new buttonClass[3][3];
		for(int i = 0; i < 3; i++)
			newArr[0][i] = arr[i];
		for(int i = 3; i < 6; i++)
			newArr[1][i%3] = arr[i];
		for(int i = 6; i < 9; i++)
			newArr[2][i%3] = arr[i];

		return newArr;
	}

	// returns neighbors of a subgrid, where the neighbors consist of anything
	// in the same row and column as the subgrid as an int array
	private int[] determineSubgrids(int section)
	{
		switch(section)
		{
			case 0: return (new int[]{1,2,3,6});
			case 1: return (new int[]{0,2,4,7});
			case 2: return (new int[]{0,1,5,8});
			case 3: return (new int[]{4,5,0,6});
			case 4: return (new int[]{3,5,1,7});
			case 5: return (new int[]{3,4,2,8});
			case 6: return (new int[]{7,8,0,3});
			case 7: return (new int[]{6,8,4,1});
			case 8: return (new int[]{6,7,5,2});
		}

		return null;
	}

}