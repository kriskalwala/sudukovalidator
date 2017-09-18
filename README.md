# MAC OSX / LINUX  

## 1. Installation
  
   Install Maven 3.5.0 using brew: 
       
       brew install maven   
       
       or download from : https://maven.apache.org/download.cgi and unzip then pointing locations as   
       shown below in point 2 & 3. (if MAVEN is already installed check :  $ whereis mvn)
   Install Java 8 : http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## 2. Make Sure to set environment variables 
   
      $ export M2_HOME=/usr/local/Cellar/maven/3.5.0/libexec (if installed by brew)     
      or e.g. /Users/kris/Downloads/apache-maven-3.5.0 (if downloaded as zip)                                               
                                             
      $ export M2=$M2_HOME/bin
   
      $ export PATH=$M2:$PATH

## 2. Verify variables

      $ mvn -v
      
      Apache Maven 3.5.0 
      
      Maven home: /usr/local/Cellar/maven/3.5.0/libexec
      
      Java version: 1.8.0_121, vendor: Oracle Corporation
      
      Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre
      
      Default locale: en_GB, platform encoding: UTF-8
      
      OS name: "mac os x", version: "10.12.1", arch: "x86_64", family: "mac"

## 3. Set Home sudoku project directory where pom.xml is located as environment variable
   
     export SUDOKU_POM_HOME=/Users/kris/workspace-oxgen/sudokuvalidator (mind your local env)
   
     otherwise: Error: Could not find or load main class sudokuvalidator.SudokuSolutionValidator


## 4. To build application run MAVEN
   
from SUDOKU_POM_HOME run :  
   
     $ mvn clean package

## 5. Run JAVA App according to following pattern

     argument: arg[0] = $SUDOKU_FILE_TO_VALIDATE
   
     absolutepath + filename = $SUDOKU_POM_HOME/target/test-classes/$SUDOKU_FILE_TO_VALIDATE
     where $SUDOKU_POM_HOME is a dir where pom.xml of the app is located.
   
     run app :  
   
     java -cp $SUDOKU_POM_HOME/target/sudokuvalidator-0.0.1-SNAPSHOT.jar CLASS_WITH_MAIN$SUDOKU_POM_HOME/target/test-     
            classes/$SUDOKU_FILE_TO_VALIDATE

   use following bash shell script located in $SUDOKU_POM_HOME/src/main/scripts (bash SH like equivalently BAT)
   
       $ . validate.sh valid.txt 

## 6. EXAMPLES of usage:
    
    run BUILD & TESTS
    $ mvn clean package    (from directory where pom.xls is located)
    
    run SCRIPT:  (from directory where scripts are located)
    
    $ . validate.sh invalidsolution.txt
    $ . validate.sh validsolution.txt

    run APP directly:
    
    $ java -cp /Users/kris/workspace-oxgen/sudokuvalidator/target/sudokuvalidator-0.0.1-SNAPSHOT.jar 
    sudokuvalidator.SudokuSolutionValidator /Users/kris/workspace-oxgen/sudokuvalidator/target/test-
    classes/validsolution.txt

    $ java -cp /Users/kris/workspace-oxgen/sudokuvalidator/target/sudokuvalidator-0.0.1-SNAPSHOT.jar 
    sudokuvalidator.SudokuSolutionValidator /Users/kris/workspace-oxgen/sudokuvalidator/target/test-
    classes/invalidsolution.txt


    $java -cp /Users/kris/workspace-oxgen/sudokuvalidator/target/sudokuvalidator-0.0.1-SNAPSHOT.jar 
    sudokuvalidator.SudokuSolutionValidator /Users/kris/workspace-oxgen/sudokuvalidator/target/test-
    classes/toValidateSolution.txt


# WINDOWS

    Set environment variables:
  
    e.g. 
     
    JAVA_HOME="C:\kriskalwala\Java\jdk1.8.0_111"
    Path=D:\kriskalwala\Java\jdk1.8.0_111\bin;D:\apps\apache-maven-3.5.0\bin;
    M2_HOME=D:\apps\apache-maven-3.5.0\
    
    Set SUDOKU_POM_HOME:
    
    SET SUDOKU_POM_HOME="here_goes_abolutepath_to_pom.xml"
    
    run:
    
    validate.bat (located in project in the same directory as validate.sh)
