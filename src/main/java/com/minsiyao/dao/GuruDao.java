package com.minsiyao.dao;

import com.minsiyao.entity.Guru;
import org.apache.ibatis.annotations.Param;

public interface GuruDao {
    public Integer selectToDay();

    public Integer selectOnTwoDay();

    public Integer selectOnThreeDay();

    public Integer selectOnFourDay();

    public Integer selectOnFiveDay();

    public Integer selectOnSixDay();

    public Integer selectOnSeven();

    public Integer selectInSevenDays();

    //    查询一年内的注册量
    public Integer selectOnMouth(@Param("mouth") Integer mouth);

    //    根据id删除
    public void deleteById(@Param("id") String id);

    //    插入数据
    public void insert(Guru guru);
}
