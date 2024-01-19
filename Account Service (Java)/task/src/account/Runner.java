package account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



//@Component
public class Runner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    @Override
    public void run(String... args) {
        LOGGER.error("ERROR!");
        LOGGER.warn("WARN!");
        LOGGER.info("INFO!");
        LOGGER.debug("DEBUG!");
        LOGGER.trace("TRACE!");
    }
}