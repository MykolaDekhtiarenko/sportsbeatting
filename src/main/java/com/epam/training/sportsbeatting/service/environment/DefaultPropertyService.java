package com.epam.training.sportsbeatting.service.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultPropertyService implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPropertyService.class);

    private static final String NO_PROPERTY_CONFIGURED = "There is no property %s configured. Using default value %s.";

    @Autowired
    private Environment env;

    @Override
    public Optional<String> getProperty(String name) {
        return Optional.ofNullable(env.getProperty(name));
    }

    @Override
    public String getPropertyOrDefault(String name, String defaultValue) {
        String value = env.getProperty(name);
        if (Objects.isNull(value)) {
            LOGGER.error(String.format(NO_PROPERTY_CONFIGURED, name, defaultValue));
            return defaultValue;
        }
        return value;
    }

}
