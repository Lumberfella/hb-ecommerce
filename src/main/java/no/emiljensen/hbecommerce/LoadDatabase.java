package no.emiljensen.hbecommerce;

import no.emiljensen.hbecommerce.watch.Watch;
import no.emiljensen.hbecommerce.watch.WatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WatchRepository watchRepository) {
        return args -> {
            log.info("Load " + watchRepository.save(new Watch("001", "Rolex", 100, "3 for 200")));
            log.info("Load " + watchRepository.save(new Watch("002", "Michael Kors", 80, "2 for 120")));
            log.info("Load " + watchRepository.save(new Watch("003", "Swatch", 50, null)));
            log.info("Load " + watchRepository.save(new Watch("004", "Casio", 30, null)));
        };
    }
}
