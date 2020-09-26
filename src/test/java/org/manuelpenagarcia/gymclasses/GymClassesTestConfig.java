package org.manuelpenagarcia.gymclasses;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@PropertySource("classpath:application.properties")
@Component
public class GymClassesTestConfig {

}
