package dev.idion.hackingspringboot.reactive;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;

import dev.idion.hackingspringboot.reactive.domain.cart.Cart;
import dev.idion.hackingspringboot.reactive.domain.cart.repository.CartRepository;
import dev.idion.hackingspringboot.reactive.domain.cartitem.CartItem;
import dev.idion.hackingspringboot.reactive.domain.item.Item;
import dev.idion.hackingspringboot.reactive.domain.item.repository.ItemRepository;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class InventoryServiceUnitTest {

  InventoryService inventoryService;

  @MockBean private ItemRepository itemRepository;

  @MockBean private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    // 테스트 데이터 정의
    Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
    CartItem sampleCartItem = new CartItem(sampleItem);
    Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

    // 협력자와의 상호작용 정의
    when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
    when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
    when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

    inventoryService = new InventoryService(itemRepository, cartRepository);
  }
}
