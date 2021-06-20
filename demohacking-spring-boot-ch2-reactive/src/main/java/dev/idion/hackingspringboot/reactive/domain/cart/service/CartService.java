package dev.idion.hackingspringboot.reactive.domain.cart.service;

import dev.idion.hackingspringboot.reactive.domain.cart.Cart;
import dev.idion.hackingspringboot.reactive.domain.cart.repository.CartRepository;
import dev.idion.hackingspringboot.reactive.domain.cartitem.CartItem;
import dev.idion.hackingspringboot.reactive.domain.item.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

  private final ItemRepository itemRepository;
  private final CartRepository cartRepository;

  public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
  }

  public Mono<Cart> addToCart(String cartId, String id) {
    return this.cartRepository.findById(cartId)
        .log("foundCart")
        .defaultIfEmpty(new Cart(cartId))
        .log("emptyCart")
        .flatMap(cart -> cart.getCartItems().stream()
            .filter(cartItem -> cartItem.getItem()
                .getId().equals(id))
            .findAny()
            .map(cartItem -> {
              cartItem.increment();
              return Mono.just(cart).log("newCartItem");
            })
            .orElseGet(() -> this.itemRepository.findById(id)
                .log("fetchedItem")
                .map(CartItem::new)
                .log("cartItem")
                .map(cartItem -> {
                  cart.getCartItems().add(cartItem);
                  return cart;
                }).log("addedCartItem")
            ))
        .log("cartWithAnotherItem")
        .flatMap(this.cartRepository::save)
        .log("savedCart");
  }
}
