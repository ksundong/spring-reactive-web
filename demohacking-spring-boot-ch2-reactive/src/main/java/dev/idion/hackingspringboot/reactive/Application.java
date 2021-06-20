package dev.idion.hackingspringboot.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.result.view.ViewResolutionResultHandler;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    BlockHound.builder()
        .allowBlockingCallsInside(
            ViewResolutionResultHandler.class.getCanonicalName(), "render")
        .install();

    SpringApplication.run(Application.class, args);
  }

}
