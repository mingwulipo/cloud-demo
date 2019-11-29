package org.study.cloud.common.config.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//@Component
public class EsConfig {

    @Autowired
    private EsProperties esProperties;

    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    private void initClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(esProperties.getHost(), Integer.valueOf(esProperties.getPort()), esProperties.getSchema()));

        restClientBuilder.setRequestConfigCallback(
                requestConfigBuilder -> {
                    requestConfigBuilder.setConnectTimeout(esProperties.getConnectTimeout());
                    requestConfigBuilder.setSocketTimeout(esProperties.getSocketTimeout());
                    return requestConfigBuilder;
                });

        //其他项目依赖正常，这里依赖就有问题，依赖6.7.2结果5.6.14也依赖进来了，下面构造器报错，好像es使用的是5.6.14
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        System.out.println("ok");
    }

    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }
}
