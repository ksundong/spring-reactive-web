package dev.idion.hackingspringboot.reactive;

import dev.idion.hackingspringboot.reactive.domain.item.Item;
import dev.idion.hackingspringboot.reactive.domain.item.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

  @Bean
  CommandLineRunner initialize(BlockingItemRepository repository) {
    return args -> {
      repository.save(new Item("Alf alarm clock", 19.99));
      repository.save(new Item("Smurf TV tray", 24.99));
    };
  }
}
