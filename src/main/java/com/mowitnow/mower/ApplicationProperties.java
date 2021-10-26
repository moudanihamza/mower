package com.mowitnow.mower;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mower")
@Configuration
@Data
public class ApplicationProperties {

    private String configPath;

    private boolean autoStart;

    private String elementsSeparator;

    private int minX;

    private int minY;


}
