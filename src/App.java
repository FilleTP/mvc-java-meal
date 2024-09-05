import repositories.*;
import controllers.*;

public class App {
  public static void main(String[] args) {
    String csvFilePath = "./meals.csv";
    MealRepository mealRepository = new MealRepository(csvFilePath);
    MealsController mealsController = new MealsController(mealRepository);
    Router routerInit = new Router(mealsController);
    routerInit.run();
  }
}
