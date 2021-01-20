package tech.binaryer.shjy.biz.dto.page;

import lombok.Data;

@Data
public class QueryParam {
    public QueryParam(){
        //page_size = env.getProperty("custome.page.size");
        pageSize = 3;
        pageIndex = 1;//默认第一页
    }

    /**
     * 记录条数 page_size
     */
    protected Integer pageSize;

    /**
     * 记录页数 page_index
     */
    protected Integer pageIndex;

 /*   *//**
     * 最大条数 limit
     *//*
    protected Integer limit;*/




}
