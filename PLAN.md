# Data Plan
## Yasser Elmzoudi - ye9

This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci307/current/assign/01_data/):


### What is the answer to the two questions below for the data file yob1900.txt (pick a letter that makes it easy to answer)? 

### Describe the algorithm you would use to answer each one.
1. Given a year, what is the ***top ranked*** name for each gender (M and F)?
    - For this question, I would first utilize the given year (**YYYY**)  to load the appropriate data file (yob**YYYY**.txt) (see assumptions above). This would be accomplsihed by utilizing the *class.getClassLoader().getResource* method in order to load the necessary resources from the classpath rather than a specific file location, allowing for fluidity in the code. I would then use the *Files.readAllLines()* to read all of the lines in the given file. For each line, I would split the String based on ",". I would then check the gender associated with the first name and store that name as the top ranked name for that gender. Then, I would iterate over the remaining lines until the gender of a given individual is different than the gender of the top ranked individual. Then, I would store the name of that individual as the top ranked name of the opposite gender as the first. The top ranked names would then be returned. If only one gender is found in the file, this would be signified in the returned String.

1. Given a year, a gender, and a letter, how many names ***start*** with that letter and how many ***total*** babies were given those names?
    - For this question, I would first utilize the given year (**YYYY**)  to load the appropriate data file (yob**YYYY**.txt) (see assumptions above). This would be accomplsihed by utilizing the *class.getClassLoader().getResource* method in order to load the necessary resources from the classpath rather than a specific file location, allowing for fluidity in the code. I would then use the *Files.readAllLines()* to read all of the lines in the given file. For each line, I would split the String based on ",". I would then check to see if the given gender is the same as the gender of the first individual. If it is not, I would then interate though all of the lines until I reach the first individual that corresponds to the given gender. I would then increment a *totalNamesFirstLetter* variable by one and a *totalOccurrencesFirstLetter* variable by the number of occurrences associated with the individual. I would then iterate through the remaining lines and continue to increment the two variables.

### Likely you may not even need a data structure to answer the questions below, but what about some of the questions in Part 2?


### What are some ways you could test your code to make sure it is giving the correct answer (think beyond just choosing "lucky" parameter values)?



### What kinds of things make the second question harder to test?

### What kind of errors might you expect to encounter based on the questions or in the dataset?

### How would you detect those errors and what would a reasonable "answer" be in such cases?

### How would your algorithm and testing need to change (if at all) to handle multiple files (i.e., a range of years)?

