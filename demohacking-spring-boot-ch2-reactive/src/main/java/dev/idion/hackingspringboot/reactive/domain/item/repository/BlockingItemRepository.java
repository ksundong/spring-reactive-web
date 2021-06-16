package dev.idion.hackingspringboot.reactive.domain.item.repository;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {

}
