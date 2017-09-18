package com.kriskalwala.sudokuvalidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.kriskalwala.sudokuvalidator.IsSolutionValid;




/* Solution to verify correctness of solution is based on the fact that 
 * we check 3 basic conditions that must be jointly fulfilled :
 *  
 * 1. all rows' numbers must fall within the range of 1,..,9 and should be unique, non-repeating within the row
 * and may be allocated unordered.
 * 
 * AND
 * 
 * 2. all columns' numbers must fall within the range of 1,..,9 and should be unique, non-repeating within the column
 * and may be allocated unordered.
 * 
 * AND
 * 
 * 3. all numbers of elements of each of 9 squares must fall within the range of 1,..,9 and must be unique, non-repeating 
 * within the square represented by an 9-length array and may be allocated unordered.
 * 
 * verification will detect first occurring error during validation and throws this error signalling 
 * the invalidation of the solution 
 */



public class SudokuSolutionValidator {
	
	// we distinguish following objects: 1d row, 1d column, 3d row, 3d column, cell, block, 1/3 row, 1/3 column
	
	public static final int  ALL_BLOCKS_NR = 9; // number of all blocks in SUDOKU
	public static final int  COL_ROW_LENGTH = 9; // number of cells in 1d row or 1d column 
	public static final int  NR_OF_CELLS_IN_BLOCK = 9; // number of cells in block
	public static final int  ROWS_COLS_NR_IN_BLOCK  = 3; // number of 1/3 rows and 1/3 columns in block
	public static final int  BLOCKS_NR = 3; // number of blocks in SUDOKU in first 3d row and first 3d column	
	
  


	int[][] data = new int[9][9];
	
	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(SudokuSolutionValidator.class.getName());
		
		logger.info("Maven Project for Sudoku Solutions correctness check/validation");
		logger.info("args.length : " + args.length);
		
		if (args.length < 1) throw new IllegalArgumentException("please enter missing argument: " + " \"absolute path and filename to solution CSV text file\"");
		
		logger.info("args 0 : " + args[0]);
		
		SudokuSolutionValidator ssv = new SudokuSolutionValidator();
		
