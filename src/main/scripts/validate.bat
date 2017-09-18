@echo off
if [%1]==[] goto usage

SET SUDOKU_FILE_TO_VALIDATE=%1
SET JAR_FILE="sudokuvalidator-0.0.1-SNAPSHOT.jar"

if "%SUDOKU_POM_HOME%"=="" (goto :nospht)

if NOT EXIST %SUDOKU_POM_HOME%/target/%JAR_FILE% (goto :nojarfile)

if NOT EXIST %SUDOKU_FILE_TO_VALIDATE%  (goto :nosolutionfile)

java -cp %SUDOKU_POM_HOME%/target/%JAR_FILE% com.kriskalwala.sudokuvalidator.SudokuSolutionValidator %SUDOKU_FILE_TO_VALIDATE%

	
@echo Done.
goto :eof

:usage
@echo Missing parameter. Usage: %0 ^<AbsolutePathToSolutionFile^>
exit /B 1

:nosph
echo SUDOKU_POM_HOME is empty. SUDOKU_POM_HOME/target/%JAR_FILE% must be located. 
exit /B 1

:nojarfile
echo %SUDOKU_POM_HOME%/target/%JAR_FILE% not be found.
exit /B 1

:nosolutionfile
echo sudoku file "%SUDOKU_FILE_TO_VALIDATE%" not found
exit /B 1