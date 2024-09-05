/*View: Meant for displaying information, interface.
  The class has a private field, scanner, that is used to read user input
  It has a method to display all meals to terminal
  It has a method to ask for name of a new meal, and return string input from user
  It has a method to ask for price of a new meal, and return integer input from user
 */
package views;

import java.util.List;
import java.util.Scanner;
import models.Meal;

public class MealsView {
  private Scanner scanner = new Scanner(System.in);

  public void display(List<Meal> meals) {
    for (Meal meal : meals) {
      System.out.println(String.format("Meal: %s, Price: %d €", meal.getName(), meal.getPrice()));
    }
  }

  public String askName() {
    System.out.println("What is the name of the meal?");
    return scanner.nextLine();
  }

  /*publicly accessible instance method that returns an integer
    prints a new line with a question
    starts while loop, with condition - the scanner checks the next input -
    if its not an integer, it prints message as well as consumes the invalid input
    if its valid int, then it creates an int price of it, as well as consumes next empty line char \n
    it then returns price
  */
  public int askPrice() {
    System.out.println("What is the price of the meal?");
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. enter a valid number.");
      scanner.next();
    }
    int price = scanner.nextInt();
    scanner.nextLine();
    return price;
  }
}
