package repositories;

import models.Meal;

import storage.CsvFileWriting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class MealRepository {
  private CsvFileWriting csvFileWriting = new CsvFileWriting();
  private List<Meal> meals = new ArrayList<>();
  private String csvFile;

  public MealRepository(String csvFile) {
    this.csvFile = csvFile;
    loadMealsFromCsv();
  }

  public void addMeal(Meal meal) {
    this.meals.add(meal);
    saveMealsToCsv(meal);
  }

  public List<Meal> getMeals() {
    return this.meals;
  }

  private void loadMealsFromCsv() {
    try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {
      br.readLine();
      String line = "";
      while ((line = br.readLine()) != null) {
        String[] tokens = line.split(",");
        int price = Integer.parseInt(tokens[1]);
        String name = tokens[0];
        meals.add(new Meal(name, price));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveMealsToCsv(Meal meal) {

    String[] headers = new String[] {"name", "price"};
    String[] mealData = new String[] { meal.getName(), String.valueOf(meal.getPrice()) };

    try {
      this.csvFileWriting.writeToCsv(mealData, this.csvFile, headers);
    } catch (IOException ioe) {
      System.out.println("Couldnt save to csv");
    }
  }
}
