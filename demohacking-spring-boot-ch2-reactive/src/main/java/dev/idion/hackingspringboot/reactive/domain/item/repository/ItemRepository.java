package dev.idion.hackingspringboot.reactive.domain.item.repository;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {

}
