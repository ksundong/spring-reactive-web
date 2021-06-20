package dev.idion.hackingspringboot.reactive.domain.item.repository;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {

}
