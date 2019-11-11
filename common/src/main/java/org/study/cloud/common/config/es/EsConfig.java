package org.study.cloud.common.config.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class EsConfig {

    @Autowired
    private EsProperties esProperties;

    @Bean
    private RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(esProperties.getHost(), Integer.valueOf(esProperties.getPort()), esProperties.getSchema()));

        if (!StringUtils.isEmpty(esProperties.getAccount()) && !StringUtils.isEmpty(esProperties.getPassword())) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esProperties.getAccount(), esProperties.getPassword()));
            restClientBuilder.setRequestConfigCallback(
                    requestConfigBuilder -> {
                        requestConfigBuilder.setConnectTimeout(esProperties.getConnectTimeout());
                        requestConfigBuilder.setSocketTimeout(esProperties.getSocketTimeout());
                        return requestConfigBuilder;
                    }).setHttpClientConfigCallback(httpClientBuilder -> {
                        httpClientBuilder.disableAuthCaching();
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    });

        } else {
            restClientBuilder.setRequestConfigCallback(
                    requestConfigBuilder -> {
                        requestConfigBuilder.setConnectTimeout(esProperties.getConnectTimeout());
                        requestConfigBuilder.setSocketTimeout(esProperties.getSocketTimeout());
                        return requestConfigBuilder;
                    });
        }

        return new RestHighLevelClient(restClientBuilder);
    }

}
