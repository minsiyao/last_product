package com.minsiyao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Paging<T> {
    private Integer page;       //当前页码
    private Integer total;      //总页数
    private Integer records;    //总条数
    private List<T> rows;       //数据
    private Integer rowNum;     //每页行数

}
