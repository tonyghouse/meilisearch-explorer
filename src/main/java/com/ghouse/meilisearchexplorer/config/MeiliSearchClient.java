package com.ghouse.meilisearchexplorer.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeiliSearchClient {


    @Bean
    public Client getMeiliClient() {
        return new Client(new Config("http://localhost:7700", "king"));
    }
}
