package com.xuecheng.framework.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult<T> {

    private Long total;
    private List<T> list;
}
