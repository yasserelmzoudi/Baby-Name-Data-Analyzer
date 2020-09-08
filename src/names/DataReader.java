package names;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DataReader {

  private List<Integer> years;
  private List<YearOfBirthFile> data;

  public DataReader(String dataSet) throws Exception {
    years = new ArrayList<>();
    data = new ArrayList<>();
    readDataSet(dataSet);
  }

  public void readDataSet(String dataSet) throws Exception {
    try {
      Path path = Paths.get(DataReader.class.getClassLoader().getResource(dataSet).toURI());
      File[] files = path.toFile().listFiles();
      Arrays.sort(files);
      for (File file : files) {
        int year = Integer.parseInt(file.getName().substring(3, file.getName().length() - 4));
        years.add(year);

        YearOfBirthFile dataFile = new YearOfBirthFile(year);
        data.add(dataFile);

        List<String> readFile = Files.readAllLines(file.toPath());

        String currentGender = getIndividualData(readFile.get(0), 1);
        boolean unchanged = true;

        for (int currentLine = 0; currentLine < readFile.size(); currentLine++) {
          String line = readFile.get(currentLine);

          String name = getIndividualData(line, 0);
          String gender = getIndividualData(line, 1);
          int count = Integer.parseInt(getIndividualData(line, 2));

          unchanged = setGenderChangeIndexForYearOfBirthFile(dataFile, currentGender, unchanged,
              currentLine,
              gender);

          addIndividualToYearOfBirthFile(dataFile, currentLine, name, gender, count);
        }
      }
    } catch (Exception e) {
      throw new Exception("Invalid file", e);
    }
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

  public void addIndividualToYearOfBirthFile(YearOfBirthFile dataFile, int currentLine,
      String name, String gender, int count) {
    Individual person = new Individual(name, gender, count,
        currentLine + 1 - dataFile.getGenderChangeIndex(), dataFile);
    dataFile.add(person);
  }

  public List<Integer> getYears() {
    return years;
  }

  public List<YearOfBirthFile> getData() {
    return data;
  }

  public YearOfBirthFile getYearOfBirthFile(int year) {
    for (YearOfBirthFile file : data) {
      if (file.getMyYear() == year) {
        return file;
      }
    }
    return null;
  }

  public List<YearOfBirthFile> getFilesInRange(int startYear, int endYear) {
    List<YearOfBirthFile> filesInRange = new ArrayList<>();
    for (int year = startYear; year <= endYear; year++) {
      if (getYears().contains(year)) {
        filesInRange.add(getYearOfBirthFile(year));
      }
    }

    return filesInRange;
  }

  public int getStartYear() {
    Collections.sort(years);
    return years.get(0);
  }

  public int getEndYear() {
    Collections.sort(years);
    return years.get(years.size() - 1);
  }

  public Set<Individual> getIndividualsInRange(int startYear, int endYear) {
    Set<Individual> individualsInRange = new HashSet<>();

    for (YearOfBirthFile file : getFilesInRange(startYear, endYear)) {
      individualsInRange.addAll(file.getMyIndividuals());
    }

    return individualsInRange;
  }

  public double nameBabyCountFromLetter(int startYear, int endYear, String gender, char letter) {
    double letterCount = 0.0;

    for (YearOfBirthFile file : getFilesInRange(startYear, endYear)) {
      letterCount += file.totalBabies(gender, letter);
    }

    return letterCount;
  }

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
