package com.minsiyao.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.minsiyao.entity.Album;
import com.minsiyao.entity.Article;
import com.minsiyao.entity.Body;
import com.minsiyao.service.AlbumService;
import com.minsiyao.service.ArticleService;
import com.minsiyao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("cmfz")
@Controller
public class CMFZController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("first_page")
    @ResponseBody
    public JSON first_page(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        if ("all".equals(type)) {//查询所有
            map.put("header", bannerService.queryAll());
            List<Body> list = new ArrayList();
            List<Album> albums = albumService.queryAll();
            for (Album album : albums) {
                Body body = new Body(album.getImg(), album.getTitle(), "", "0", album.getCount() + "", album.getCreate_date());
                list.add(body);
            }
            List<Article> articles = articleService.queryAll();
            for (Article article : articles) {
                Body body = new Body("", article.getTitle(), article.getAuthor(), "1", "", article.getCreate_date());
                list.add(body);
            }
            map.put("body", list);
            JSONObject jsonObject = new JSONObject(map);
            System.out.println(jsonObject);
            return jsonObject;
        } else if ("wen".equals(type)) {//查询闻
            List<Album> albums = albumService.queryAll();
            List list = new ArrayList();
            for (Album album : albums) {
                Body body = new Body(album.getImg(), album.getTitle(), "", "0", album.getCount() + "", album.getCreate_date());
                list.add(body);
            }
            map.put("body", list);
            JSONObject jsonObject = new JSONObject(map);
            System.out.println(jsonObject);
            return jsonObject;
        } else if ("si".equals(type)) {//查询思
            if ("ssyj".equals(sub_type)) {//查询思的上师言教
                List<Article> articles = articleService.queryByType("1");
                List list = new ArrayList();
                for (Article article : articles) {
                    Body body = new Body("", article.getTitle(), article.getAuthor(), "1", "", article.getCreate_date());
                    list.add(body);
                }
                map.put("body", list);
                JSONObject jsonObject = new JSONObject(map);
                return jsonObject;
            } else if ("xmfy".equals(sub_type)) {//查询闻的显密法要
                List<Article> articles = articleService.queryByType("2");
                List list = new ArrayList();
                for (Article article : articles) {
                    Body body = new Body("", article.getTitle(), article.getAuthor(), "1", "", article.getCreate_date());
                    list.add(body);
                }
                map.put("body", list);
                JSONObject jsonObject = new JSONObject(map);
                return jsonObject;

            }
        }

        return null;
    }

}
