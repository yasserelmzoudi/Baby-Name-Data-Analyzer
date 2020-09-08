data
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 


Name: Yasser Elmzoudi (ye9)

### Timeline

Start Date: August 22, 2020

Finish Date: September 8, 2020

Hours Spent: 40

### Resources Used
Stack Overflow, Piazza, Resources provided on class website

### Running the Program
Classes:
- Individual
    - Constructed from a name, gender, and number of occurrences
    - Overrides Object's toString, equals, and hashCode methods for comparison
- YearOfBirthFile
    - Keeps track of List of names associated with the year
    - Keeps track of index where the gender switches
    - Computes rank, number of total occurrences, and other necessary information through methods
- DataReader
    - Constructed from a directory name and/or a boolean signifying if the source is a URL
    - Opens and reads data files, making YearOfBirthFile Objects for each year in List
    - Creates Individual Objects from each line of data and then inserts them into a YearOfBirthFile's List of Names field
- BabyNamesAnalysis
    - Constructed from a DataReader to have access to read data
    - Different methods represent the possible questions that could be asked
    - These methods call the methods found in YearOfBirthFile appropriately to conduct necessary analysis

Main class:
* BabyNamesAnalysis.java is the class containing the main method that runs on the complete data set.

Possible Data Sources
1. Complete Data Set
    - When building a new DataReader, pass in "ssa_complete" as the first parameter signifying that directory and false as the second parameter, signifying that that data source is not a URL 
1. Test Data Set
    - When building a new DataReader, pass in "read_test" (or any desired directory for tests) as the first parameter signifying that directory and false as the second parameter, signifying that that data source is not a URL 
1. URL
    - When building a new DataReader, pass in "" as the first parameter as the directory is obsolete for this source and true as the second parameter, signifying that that data source is a URL 


### Notes/Assumptions
* When reading from URL, assumes file name starts with "yob"
* Assumes years are 4 digits long
* Assumes names for a given gender are not repeated in a given data file

### Impressions
* This project proved to be imperative in showing me the benefit of writing clean and consolidated code. Many of the questions that were asked regarding the data set built off of one another and therefore, through correct planning and anticipation, could be addressed using parts of previously answered questions. Overall, this project was a great first step into the process of software design and implementation and only a preview of what's to come.

