package com.xuecheng.search.service;

import com.xuecheng.framework.domain.course.CoursePub;
import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EsCourseService {

    @Value("${elasticsearch.index}")
    private String index;
    @Value("${elasticsearch.field}")
    private String field;
    @Resource
    RestHighLevelClient restHighLevelClient;

    public QueryResponseResult<CoursePub> list(Integer page, Integer size, CourseSearchParam courseSearchParam) {
        if (courseSearchParam == null) courseSearchParam = new CourseSearchParam();
        // 创建搜索对象
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 创建布尔查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 设置返回结果集显示字段
        String[] fields = field.split(",");
        searchSourceBuilder.fetchSource(fields, new String[]{});

        if (StringUtils.isNotEmpty(courseSearchParam.getKeyword())) {
            // 构建关键字搜索
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(
                    courseSearchParam.getKeyword(), "name", "teachplan", "description");
            multiMatchQueryBuilder.minimumShouldMatch("70%");
            multiMatchQueryBuilder.field("name", 10);
            // 设置multiMatchQueryBuilder到boolQueryBuilder中
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        if (StringUtils.isNotEmpty(courseSearchParam.getMt())) {
            // 构建一级分类搜索
            boolQueryBuilder.filter(QueryBuilders.termQuery("mt",courseSearchParam.getMt()));
        }
        if (StringUtils.isNotEmpty(courseSearchParam.getSt())) {
            // 构建二级分类搜索
            boolQueryBuilder.filter(QueryBuilders.termQuery("st",courseSearchParam.getSt()));
        }
        if (StringUtils.isNotEmpty(courseSearchParam.getGrade())) {
            // 构建难度等级分类搜索
            boolQueryBuilder.filter(QueryBuilders.termQuery("grade",courseSearchParam.getGrade()));
        }
        // 设置boolQueryBuilder到searchSourceBuilder中
        searchSourceBuilder.query(boolQueryBuilder);
        // 设置分页
        if (page <= 0) page = 1;
        if (size <= 0) size = 12;
        int index = (page-1) * size;
        searchSourceBuilder.from(index); //记录数下标
        searchSourceBuilder.size(size);
        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font class='eslight'>");
        highlightBuilder.postTags("</font>");
        // 设置高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        searchSourceBuilder.highlighter(highlightBuilder);

        request.source(searchSourceBuilder);

        QueryResult<CoursePub> queryResult = new QueryResult<>();
        List<CoursePub> coursePubs = new ArrayList<>();
        try {
            // 发起请求，执行搜索
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();

            // 总记录数
            long total = hits.getTotalHits().value;
            queryResult.setTotal(total);
            queryResult.setList(coursePubs);
            SearchHit[] searchHits = hits.getHits();

            for (SearchHit hit : searchHits) {
                CoursePub coursePub = new CoursePub();
                // 获取源文档
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String name = (String) sourceAsMap.get("name");
                // 获取高亮字段
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if (highlightFields != null){
                    HighlightField highlightFieldName = highlightFields.get("name");
                    if (highlightFieldName != null){
                        StringBuilder stringBuffer = new StringBuilder();
                        Text[] fragments = highlightFieldName.fragments();
                        for (Text fragment : fragments) {
                            stringBuffer.append(fragment);
                        }
                        name = stringBuffer.toString();
                    }
                }
                coursePub.setName(name);
                coursePub.setId((String) sourceAsMap.get("id"));

                if (sourceAsMap.get("pic") != null) {
                    coursePub.setPic((String) sourceAsMap.get("pic"));
                }
                if (sourceAsMap.get("price") != null) {
                    coursePub.setPrice(Float.valueOf(sourceAsMap.get("price").toString()));
                }
                if (sourceAsMap.get("price_old") != null) {
                    coursePub.setPrice_old(Float.valueOf(sourceAsMap.get("price_old").toString()));
                }
                coursePubs.add(coursePub);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }
}
