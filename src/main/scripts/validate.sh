#!/bin/bash

arg1=$1
echo $arg1

if [ "SUDOKU_POM_HOME"=="" ]; then

  echo 'no environemnt variable set : SUDOKU_POM_HOME'
  echo 'please execute from command line: '
  echo 'export SUDOKU_POM_HOME=/Users/kris/workspace-oxgen/sudokuvalidator (mind your local env)'  

fi



if [ -z "$1" ]; then

   echo 'missing argument as filename with ext of text file'
   echo ''
   echo 'usage : '
   echo 'macbook-air:scripts kris$ pwd'
   echo '/Users/kris/workspace-oxgen/sudokuvalidator/src/main/scripts'
   echo ''
   echo 'macbook-air:scripts kris$ . validate.sh <SUDOKU_FILE_TO_VALIDATE>'
   echo ''
   echo 'example:  . validate.sh validsudoku.txt'

else

   JAR_FILE=sudokuvalidator-0.0.1-SNAPSHOT.jar
   CLASS_WITH_MAIN=com.kriskalwala.sudokuvalidator.SudokuSolutionValidator
   SUDOKU_FILE_TO_VALIDATE=$arg1
  

   java -cp $SUDOKU_POM_HOME/target/$JAR_FILE $CLASS_WITH_MAIN $SUDOKU_POM_HOME/target/test-classes/$SUDOKU_FILE_TO_VALIDATE

fi
