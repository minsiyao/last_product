package com.minsiyao.service;

import com.minsiyao.entity.Guru;

public interface GuruService {
    public Integer queryToDay();

    public Integer queryOnTwoDay();

    public Integer queryOnThreeDay();

    public Integer queryOnFourDay();

    public Integer queryOnFiveDay();

    public Integer queryOnSixDay();

    public Integer queryOnSeven();

    public Integer queryInSevenDays();

    //    查询一年内每个月的注册量
    public Integer queryOnMouth(Integer mouth);

    //    根据id删除
    public void deleteById(String id);

    //    添加数据
    public void insert(Guru guru);
}
