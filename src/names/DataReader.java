package names;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yasser Elmzoudi
 * <p>
 * The purpose of DataReader is to load the baby name data from a variety of potential sources and
 * create YearOfBirthFiles from the read years in the data. This class then creates Individuals that
 * are placed into their appropriate YearOfBirthFiles. DataReader also contains methods pertaining
 * to the access of the read data, either in the form of a perticular YearOfBirthFile, or a range of
 * files or individuals.
 * <p>
 * This class is able to handle empty or incorrect data sources being sent to it by throwing an
 * Exception when an invalid file or directory is provided
 * <p>
 * A DataReader could be created by calling:
 * <p>
 * DataReader testReading = new DataReader("ssa_complete", false);
 * <p>
 * Where "ssa_complete" is the directory to be read from and false signifies that the data source is
 * not a URL
 * <p>
 * This class assumes that the only URL that can be utilized to read data from is URL_LOCATION
 */
public class DataReader {

  private static final String URL_LOCATION = "https://www2.cs.duke.edu/courses/fall20/compsci307d/assign/01_data/data/ssa_complete/";
  private static final String FILE_START = "yob";
  private static final String FILE_EXTENSION = ".txt";
  private static final int NAME_INDEX = 0;
  private static final int GENDER_INDEX = 1;
  private static final int OCCURRENCES_INDEX = 2;
  private static final int YEAR_LENGTH = 4;

  private List<Integer> years;
  private List<YearOfBirthFile> data;
  private String dataSet;

  /**
   * Constructs a DataReader from a String and a Boolean that signifies that source of data
   *
   * @param dataSet String representing the name of the directory to be read from
   * @param isURL   Boolean representing if the data source is a URL
   * @throws Exception Handles error of empty or incorrect data source provided
   */
  public DataReader(String dataSet, boolean isURL) throws Exception {
    this.dataSet = dataSet;
    years = new ArrayList<>();
    data = new ArrayList<>();
    readDataSet(dataSet, isURL);
  }

  private void readDataSet(String dataSet, boolean isURL) throws Exception {
    try {
      if (isURL) {
        setYearsFromURL();
      } else {
        setYearFromDirectory(dataSet);
      }

      Collections.sort(years);
      for (int year : years) {
        List<String> readData = new ArrayList<>();
        if (isURL) {
          readData = readFromURL(year);
        } else {
          readData = readFromDirectory(year);
        }

        YearOfBirthFile dataFile = createYearOfBirthFile(year);

        String currentGender = getIndividualData(readData.get(0), GENDER_INDEX);
        boolean unchanged = true;

        iterateThroughDataLines(dataFile, readData, currentGender, unchanged);
      }
    } catch (Exception e) {
      throw new Exception("Invalid file", e);
    }
  }

  private void setYearsFromURL() throws Exception {
    URL webData = new URL(URL_LOCATION);
    BufferedReader webReader = new BufferedReader(new InputStreamReader(webData.openStream()));

    String file;
    while ((file = webReader.readLine()) != null) {
      String removedHTML = file.replaceAll("<.*?>", "");
      if (removedHTML.startsWith(FILE_START)) {
        int year = getYearFromFileName(removedHTML);
        years.add(year);
      }
    }
  }

  private void setYearFromDirectory(String dataSet) throws URISyntaxException {
    Path path = Paths.get(DataReader.class.getClassLoader().getResource(dataSet).toURI());
    File[] files = path.toFile().listFiles();
    Arrays.sort(files);
    for (File file : files) {
      int year = getYearFromFileName(file.getName());
      years.add(year);
    }
  }

  private int getYearFromFileName(String fileName) {
    String year = "";
    for (char character : fileName.toCharArray()) {
      if (Character.isDigit(character) && year.length() < YEAR_LENGTH) {
        year += character;
      }
    }
    return Integer.parseInt(year);
  }

  private List<String> readFromDirectory(int year) throws Exception {
    Path path = Paths.get(DataReader.class.getClassLoader()
        .getResource(dataSet + "/" + FILE_START + year + FILE_EXTENSION).toURI());

    List<String> readData = Files.readAllLines(path);
    return readData;
  }

  private List<String> readFromURL(int year) throws Exception {
    String urlName = FILE_START + year + FILE_EXTENSION;
    URL webData = new URL(URL_LOCATION + urlName);
    BufferedReader webReader = new BufferedReader(new InputStreamReader(webData.openStream()));

    String individualData;
    List<String> readData = new ArrayList<>();

    while ((individualData = webReader.readLine()) != null) {
      readData.add(individualData);
    }
    return readData;
  }

  private void iterateThroughDataLines(YearOfBirthFile dataFile, List<String> readFile,
      String currentGender, boolean unchanged) {
    for (int currentLine = 0; currentLine < readFile.size(); currentLine++) {
      String line = readFile.get(currentLine);

      String name = getIndividualData(line, NAME_INDEX);
      String gender = getIndividualData(line, GENDER_INDEX);
      int count = Integer.parseInt(getIndividualData(line, OCCURRENCES_INDEX));

      unchanged = setGenderChangeIndexForYearOfBirthFile(dataFile, currentGender, unchanged,
          currentLine,
          gender);

      addIndividualToYearOfBirthFile(dataFile, currentLine, name, gender, count);
    }
  }

