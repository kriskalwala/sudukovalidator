#!/bin/bash

#set -e

arg1=$1
echo $arg1

#exit -1

echo $KRIS_ENV1

if [ -z "$1" ]; then

   echo 'missing argument as filename with ext of text file'
   echo ''
   echo 'usage : '
   echo 'macbook-air:scripts kris$ pwd'
   echo '/Users/kris/workspace-oxgen/SudokuSolutionsChecker/src/main/scripts'
   echo ''
   echo 'macbook-air:scripts kris$ . validate.sh <SUDOKU_FILE_TO_VALIDATE>'
   echo ''
   echo 'example:  . validate.sh validsudoku.txt'   

else

   #SUDOKU_POM_HOME=/Users/kris/workspace-oxgen/SudokuSolutionsChecker
   JAR_FILE=sudokuvalidator-0.0.1-SNAPSHOT.jar
   CLASS_WITH_MAIN=sudokuvalidator.SudokuSolutionValidator
   SUDOKU_FILE_TO_VALIDATE=$arg1    
       #validsudoku.txt

   java -cp $SUDOKU_POM_HOME/target/$JAR_FILE $CLASS_WITH_MAIN $SUDOKU_POM_HOME/target/test-classes/$SUDOKU_FILE_TO_VALIDATE

fi
