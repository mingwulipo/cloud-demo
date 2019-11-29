package org.study.cloud.common.util.es;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.cloud.common.config.es.EsConfig;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-11 10:27
 */
//@Component
@Slf4j
public class EsNoUtil {
    private static final String ORDER_NO = "order_no";
    private static final long ORDER_INIT_NO = 2193737508566913024L;

    @Autowired
    private EsConfig esConfig;

    /**
     * 订单编号
     * @author lipo
     * @date 2019-11-11 11:10
     */
    public long nextOrderNo() {
        try {
            return nextNo(ORDER_NO, ORDER_INIT_NO);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 生成编号
     * @author lipo
     * @date 2019-11-11 11:10
     */
    private long nextNo(String noName, long initNo) throws IOException {
        //查询
        EsNoEntity esNoEntity = get(noName);
        if (esNoEntity == null) {
            //不存在数据，插入初始值
            esNoEntity = new EsNoEntity(noName, initNo, initNo);
        }

        //已经存在
        esNoEntity.setCurrentNo(esNoEntity.getCurrentNo() + 1);

        //保存
        save(noName, esNoEntity);

        return esNoEntity.getCurrentNo();

    }

    private void save(String noName, EsNoEntity esNoEntity) throws IOException {
        IndexRequest request = new IndexRequest(EsNoEntity.INDEX, EsNoEntity.TYPE, noName);
        request.source(esNoEntity);
        esConfig.getRestHighLevelClient().index(request, RequestOptions.DEFAULT);
    }

    /**
     * 查询数据
     * @author lipo
     * @date 2019-11-11 11:37
     */
    private EsNoEntity get(String noName) throws IOException {
        GetRequest getRequest = new GetRequest(EsNoEntity.INDEX, EsNoEntity.TYPE, noName);
        GetResponse getResponse = esConfig.getRestHighLevelClient().get(getRequest, RequestOptions.DEFAULT);
        return JSON.parseObject(getResponse.getSourceAsString(), EsNoEntity.class);
    }

    /**
     * 查询数据是否存在
     * @author lipo
     * @date 2019-11-11 11:08
     */
    private boolean exist(String noName) throws IOException {
        GetRequest request = new GetRequest(EsNoEntity.INDEX, EsNoEntity.TYPE, noName);
        return esConfig.getRestHighLevelClient().exists(request, RequestOptions.DEFAULT);
    }

    @PostConstruct
    public void init() {
        System.out.println(nextOrderNo());
    }

}
