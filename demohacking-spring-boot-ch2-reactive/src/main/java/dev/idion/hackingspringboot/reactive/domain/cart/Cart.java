package dev.idion.hackingspringboot.reactive.domain.cart;

import dev.idion.hackingspringboot.reactive.domain.cartitem.CartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Id;

public class Cart {

  private @Id String id;
  private List<CartItem> cartItems;

  private Cart() {}

  public Cart(String id) {
    this(id, new ArrayList<>());
  }

  public Cart(String id, List<CartItem> cartItems) {
    this.id = id;
    this.cartItems = cartItems;
  }

  public String getId() {
    return id;
  }

  public List<CartItem> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return Objects.equals(getCartItems(), cart.getCartItems());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCartItems());
  }
}
