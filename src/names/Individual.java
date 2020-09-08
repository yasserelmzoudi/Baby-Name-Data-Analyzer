package names;

import java.util.Objects;

public class Individual {

  private String name;
  private String gender;
  private int occurrences;
  private int rank;
  private YearOfBirthFile fileLocatedIn;

  public Individual(String name, String gender, int occurrences, int rank,
      YearOfBirthFile fileLocatedIn) {
    this.name = name;
    this.gender = gender;
    this.occurrences = occurrences;
    this.rank = rank;
    this.fileLocatedIn = fileLocatedIn;
  }

  public Individual(String name, String gender) {
    this(name, gender, -1, -1, null);
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public int getOccurrences() {
    return occurrences;
  }

  public int getRank() {
    return rank;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Individual person = (Individual) other;
    return (name.equalsIgnoreCase(person.name)) && (gender.equalsIgnoreCase(person.gender));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, gender);
  }
}
