package hazelcast.startstop.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
  "hazelcast.startstop.core",
  "hazelcast.startstop.webapp"
})
public class WebConfig {}

