package com.xuecheng.framework.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {

    private QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult<T> queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }

    public static QueryResponseResult<?> SUCCESS(QueryResult<?> queryResult) {
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

}
