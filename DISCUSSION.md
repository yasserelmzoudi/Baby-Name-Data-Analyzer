##Yasser Elmzoudi (ye9)
##Wyatt Focht (wgf6) 
###September 4, 2020

* The main code smell in my code is duplication. This is primarily evident in my Questions class
considering that there are certain Questions that reuse certain functions, such as retrieving files 
from a certain range of years
* My YearOfBirthFile class helps with understanding as it compartmentalizes the date for a single 
year and makes it easily accessible for later analysis. A piece of code that obscures understanding 
is my readData method in DataReader considering the fact that it does many tasks such as parse each line
of data and keep track of the gender change index, all of which could be further split up in other more 
descriptive methods
* Names in the code that are helpful are the variable names found in my Individual class considering that 
they work to effectively describe the traits of a given individual. Variable names that could make my code 
more readable are those found in my Questions class given that every method uses a "result" variable that 
is returned to signify the answer to the given Question
* A method that I could include to make my code more readable is one that retrieves data from a given range
of years so that I do not have this duplicated code in all of the Questions that refer to a range of years