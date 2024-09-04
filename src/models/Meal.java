package models;

public class Meal {
  private String name;
  private int price;

  public Meal(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }
}
