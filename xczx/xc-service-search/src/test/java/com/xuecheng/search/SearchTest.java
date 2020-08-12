package com.xuecheng.search;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void search() throws IOException {
        // 搜索文档
        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 搜索方式
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 设置结果集显示字段
        // searchSourceBuilder.fetchSource(new String[] {}, new String[] {});

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(JSON.toJSONString(map));
        }
    }
}
