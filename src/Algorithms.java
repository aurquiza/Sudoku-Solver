/*
	Name: Alexis Urquiza, Eric Leon, Jakub Glebocki
	NetID: aurqui7, eleon23, jglebo2

*/
import java.util.Vector;
import java.util.*;

public class Algorithms
{
	Grid grid;
	Candidate cand;

	// constructor
	public Algorithms(Grid refGrid)
	{
		grid = refGrid;
		cand = new Candidate(refGrid);
	}

	public void SingleAlgorithm()
	{
		// created variables
		boolean flag = false;
		Vector<Integer> v = null;
		buttonClass chosenCell = null;

		// go through every subgrid
		for(int i = 0; i < 9; i++)
		{
			//get a subcell
			buttonClass cells[] = grid.getSubAtCellAt(i);

			// go through every cell
			for(int j = 0; j < 9; j++)
			{	
				//convert string array to vector int
				v = convertToVector(cells[j].getCandidates());
				// if the size is one then you found a single candidate
				if(v.size() == 1)
				{
					flag = true;
					chosenCell =  cells[j];
					break;
				}
			}

			// if candidate found then break
			if(flag)
			{
				break;
			}
		}

		// error checking in case no candidate was found
		if(flag)
		{
			// delete potential candidate and set cell value
			chosenCell.setCellValue(Integer.toString(v.get(0)));
			cand.removeCandidate(Integer.toString(v.get(0)), chosenCell.getCellSection(), chosenCell);
		}
		
	}

	// hidden single algorithm
	public void HiddenSingleAlgorithm()
	{
		boolean flag = false;
		buttonClass chosenCell = null;
		int ChosenCandidate = -1;
		
		for(int i = 0; i < 9; i++)
		{
			//keep track of the amount of times a candidate pops up in a subgrid
			int[] numberOfCandidates = new int[]{0,0,0,0,0,0,0,0,0,0};
			buttonClass cells[] = grid.getSubAtCellAt(i);

			for(int j = 0; j < 9; j++)
			{
				Vector<Integer> v = convertToVector(cells[j].getCandidates());

				for(int k = 0; k < v.size(); k++)
				{
					numberOfCandidates[v.get(k)] += 1;
				}

			}

			// This loops through the count of number of candidates
			for(int x = 1; x < 10; x++)
			{
				// if one of the candidates has a count of 1 then
				// hidden single is found(or just a single)
				if(numberOfCandidates[x] == 1)
				{
					flag = true;
					ChosenCandidate = x;
					for(int j = 0; j < 9; j++)
					{
						Vector<Integer> v = convertToVector(cells[j].getCandidates());
						for(int k = 0; k < v.size(); k++)
							if(v.get(k) == x)
								chosenCell = cells[j];
					} // end of for loop
				} // end of if statement
			} // end of for loop

			if(flag)
				break;
		} // end of for loop

		// check if there was a hidden single found and assign it if there was
		if(flag)
		{
			chosenCell.setCellValue(Integer.toString(ChosenCandidate));
			cand.removeCandidate(Integer.toString(ChosenCandidate), chosenCell.getCellSection(), chosenCell);
		}

	}

	// locked candidate algorithm
	public void LockedCandidateAlgorithm()
	{
		// 3 vectors that will hold sets of data pertaining to the cadidate lists for each
		// subgrid

		for(int i = 0; i < 9; i++)
		{
			//for horizontal locked candidates
			Vector<HashSet<Integer>> originV = new Vector<HashSet<Integer>>(3);
			Vector<HashSet<Integer>> boxV1 = new Vector<HashSet<Integer>>(3);
			Vector<HashSet<Integer>> boxV2 = new Vector<HashSet<Integer>>(3);

			//for vertical locked candidates
			Vector<HashSet<Integer>> originVert = new Vector<HashSet<Integer>>(3);
			Vector<HashSet<Integer>> boxVert1 = new Vector<HashSet<Integer>>(3);
			Vector<HashSet<Integer>> boxVert2 = new Vector<HashSet<Integer>>(3);


			// get your "origin box"
			buttonClass subgridCells[] = grid.getSubAtCellAt(i);
			buttonClass originBox[][] = convertTo2D(subgridCells);
			
			//get neighbor subgrids based on origin subgrid
			int hNeighbors[] = horizontalNeighbors(i);
			int vNeighbors[] = verticalNeighbors(i);

			// convert neighbor subgrids to 2d arrays for horizontal locked candidates
			buttonClass box1[][] = convertTo2D(grid.getSubAtCellAt(hNeighbors[0]));
			buttonClass box2[][] = convertTo2D(grid.getSubAtCellAt(hNeighbors[1]));

			// convert neighbor subgrids to 2d arrays for vertical locked candidates
			buttonClass box1vertical[][] = convertTo2D(grid.getSubAtCellAt(vNeighbors[0]));
			buttonClass box2vertical[][] = convertTo2D(grid.getSubAtCellAt(vNeighbors[1]));


			groupCandidatesByCol(originVert, originBox);
			groupCandidatesByCol(boxVert1, box1);
			groupCandidatesByCol(boxVert2, box2);

			// create sets for each subgrid
			groupCandidatesByRow(originV, originBox);
			groupCandidatesByRow(boxV1, box1);
			groupCandidatesByRow(boxV2, box2);

			// group the sets into 1 final set that determines what candidates to delete
			Vector<Integer> groupedVec = incrementAndGroup(boxV1,boxV2);
			removeResolvedVals(groupedVec, originBox);

			Vector<Integer> groupedVecVertical = incrementAndGroup(boxVert1,boxVert2);
			removeResolvedVals(groupedVecVertical, originBox);

			// delete candidates
			if(groupedVec.size() != 0)
			{
				findAndDeleteCandidateRow(box1, box2, originBox, groupedVec);
				break;
			}
		}
	}


