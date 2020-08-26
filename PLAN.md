# Data Plan
## Yasser Elmzoudi - ye9

This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci307/current/assign/01_data/):


### What is the answer to the two questions below for the data file yob1900.txt (pick a letter that makes it easy to answer)? 
1.  Top Ranked 1900 Female Name:  **Mary**

    Top Ranked 1900 Male Name:    **John**

1.  Number of 1900 Males Names Starting with 'X':                  **0**

    Total Number of 1900 Male Babies with Names Starting with 'X':  **0**

### Describe the algorithm you would use to answer each one.
1. Given a year, what is the ***top ranked*** name for each gender (M and F)?
    - For this question, I would first utilize the given year (**YYYY**)  to load the appropriate data file (yob**YYYY**.txt) (see assumptions above). This would be accomplsihed by utilizing the *class.getClassLoader().getResource* method in order to load the necessary resources from the classpath rather than a specific file location, allowing for fluidity in the code. I would then use the *Files.readAllLines()* to read all of the lines in the given file. For each line, I would split the String based on ",". I would then check the gender associated with the first name and store that name as the top ranked name for that gender. Then, I would iterate over the remaining lines until the gender of a given individual is different than the gender of the top ranked individual. Then, I would store the name of that individual as the top ranked name of the opposite gender as the first. The top ranked names would then be returned. If only one gender is found in the file, this would be signified in the returned String.

1. Given a year, a gender, and a letter, how many names ***start*** with that letter and how many ***total*** babies were given those names?
    - For this question, I would first utilize the given year (**YYYY**)  to load the appropriate data file (yob**YYYY**.txt) (see assumptions above). This would be accomplsihed by utilizing the *class.getClassLoader().getResource* method in order to load the necessary resources from the classpath rather than a specific file location, allowing for fluidity in the code. I would then use the *Files.readAllLines()* to read all of the lines in the given file. For each line, I would split the String based on ",". I would then check to see if the given gender is the same as the gender of the first individual. If it is not, I would then interate though all of the lines until I reach the first individual that corresponds to the given gender. I would then increment a *totalNamesFirstLetter* variable by one and a *totalOccurrencesFirstLetter* variable by the number of occurrences associated with the individual. I would then iterate through the remaining lines and continue to increment the two variables.

### Likely you may not even need a data structure to answer the questions below, but what about some of the questions in Part 2?

|                 |                                                                                  2D Array                                                                                   |                                                                                                                                            Nested HashMap                                                                                                                                            |                                                                                                                                              LinkedHashMap                                                                                                                                              |                                                                                                                                                ArrayList                                                                                                                                                |
|:---------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| **Description** | 2D Array where the rows of the array refer to different individuals and the columns refer to a given individual's name, gender, and the number of occurrences of their name |                    Nested HashMap where the first key corresponds to the first letter in an individual's name. The value of that key would then be a HashMap whose key would be an individual's name, and whose value would be the number of occurrences of the individual's name                    |                                                                             LinkedHashMap where the key corresponds to an individual's name and the value corresponds to the number of occurrences for the given individual                                                                             |                                                   ArrayList of Names for a given file where the indicies correspond to the rank of the Name. A "Gender-Switch" index would be kept track of that would signify when the gender is no longer the same                                                    |
|    **Pros**     |                                                                    The ranking of the names is preserved                                                                    | O(1) complexity for retrieving values. Also, as many of the questions in [Part 2](https://www2.cs.duke.edu/courses/fall20/compsci307d/assign/01_data/part2.php) deal with the first letter of the individual's name, incorporating this into the utilized data structure simplifies the coming tasks | O(1) complexity for retrieving values. Also, preserves insertion order which thus would preserve rank of the occurrences of names. This is important as many of the questions in [Part 2](https://www2.cs.duke.edu/courses/fall20/compsci307d/assign/01_data/part2.php) deal with the ranking of names. | O(1) complexity for retrieving values. Also, preserves insertion order which thus would preserve rank of the occurrences of names. This is important as many of the questions in [Part 2](https://www2.cs.duke.edu/courses/fall20/compsci307d/assign/01_data/part2.php) deal with the ranking of names. |
|    **Cons**     |                                                          With 2D arrays, O(*row\*column*) complexity for traversal                                                          |                                                                                                       With HashMaps, insertion order is not preserved, resulting in the potential loss of rank                                                                                                       |                                                                                             With LinkedHashMaps, most memory is consumed as a doubly-linked list is maintained, running through all entries                                                                                             |                                                                              Would have to be incorporated within the bounds of a singular file, making it less direct to access other information pertaining to the data                                                                               |

### What are some ways you could test your code to make sure it is giving the correct answer (think beyond just choosing "lucky" parameter values)?
- Create a custom test file with one letter names and relatively easy number of occurrences to sum

| Example Test File | 
| ------ |
| A,M,100 |
| A,M,50 |
| A,M,25 |

- Create JUnit tests for each class utilized and have multiple tests for each method in which an expected output is compared to the actual output


### What kinds of things make the second question harder to test?
- More iteration is required as half of the asnwer cannot be instantly determined from the first line as in the first question
- More information must be tracked and returned

### What kind of errors might you expect to encounter based on the questions or in the dataset?
- Gender given is not represented in data set   
    - Only M/F in data set, not both
- Year given is not in range of data set        
    - 2020
- Year given is not a valid year                
    - 2*!9
- Gender given is not M or F                    
    - X
- Letter given is not a valid letter            
    - ^
- File is not a valid file


### How would you detect those errors and what would a reasonable "answer" be in such cases?

### How would your algorithm and testing need to change (if at all) to handle multiple files (i.e., a range of years)?

