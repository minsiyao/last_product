package com.minsiyao.dao;

import com.minsiyao.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public void insert(Banner banner);

    public void deleteById(String[] id);

    public void update(Banner banner);

    public List<Banner> selectAll();

    public List<Banner> selectById(String[] id);

    public List<Banner> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer selectCount();

    public void bulkInsert(List<Banner> banners);
}
