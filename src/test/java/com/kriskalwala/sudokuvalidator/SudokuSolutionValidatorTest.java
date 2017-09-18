package com.kriskalwala.sudokuvalidator;

import java.io.File;
import java.util.logging.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class SudokuSolutionValidatorTest extends TestCase {
	
	Logger logger = Logger.getLogger(SudokuSolutionValidatorTest.class.getName());
	
    public SudokuSolutionValidatorTest( String testName )
    {
        super( testName );
    }
    
    public static Test suite()
    {
        return new TestSuite( SudokuSolutionValidatorTest.class );
    }

    public void testFailure()
    {
      	SudokuSolutionValidator ssv = new SudokuSolutionValidator();
		String fileName = "invalidsolution.txt";
    	    String absolutePath = getAbsolutePathForResource(fileName);
    	    logger.info(" TEST: getAbsolutePathForResource -> absolutePath : " + absolutePath);
    	    IsSolutionValid svr = null;
      	try {
      		ssv.importDatataFromCSVfile(absolutePath);
      		svr = ssv.validateSudokuSolution();      		      		
       	}
      	catch (Exception e) {
    		   logger.severe(e.getMessage());
      	}
       
      	assertEquals(svr.getErrorfield(), IsSolutionValid.isValid.INVALID, svr.getIsValidfield());
      	logger.info("testFailure RESULT: " + svr.getIsValidfield());
      	logger.info("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void testFailure2()
    {
      	SudokuSolutionValidator ssv = new SudokuSolutionValidator();
		String fileName = "toValidateSolution.txt";
    	    String absolutePath = getAbsolutePathForResource(fileName);
    	    System.out.println(" testFailure2: getAbsolutePathForResource -> absolutePath : " + absolutePath);
    	    IsSolutionValid svr = null;
      	try {
      		ssv.importDatataFromCSVfile(absolutePath);
      		svr = ssv.validateSudokuSolution();      		    		
       	}
      	catch (Exception e) {
    		   logger.severe(e.getMessage());
      	}
      	
      	assertEquals(svr.getErrorfield(), IsSolutionValid.isValid.INVALID, svr.getIsValidfield());
      	logger.info("testFailure2 RESULT : " + svr.getIsValidfield());
      	logger.info("----------------------------------------------------------------------------------------------------------------------------------------------------");
    	
    }
    
    
    public void testSucceed()
    {
      	SudokuSolutionValidator ssv = new SudokuSolutionValidator();
    	
		String fileName = "validsolution.txt";
      	String absolutePath = getAbsolutePathForResource(fileName);
      	logger.info(" TEST: getAbsolutePathForResource -> absolutePath : " + absolutePath);
      	IsSolutionValid svr = null;
       	
      	try {    	
      		ssv.importDatataFromCSVfile(absolutePath);      		
      		svr = ssv.validateSudokuSolution();      		
     	}
     	catch (Exception e) {
    		     logger.severe(e.getMessage());
     	}    	
        
      	assertEquals(svr.getErrorfield(), IsSolutionValid.isValid.VALID, svr.getIsValidfield());
      	logger.info("3. testSucceed - RESULT: " + svr.getIsValidfield());
      	logger.info("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }
      
	private String getAbsolutePathForResource(String fileName) {
		  ClassLoader classLoader = getClass().getClassLoader();
    	      File file = new File(classLoader.getResource(fileName).getFile());
    	      String absolutePath = file.getAbsolutePath();
		return absolutePath;
	}

}
