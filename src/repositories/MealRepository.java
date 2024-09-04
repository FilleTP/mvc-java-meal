package repositories;

import models.Meal;
import java.util.ArrayList;
import java.util.List;

public class MealRepository {
  private List<Meal> meals = new ArrayList<>();

  public void addMeal(Meal meal) {
    this.meals.add(meal);
  }

  public List<Meal> getMeals() {
    return this.meals;
  }
}