		try {
			ssv.importDatataFromCSVfile(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		logger.info(String.format("validating solution file %1$s" , args[0]));
		
		//verify correctness of data
		ssv.checkCorrectnessOfData();
		
				
		//validate SUDOKU solution
		ssv.validateSudokuSolution();
				
	}
	

	public IsSolutionValid validateSudokuSolution() { 
		
	         	// verify if there are unique non-repeated numbers in the row array
				for (int row = 0; row < 9; row++) {
					int[] rowArray = getRow(row);
					if (!IsUnique(rowArray))
						return IsSolutionValid.invalid(String.format("ERROR_REPEATING_NON_UNIQUE_NRS - row %1$d contains repeated " + "numbers  %2$s", row, Arrays.toString(rowArray)));
				}

				// verify if there are unique non-repeated numbers in the column array
				for (int column = 0; column < 9; column++) {
					int[] columnArray = getColumn(column);
					if (!IsUnique(columnArray))
						return IsSolutionValid.invalid(String.format("ERROR_REPEATING_NON_UNIQUE_NRS -  column %1$d contains repeated " + "numbers  %2$s", column, Arrays.toString(columnArray)));
				}

				// verify that each block cells which contain unique & non repeating numbers within range already verified
				for (int block = 0; block < 9; block++) {
					int[] blockArray = getBlock(block);
					if (!IsUnique(blockArray))
						return IsSolutionValid.invalid(String.format("ERROR_REPEATING_NON_UNIQUE_NRS - block  %1$d contains repeated " + "numbers %2$s", block, Arrays.toString(blockArray)));
				}
                
				return IsSolutionValid.valid();
			
	}

	/*--------------------------------------- HELPER METODS --------------------------------------*/
	
	public void importDatataFromCSVfile(String fileAbsolutePath) throws IOException {

		// import data line by line from exteral file located in resources of the project
		try (Stream<String> stream = Files.lines(Paths.get(fileAbsolutePath))) {
			final AtomicInteger rowCount = new AtomicInteger(0);
			stream.forEach((line) -> {
				String[] items = line.split(",");
				for (int i = 0; i < items.length; i++) {
					int parseInt = Integer.parseInt(items[i]);

					data[rowCount.get()][i] = parseInt;
				}
				rowCount.getAndIncrement();
			});
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
			  System.out.print(data[i][j] + ", ");	
			}
			System.out.println();
		}
	}


	
	public void checkCorrectnessOfData() {
		
		//check if data representing solution are exactly SUDOKU dimension
		if (data.length != 9) {
			throw new IllegalArgumentException(
					String.format("ERROR_INVALID_ARRAY_LENGTH %1$d, expected %2$d", data.length, 9));
		}
		
		// check if all elements are in valid range
		for (int i = 0; i < 9; i++) {
		   for (int j = 0; j < 9; j++) {
			    int element = data[i][j]; //getElementAt(i, j);
				if (element < 1 || element > 9) {
					throw new IllegalArgumentException(String.format("ERROR_OUT_OF_RANGE: range must be as of [1..%1$d], " + "invalid cell number %2$d at indexes:  %3$d, %4$d", 9, element, i, j));
				 }
			}
		  }		
	}

	
    // checking if all array elements are unique for each case: row.column, block (9 length)
	private boolean IsUnique(int[] anyArray) {
		if (anyArray.length != COL_ROW_LENGTH) {
			throw new IllegalArgumentException(String.format("ERROR_ARRAY_LENGTH: invalid array length %1$d, expected %2$d", anyArray.length, COL_ROW_LENGTH));
		}
		
		boolean result = true;
		int[] counter = new int[COL_ROW_LENGTH]; // all zero by default
		// count occurrences for numbers in cells
		for (int i = 0; i < COL_ROW_LENGTH; i++) {
			counter[anyArray[i] - 1]++;
		}
		// if any counter is not equal to one it means there is no unique numbers in array so solution is not correct
		for (int i = 0; i < COL_ROW_LENGTH; i++) {
			if (counter[i] != 1)
				result = false;
		}

		return result;
	}
	
	private int[] getRow(int row) {
		if (row < 0 || row >= COL_ROW_LENGTH)
			throw new IllegalArgumentException(String.format("ERROR_ROW: invalid row value %1$d, must fall in [0..%2%d]", row, COL_ROW_LENGTH - 1));
		return data[row];
	}

	private int[] getColumn(int column) {
		if (column < 0 || column >= COL_ROW_LENGTH)
			throw new IllegalArgumentException(String.format("ERROR_COLUMN: invalid column value %1$d, must fall in [0..%2%d]", column, COL_ROW_LENGTH - 1));
		int[] result = new int[COL_ROW_LENGTH];
		for (int i = 0; i < COL_ROW_LENGTH; i++) {
			result[i] = data[i][column];
		}
		return result;
	}

	private int[] getBlock(int block) {
		if (block < 0 || block >= ALL_BLOCKS_NR)
			throw new IllegalArgumentException(String.format("ERROR_BLOCK: invalid block value %1$d, block nr must fall in [0..%2%d]", block, ALL_BLOCKS_NR - 1));
		int[] result = new int[ALL_BLOCKS_NR];
		
		int initialRow = (block / BLOCKS_NR) * BLOCKS_NR;
		int initialColumn = (block % BLOCKS_NR) * ROWS_COLS_NR_IN_BLOCK;
		for (int i = 0; i < ROWS_COLS_NR_IN_BLOCK; i++)
			for (int j = 0; j < ROWS_COLS_NR_IN_BLOCK; j++)
				result[i + ROWS_COLS_NR_IN_BLOCK * j] = data[initialRow + i][initialColumn + j];

		return result;
	}

}