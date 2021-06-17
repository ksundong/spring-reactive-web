package dev.idion.hackingspringboot.reactive.domain.cart.repository;

import dev.idion.hackingspringboot.reactive.domain.cart.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {

}
