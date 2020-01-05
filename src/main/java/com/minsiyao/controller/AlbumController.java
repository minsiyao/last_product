package com.minsiyao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.minsiyao.entity.*;
import com.minsiyao.service.AlbumService;
import com.minsiyao.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("Album")
public class AlbumController {
    @Autowired
    private AlbumService as;
    @Autowired
    private ChapterService cs;

    @ResponseBody
    @RequestMapping("queryAll")
    public List<Album> queryAll() {
        List<Album> albums = as.queryAll();
        for (Album album : albums) {
            System.out.println(album);
        }
        return as.queryAll();
    }

    @ResponseBody
    @RequestMapping("queryByPaging")
    public Paging queryByPaging(Integer page, Integer rows) {
        return as.queryByPaging(page, rows);
    }

    @ResponseBody
    @RequestMapping("editAlbum")
    public Map<String, String> editAlbum(Album album, String[] id, String oper, HttpServletRequest request) {
        System.out.println("oper=========" + oper);
        if ("add".equals(oper)) {
            String id1 = as.insert(album);
            Map<String, String> map = new HashMap<>();
            map.put("id", id1);
            return map;
        }
        if ("edit".equals(oper)) {
            as.update(album);
            if (!("".equals(album.getImg()) || album.getImg() == null)) {
                Map<String, String> map = new HashMap<>();
                map.put("id", album.getId());
                return map;
            }
        }
        if ("del".equals(oper)) {
            List<Album> albums = as.queryById(id);
            String realPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("项目路径为：" + realPath);
            for (Album album1 : albums) {
                System.out.println("album1.getImg()======" + album1.getImg());
                String img = album1.getImg().replaceAll("/", "\\\\");
                File file = new File(realPath + img);
                System.out.println("删除的路径为：" + realPath + img);
                file.delete();
            }
            as.deleteById(id);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile img, HttpServletRequest request) {
        System.out.println("id================" + id);
        System.out.println("文件上传");
        System.out.println(img.getOriginalFilename());
        String realPath = request.getSession().getServletContext().getRealPath("/img/album");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = new Date().getTime() + "_" + img.getOriginalFilename();
        fileName = fileName.replaceAll(" ", "");
        File file1 = new File(realPath, fileName);
        try {
            img.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        开始修改数据
        Album album = new Album();
        album.setId(id);
        album.setImg("img/album/" + fileName);
        as.update(album);
        System.out.println("修改后的数据为======" + album);

    }

    //    闻的详情页接口
    @ResponseBody
    @RequestMapping("wen")
    public JSON wen(String id, String uid) {
        Map<String, Object> map = new HashMap<>();
        String[] ids = {id};
        List<Album> albums = as.queryById(ids);
        Album album = albums.get(0);
        Introduction introduction = new Introduction(album.getImg(), album.getTitle(), album.getScore() + "", album.getAuthor(), album.getBroadcaster(), album.getCount() + "", album.getBrief(), album.getCreate_date());
        System.out.println("introduction=====" + introduction);
        map.put("introduction", introduction);//放置introduction

        List<List11> list = new ArrayList<>();
        List<Chapter> chapters = cs.queryByAlbumId(ids);
        for (Chapter chapter : chapters) {
            list.add(new List11(chapter.getTitle(), chapter.getAudio(), chapter.getSize() + "", chapter.getDuration()));
        }
        map.put("list", list);//放置list
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        return jsonObject;
    }

}
