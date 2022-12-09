package com.maersk.ohm.sit.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@Component
@ConfigurationProperties(prefix="e2eframework")
@EnableConfigurationProperties(ConfigReader.class)
public class ConfigReader {

    @Value("${api.base-uri}")
    private String baseURI;

    @Value("${api.base-uri-start}")
    private String baseURIStart;

    @Value("${api.base-uri-ui}")
    private String baseURIUI;

    @Value("${api.version}")
    private String apiVersion;

}
