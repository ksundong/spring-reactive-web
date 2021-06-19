package dev.idion.hackingspringboot.reactive.domain.cartitem;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import java.util.Objects;

public class CartItem {

  private Item item;
  private int quantity;

  private CartItem() {}

  public CartItem(Item item) {
    this.item = item;
    this.quantity = 1;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void increment() {
    quantity += 1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartItem cartItem = (CartItem) o;
    return getQuantity() == cartItem.getQuantity() && Objects.equals(getItem(), cartItem.getItem());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItem(), getQuantity());
  }
}