  private YearOfBirthFile createYearOfBirthFile(int year) {
    YearOfBirthFile dataFile = new YearOfBirthFile(year);
    data.add(dataFile);
    return dataFile;
  }

  private String getIndividualData(String line, int index) {
    return line.split(",")[index];
  }

  private boolean setGenderChangeIndexForYearOfBirthFile(YearOfBirthFile dataFile,
      String currentGender, boolean unchanged, int currentLine, String gender) {
    if (unchanged && !gender.equals(currentGender)) {
      dataFile.setGenderChangeIndex(currentLine);
      unchanged = false;
    }
    return unchanged;
  }

  private void addIndividualToYearOfBirthFile(YearOfBirthFile dataFile, int currentLine,
      String name, String gender, int count) {
    Individual person = new Individual(name, gender, count,
        currentLine + 1 - dataFile.getGenderChangeIndex(), dataFile);
    dataFile.add(person);
  }

  /**
   * Returns years that have been read by DataReader
   *
   * @return List of Integers representing read years
   */
  public List<Integer> getYears() {
    return years;
  }

  /**
   * Returns YearOfBirthFiles that have been created by DataReader
   *
   * @return List of YearOfBirthFiles representing read data for each read year
   */
  public List<YearOfBirthFile> getData() {
    return data;
  }

  /**
   * Retrieves YearOfBirthFile from a corresponding year
   *
   * @param year int representing the year of the desired YearOfBirthFile
   * @return YearOfBirthFile if year is found in read years or null otherwise
   */
  public YearOfBirthFile getYearOfBirthFile(int year) {
    for (YearOfBirthFile file : data) {
      if (file.getMyYear() == year) {
        return file;
      }
    }
    return null;
  }

  /**
   * Returns YearOfBirthFiles corresponding to the given range of years
   *
   * @param startYear int representing the desired start year
   * @param endYear   int representing the desired end year
   * @return List of YearOfBirthFiles that have been created for years between start year and end
   * year inclusive
   */
  public List<YearOfBirthFile> getFilesInRange(int startYear, int endYear) {
    List<YearOfBirthFile> filesInRange = new ArrayList<>();
    for (int year = startYear; year <= endYear; year++) {
      if (getYears().contains(year)) {
        filesInRange.add(getYearOfBirthFile(year));
      }
    }

    return filesInRange;
  }

  /**
   * Retrieves the first year data has been read for
   *
   * @return int representing the first year data has been read for
   */
  public int getStartYear() {
    Collections.sort(years);
    return years.get(0);
  }

  /**
   * Retrieves the last year data has been read for
   *
   * @return int representing the last year data has been read for
   */
  public int getEndYear() {
    Collections.sort(years);
    return years.get(years.size() - 1);
  }

  /**
   * Returns Individuals corresponding to the given range of years
   *
   * @param startYear int representing the desired start year
   * @param endYear   int representing the desired end year
   * @return List of Individuals that have been created for years between start year and end year
   * inclusive
   */
  public Set<Individual> getIndividualsInRange(int startYear, int endYear) {
    Set<Individual> individualsInRange = new HashSet<>();

    for (YearOfBirthFile file : getFilesInRange(startYear, endYear)) {
      individualsInRange.addAll(file.getMyIndividuals());
    }

    return individualsInRange;
  }

  /**
   * Returns the total number of babies born within a range of years whose name starts with a
   * particular letter
   *
   * @param startYear int representing the desired start year
   * @param endYear   int representing the desired end year
   * @param gender    String representing desired gender
   * @param letter    char representing desired letter
   * @return double representing the total number of babies born within a range of years whose name
   * starts with the provided letter
   */
  public double nameBabyCountFromLetter(int startYear, int endYear, String gender, char letter) {
    double letterCount = 0.0;

    for (YearOfBirthFile file : getFilesInRange(startYear, endYear)) {
      letterCount += file.totalBabies(gender, letter);
    }

    return letterCount;
  }

  /**
   * Returns a list of names of individuals born within a range of years whose name starts with a
   * particular letter
   *
   * @param startYear int representing the desired start year
   * @param endYear   int representing the desired end year
   * @param gender    String representing desired gender
   * @param letter    char representing desired letter
   * @return Set of Strings representing the names of individuals born within a range of years whose
   * name starts with a particular letter
   */
  public Set<String> getNamesWithFirstLetter(int startYear, int endYear, String gender,
      char letter) {
    Set<Individual> individualsWithFirstLetter = new HashSet<>();
    Set<String> namesWithFirstLetter = new HashSet<>();

    for (YearOfBirthFile file : getFilesInRange(startYear, endYear)) {
      individualsWithFirstLetter.addAll(file.namesLetter(gender, letter));
      namesWithFirstLetter.addAll(file.getNamesFromIndividuals(individualsWithFirstLetter));

    }

    return namesWithFirstLetter;
  }
}
