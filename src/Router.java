import controllers.MealsController;

public class Router {

  private boolean running = true;
  private MealsController mealsController;

  public Router(MealsController mealsController) {
    this.mealsController = mealsController;
  }

  public void run() {
    while (this.running) {
      System.out.println("1 for all Meals");
      System.out.println("2 for create Meal");
      System.out.println("3 for Quit");

      String input = System.console().readLine();

      switch (input) {
        case "1":
            this.mealsController.list();
            break;
        case "2":
            this.mealsController.create();
            break;
        case "3":
            this.running = false;
            System.out.println("Ok, quitting...");
            break;
        default:
            System.out.println("Please choose a number between 1-3");
            break;
      }
    }
  }
}
