package com.minsiyao.controller;

import com.minsiyao.entity.Article;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("Article")
@Controller
public class ArticleController {
    @Autowired
    private ArticleService as;

    @RequestMapping("queryByPaging")
    @ResponseBody
    public Paging queryByPaging(Integer page, Integer rows) {
        Paging paging = as.queryByPaging(page, rows);
        return paging;
    }

    @ResponseBody
    @RequestMapping("editArticle")
    public void editArticle(String[] id, String oper) {
        if ("del".equals(oper)) {
            as.deleteById(id);
        }
    }

    @ResponseBody
    @RequestMapping("queryById")
    public List<Article> queryById(String[] id) {
        for (String s : id) {
            System.out.println("id=========" + s);
        }
        System.out.println(as.queryById(id));
        return as.queryById(id);
    }

    @ResponseBody
    @RequestMapping("updateById")
    public void updateById(Article article) {
        System.out.println(article);
        as.updateById(article);
    }

    @ResponseBody
    @RequestMapping("insert")
    public void insert(Article article) {
        System.out.println(article);
        as.insert(article);
    }
}
