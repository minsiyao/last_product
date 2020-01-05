package com.minsiyao.dao;

import com.minsiyao.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    public List<Article> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer selectCount();

    public void deleteById(String[] id);

    public List<Article> selectById(String[] id);

    public void updateById(Article article);

    public void insert(Article article);

    public List<Article> selectAll();

    public List<Article> selectByType(@Param("type") String type);

}