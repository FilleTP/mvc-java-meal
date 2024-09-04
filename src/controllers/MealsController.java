package controllers;

import java.util.List;
import models.Meal;
import repositories.MealRepository;
import views.MealsView;

public class MealsController {
  private MealRepository mealRepository;
  private MealsView mealsView = new MealsView();

  public MealsController(MealRepository mealRepository) {
    this.mealRepository = mealRepository;
  }

  public void list() {
    List<Meal> meals = mealRepository.getMeals();
    this.mealsView.display(meals);
  }

  public void create() {
    String name = this.mealsView.askName();
    int price = this.mealsView.askPrice();
    Meal meal = new Meal(name, price);
    this.mealRepository.addMeal(meal);
  }
}
