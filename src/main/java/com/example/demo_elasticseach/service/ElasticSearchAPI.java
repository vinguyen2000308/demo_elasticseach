package com.example.demo_elasticseach.service;

import com.example.demo_elasticseach.domain.Message;
import com.example.demo_elasticseach.utils.DataUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticSearchAPI {

    @Autowired
    private RestHighLevelClient client;


    public String index(String index, Message message, RestHighLevelClient client) {
        IndexRequest request = new IndexRequest(index);
        request.source(DataUtil.objectToString(message), XContentType.JSON);
        IndexResponse response = null;
        try {
            response = client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getId();
    }


    public SearchResponse query(QueryBuilder queryBuilder, RestHighLevelClient client) {
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchAllQuery);
        sourceBuilder.fetchSource();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("message");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    public GetResponse getRequest(RestHighLevelClient client) {
        GetRequest getRequest = new GetRequest("message");
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResponse;
    }


}
