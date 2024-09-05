package repositories;

import models.Meal;

import storage.CsvFileWriting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealRepository {
  private CsvFileWriting csvFileWriting = new CsvFileWriting();
  private List<Meal> meals = new ArrayList<>();
  private String csvFile;

  public MealRepository(String csvFile) {
    this.csvFile = csvFile;
  }

  public void addMeal(Meal meal) {
    this.meals.add(meal);
    saveMealToCsv(meals);
  }

  public List<Meal> getMeals() {
    return this.meals;
  }

  // To implement load csv method

  private void saveMealToCsv(List<Meal> meals) {
    List<String[]> data = new ArrayList<>();

    data.add(new String[] {"name", "price"});
    for (Meal meal : meals) {
      data.add(new String[] { meal.getName(), String.valueOf(meal.getPrice()) });
    }

    try {
      this.csvFileWriting.writeToCsv(data, this.csvFile);
    } catch (IOException ioe) {
      System.out.println("Couldnt save to csv");
    }
  }
}
