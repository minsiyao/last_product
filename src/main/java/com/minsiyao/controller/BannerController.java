package com.minsiyao.controller;

import com.minsiyao.entity.Banner;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.BannerService;
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
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bs;

    //   分页查询
    @ResponseBody
    @RequestMapping("queryByPaging")
    public Paging queryByPaging(Integer page, Integer rows) {
        return bs.queryByPaging(page, rows);
    }

    //  查询
    @ResponseBody
    @RequestMapping("queryAll")
    public List<Banner> queryAll(HttpServletRequest request) {
        return bs.queryAll();
    }

    //  编辑
    @ResponseBody
    @RequestMapping("editBanner")
    public Map<String, String> editBanner(Banner banner, String[] id, String oper, String img, HttpServletRequest request) {
        //---------------ajax请求添加--------------
        if ("add".equals(oper)) {
            System.out.println("img======" + img);
            String newId = UUID.randomUUID().toString();
            banner.setId(newId);
            request.getSession().setAttribute("newBanner", banner);
        }
        //---------------ajax请求删除--------------
        if ("del".equals(oper)) {
            for (String s : id) {
                System.out.println("s========" + s);
            }
            List<Banner> banner1 = bs.selectById(id);
            for (Banner banner2 : banner1) {
                String img1 = banner2.getImg();
                String realPath = request.getSession().getServletContext().getRealPath("/img");
                File f = new File(realPath + "/" + img1);
                f.delete();//删除图片
            }
            bs.deleteById(id);//删除数据库

        }
        //---------------ajax请求修改--------------
        if ("edit".equals(oper)) {
            request.getSession().setAttribute("editBanner", banner);
        }
        System.out.println(banner);
        HashMap<String, String> map = new HashMap<>();
        map.put("bannerId", banner.getId());
        return map;
    }

    //  上传文件
    @ResponseBody
    @RequestMapping("upload")
    public String upload(MultipartFile img, HttpServletRequest request, String bannerId) {
        System.out.println("bannerId=====" + bannerId);
        System.out.println("img========" + img);
        Banner newBanner = (Banner) request.getSession().getAttribute("newBanner");
        String imgName = new Date().getTime() + "_" + img.getOriginalFilename();//创建添加时间戳的imgName
        //添加图片
        if (newBanner != null) {
            Date date = new Date();
            newBanner.setImg(imgName);
            newBanner.setCreate_date(date);
            System.out.println("newBanner========" + newBanner);
            bs.insert(newBanner);//存入数据库
            request.getSession().setAttribute("newBanner", null);
            System.out.println("添加完毕========" + newBanner);
        }
        //修改图片
        Banner editBanner = (Banner) request.getSession().getAttribute("editBanner");
        if (editBanner != null) {
            System.out.println("修改图片");
            if ("".equals(img.getOriginalFilename())) {
                bs.update(editBanner);//存入数据库
                return null;
            }
            editBanner.setImg(imgName);
            System.out.println("editBanner========" + editBanner);
            bs.update(editBanner);//存入数据库
            request.getSession().setAttribute("editBanner", null);
        }
//--------------------上传文件-------------------
        String realPath = request.getSession().getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            img.transferTo(new File(realPath, imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