	// this deletes the candidate from the origin box
	private void _findAndDeleteCandidateRow(buttonClass[][] originBox, int cand, int row)
	{
		Candidate c = new Candidate(grid);

		// loop through every cell in the origin box
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{	
				// if the row matches then delete the candidate from that particular row
				if(x == row)
				{
					c.deleteCandidate(Integer.toString(cand), originBox[x][y].getCandidates());
				}
			}
		}
	}

	// check if the candidate has the same text as the cell
	private boolean compareCandidates(buttonClass cell, int cand)
	{
		// get candidates list
		String[] candidates = cell.getCandidates();

		// loop candidate list
		for(int i = 0; i < 9; i++)
		{
			// check if values equal to each other
			if(candidates[i].equals(Integer.toString(cand)))
			{
				return true;
			}
		}

		return false;
	}

	// this finds and deletes candidates essentially doing the locked candidates algorithm
	private void findAndDeleteCandidateRow(buttonClass[][] box1, buttonClass[][] box2, 
										buttonClass[][] originBox, Vector<Integer> groupedVec)
	{
		// loop through the grouped cell
		for(int i = 0; i < groupedVec.size(); i++)
		{
			// loop through the a subgrid
			for(int x = 0; x < 3; x++)
			{
				for(int y = 0; y < 3; y++)
				{
					// check if candidates match with number in vector
					if(compareCandidates(box1[x][y],(int)groupedVec.get(i)))
					{
						_findAndDeleteCandidateRow(originBox, (int)groupedVec.get(i), x);
					}

					// check if candidates match with number in vector 
					if(compareCandidates(box2[x][y],(int)groupedVec.get(i)))
					{
						_findAndDeleteCandidateRow(originBox, (int)groupedVec.get(i), x);
					}
				}
			}
		}
	}


	// remove any numbers from the vector passed in that already have a button on the
	// sudoku board
	private void removeResolvedVals(Vector<Integer>v1, buttonClass[][] origin)
	{
		// loop through rows in the subgrid
		for(int i = 0; i < 3; i++)
		{
			// loop through vector to find any matching values from cells
			for(int j = 0; j < v1.size(); j++)
				if(!(origin[i][0].getText().equals(" ")))
					if(Integer.parseInt(origin[i][0].getText()) == (int)v1.get(j))
						v1.remove(j);

			// loop through vector to find any matching values from cells
			for(int j = 0; j < v1.size(); j++)
				if(!(origin[i][1].getText().equals(" ")))
					if(Integer.parseInt(origin[i][1].getText()) == (int)v1.get(j))
						v1.remove(j);

			// loop through vector to find any matching values from cells
			for(int j = 0; j < v1.size(); j++)
				if(!(origin[i][2].getText().equals(" ")))
					if(Integer.parseInt(origin[i][2].getText()) == (int)v1.get(j))
						v1.remove(j);
		}
	}

	// this increments two seperate int arrays based on how many times it is seen int vector 1 and vector 2
	// then those two int arrays are compared and grouped together based on how many times it was seen from both
	// arrays. those numbers are then pushed to a vector and returned
	private Vector<Integer> incrementAndGroup(Vector<HashSet<Integer>> v1, Vector<HashSet<Integer>> v2)
	{
		// create int arrays
		int potentialCandidates1[] = new int[]{0,0,0,0,0,0,0,0,0,0};
		int potentialCandidates2[] = new int[]{0,0,0,0,0,0,0,0,0,0};

		// increment array 1 based on values seen from vector 1
		for(int i = 0; i < 3; i++)
		{
			Set<Integer> s = v1.get(i);
			Iterator it = s.iterator();
			while(it.hasNext())
				potentialCandidates1[(int)it.next()]++;
		}

		// increment array 2 based on values seen from vector 2
		for(int i = 0; i < 3; i++)
		{
			Set<Integer> s = v2.get(i);
			Iterator it = s.iterator();
			while(it.hasNext())
				potentialCandidates2[(int)it.next()]++;
		}

		// create 3 seperate vectors
		Vector<Integer> vec1 = new Vector<Integer>();
		Vector<Integer> vec2 = new Vector<Integer>();
		Vector<Integer> groupedVec = new Vector<Integer>();


		// minimize the int array 1 with only numbers that have 2
		for(int i = 1; i < 10; i++)
		{
			if(potentialCandidates1[i] == 2)
				vec1.add(i);
			else if(potentialCandidates1[i] == 1)
				groupedVec.add(i);
		}

		// minimize the int array 2 with only numbers that have 2
		for(int i = 1; i < 10; i++)
		{
			if(potentialCandidates2[i] == 2)
				vec2.add(i);
			else if(potentialCandidates2[i] == 1)
				groupedVec.add(i);
		}

		// use minimized vectors and add similar values they both have to a new and final vector
		for(int i = 0; i < vec1.size(); i++)
			for(int j = 0; j < vec2.size(); j++)
				if((int)vec1.get(i) == (int)vec2.get(j))
					groupedVec.add(vec1.get(i));

		// return final vector
		return groupedVec;
	}

	// creates sets for every row in a subgrid and adds them to a vector to help compute
	// locked candidate algorithm
	private void groupCandidatesByRow(Vector<HashSet<Integer>> v1, buttonClass b1[][])
	{	
		// loop through subgrid 
		for(int x = 0; x < 3; x++)
		{
			// create new set
			HashSet<Integer> set = new HashSet<Integer>();

			// create vectors for candidate lists
			Vector<Integer> leftMost = convertToVector(b1[x][0].getCandidates());
			Vector<Integer> middle = convertToVector(b1[x][1].getCandidates());
			Vector<Integer> rightMost = convertToVector(b1[x][2].getCandidates());

			// add those candidates to the set
			for(int i = 0; i < leftMost.size(); i++)
				set.add(leftMost.get(i));
			for(int i = 0; i < middle.size(); i++)
				set.add(middle.get(i));
			for(int i = 0; i < rightMost.size(); i++)
				set.add(rightMost.get(i));

			//add set to vector
			v1.add(set);
		}
	}

	// creates sets for every column in a subgrid and adds them to a vector to help compute
	// locked candidate algorithm
	private void groupCandidatesByCol(Vector<HashSet<Integer>> v1, buttonClass b1[][])
	{
		// loop through subgrid 
		for(int x = 0; x < 3; x++)
		{
			// create new set
			HashSet<Integer> set = new HashSet<Integer>();

			// create vectors for candidate lists
			Vector<Integer> top = convertToVector(b1[0][x].getCandidates());
			Vector<Integer> middle = convertToVector(b1[1][x].getCandidates());
			Vector<Integer> bot = convertToVector(b1[2][x].getCandidates());

			// add those candidates to the set
			for(int i = 0; i < top.size(); i++)
				set.add(top.get(i));
			for(int i = 0; i < middle.size(); i++)
				set.add(middle.get(i));
			for(int i = 0; i < bot.size(); i++)
				set.add(bot.get(i));

			//add set to vector
			v1.add(set);
		}
	}

	// converts a candidate list of strings to a vector of integers
	private Vector<Integer> convertToVector(String[] candidates)
	{
		// create integer vector
		Vector<Integer> newVec = new Vector<Integer>();

		// loop through candidate list and convert it to integer
		for(int i = 0; i < 9; i++)
		{
			try
			{
				newVec.add(Integer.parseInt(candidates[i]));
			}
			catch(NumberFormatException e)
			{
				//do nothing
			}
			
		}

		// return vector
		return newVec;
	}

	// returns an int array consisting of the horizontal neighbors for a subgrid
	private int[] horizontalNeighbors(int origin)
	{
		switch(origin)
		{
			case 0: return (new int[]{1,2});
			case 1: return (new int[]{0,2});
			case 2: return (new int[]{0,1});
			case 3: return (new int[]{4,5});
			case 4: return (new int[]{3,5});
			case 5: return (new int[]{3,4});
			case 6: return (new int[]{7,8});
			case 7: return (new int[]{6,8});
			case 8: return (new int[]{6,7});
		}
		return null;
	}

	// returns an int array consisting of the vertical neighbors for a subgrid
	private int[] verticalNeighbors(int origin)
	{
		switch(origin)
		{
			case 0: return (new int[]{3,6});
			case 1: return (new int[]{4,7});
			case 2: return (new int[]{5,8});
			case 3: return (new int[]{0,6});
			case 4: return (new int[]{1,7});
			case 5: return (new int[]{2,8});
			case 6: return (new int[]{0,3});
			case 7: return (new int[]{1,4});
			case 8: return (new int[]{2,5});
		}
		return null;
	}


	// converts a 1d button array into a 2d button array
	private buttonClass[][] convertTo2D(buttonClass[] arr)
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
}