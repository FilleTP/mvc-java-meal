import repositories.*;
import controllers.*;

public class App {
  public static void main(String[] args) {
    MealRepository mealRepository = new MealRepository();
    MealsController mealsController = new MealsController(mealRepository);
    Router routerInit = new Router(mealsController);
    routerInit.run();
  }
}
