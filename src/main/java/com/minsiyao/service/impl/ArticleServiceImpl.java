package com.minsiyao.service.impl;

import com.minsiyao.dao.ArticleDao;
import com.minsiyao.entity.Article;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao ad;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Paging queryByPaging(Integer page, Integer rows) {
        Paging paging = new Paging();
        paging.setPage(page);//添加当前页数
        paging.setRowNum(rows);//添加每页行数
        List<Article> articles = ad.selectByPaging((page - 1) * rows, rows);
        paging.setRows(articles);//添加数据
        Integer count = ad.selectCount();//总条数
        paging.setRecords(count);//设置总条数
        paging.setTotal(count % rows == 0 ? count / rows : count / rows + 1);//设置总页数
        System.out.println(paging);
        return paging;
    }

    @Override
    public void updateById(Article article) {
        ad.updateById(article);
    }

    @Override
    public void insert(Article article) {
        Date date = new Date();
        article.setCreate_date(date);
        article.setPublish_date(date);
        article.setId(UUID.randomUUID().toString());
        System.out.println("最终添加的数据=========" + article);
        ad.insert(article);
    }

    @Override
    public List<Article> queryByType(String type) {
        return ad.selectByType(type);
    }

    @Override
    public List<Article> queryAll() {
        return ad.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> queryById(String[] id) {
        return ad.selectById(id);
    }

    @Override
    public void deleteById(String[] id) {
        ad.deleteById(id);
    }
}
