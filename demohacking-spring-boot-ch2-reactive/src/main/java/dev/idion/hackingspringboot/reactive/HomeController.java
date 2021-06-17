package dev.idion.hackingspringboot.reactive;

import dev.idion.hackingspringboot.reactive.domain.cart.Cart;
import dev.idion.hackingspringboot.reactive.domain.cart.repository.CartRepository;
import dev.idion.hackingspringboot.reactive.domain.item.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

  private static final String CART_ID = "My Cart";
  private final ItemRepository itemRepository;
  private final CartRepository cartRepository;

  public HomeController(ItemRepository itemRepository, CartRepository cartRepository) {
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
  }

  @GetMapping
  public Mono<Rendering> home() {
    return Mono.just(
        Rendering.view("home.html")
            .modelAttribute("items", this.itemRepository.findAll())
            .modelAttribute("cart", this.cartRepository.findById(CART_ID).defaultIfEmpty(new Cart(CART_ID)))
            .build()
    );
  }
}