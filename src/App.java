import repositories.*;

import java.io.File;
import controllers.*;

public class App {
  public static void main(String[] args) {
    String csvFilePath = "./meals.csv";
    File file = new File(csvFilePath);
    System.out.println("CSV file path: " + file.getAbsolutePath());
    MealRepository mealRepository = new MealRepository(csvFilePath);
    MealsController mealsController = new MealsController(mealRepository);
    Router routerInit = new Router(mealsController);
    routerInit.run();
  }
}
