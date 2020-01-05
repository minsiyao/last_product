package com.minsiyao.service;

import com.minsiyao.entity.Article;
import com.minsiyao.entity.Paging;

import java.util.List;

public interface ArticleService {
    public Paging queryByPaging(Integer page, Integer rows);

    public void deleteById(String[] id);

    public List<Article> queryById(String[] id);

    public void updateById(Article article);

    public void insert(Article article);

    public List<Article> queryAll();

    public List<Article> queryByType(String type);
}
