package dev.idion.hackingspringboot.reactive;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import dev.idion.hackingspringboot.reactive.domain.cart.Cart;
import dev.idion.hackingspringboot.reactive.domain.cart.repository.CartRepository;
import dev.idion.hackingspringboot.reactive.domain.cartitem.CartItem;
import dev.idion.hackingspringboot.reactive.domain.item.Item;
import dev.idion.hackingspringboot.reactive.domain.item.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService {

  private final ItemRepository itemRepository;
  private final CartRepository cartRepository;

  public InventoryService(ItemRepository itemRepository, CartRepository cartRepository) {
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
  }

  public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
    Item item = new Item(name, description, 0.0);

    ExampleMatcher matcher = (useAnd
        ? ExampleMatcher.matchingAll()
        : ExampleMatcher.matchingAny()
            .withStringMatcher(StringMatcher.CONTAINING)
            .withIgnoreCase()
            .withIgnorePaths("price"));

    Example<Item> probe = Example.of(item, matcher);

    return itemRepository.findAll(probe);
  }

  public Mono<Cart> addItemToCart(String cartId, String itemId) {
    return this.cartRepository.findById(cartId)
        .log("foundCart")
        .defaultIfEmpty(new Cart(cartId))
        .log("emptyCart")
        .flatMap(cart -> cart.getCartItems().stream()
            .filter(cartItem -> cartItem.getItem()
                .getId().equals(itemId))
            .findAny()
            .map(cartItem -> {
              cartItem.increment();
              return Mono.just(cart).log("newCartItem");
            })
            .orElseGet(() -> this.itemRepository.findById(itemId)
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
