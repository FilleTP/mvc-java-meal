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

  public int askPrice() {
    System.out.println("What is the price of the meal?");
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. enter a valid number.");
      scanner.next();
    }
    return scanner.nextInt();
  }
}
