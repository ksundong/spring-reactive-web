package dev.idion.hackingspringboot.reactive;

import java.util.StringJoiner;

class Dish {

  private String description;
  private boolean delivered = false;

  public static Dish deliver(Dish dish) {
    Dish deliveredDish = new Dish(dish.description);
    deliveredDish.delivered = true;
    return deliveredDish;
  }

  Dish(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDelivered() {
    return delivered;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
        .add("description='" + description + "'")
        .add("delivered=" + delivered)
        .toString();
  }
}
