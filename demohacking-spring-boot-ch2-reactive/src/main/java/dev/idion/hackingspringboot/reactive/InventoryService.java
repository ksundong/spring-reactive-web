package dev.idion.hackingspringboot.reactive;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import dev.idion.hackingspringboot.reactive.domain.item.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InventoryService {

  private final ItemRepository repository;

  public InventoryService(ItemRepository repository) {
    this.repository = repository;
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

    return repository.findAll(probe);
  }
}