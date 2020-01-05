package com.minsiyao.controller;

import com.minsiyao.dto.MapDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("test")
public class Test {
    @ResponseBody
    @RequestMapping("test")
    public Map<String, Object> test(MultipartFile imgFile, HttpServletRequest request) {
        System.out.println("imgFile========" + imgFile);
        Map<String, Object> map = new HashMap<>();
        String imgFileName = new Date().getTime() + "_" + imgFile.getOriginalFilename();//创建添加时间戳的imgFileName
        String realPath = request.getSession().getServletContext().getRealPath("/img/article");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            imgFile.transferTo(new File(realPath, imgFileName));
            map.put("error", 0);
            String scheme = request.getScheme();//http
            System.out.println(scheme);
            InetAddress localHost = InetAddress.getLocalHost();//localhost
            String localhost = localHost.toString().split("/")[1];
            System.out.println(localhost);
            int serverPort = request.getServerPort();//port
            System.out.println(serverPort);
            String contextPath = request.getContextPath();//项目名
            String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/img/article/" + imgFileName;
            System.out.println(url);
            map.put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("getAll")
    public Map getAll(HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //1.拿到所有  在图片空间展示的图片
        String realPath = session.getServletContext().getRealPath("/img/article");
        File file = new File(realPath);
        String[] imgs = file.list();
        List<Object> list = new ArrayList<>();
        for (String s : imgs) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("is_dir", false);
            hashMap.put("has_file", false);
            File file1 = new File(realPath, s);
            long length = file1.length();
            hashMap.put("filesize", length);
            hashMap.put("dir_path", "");
            hashMap.put("is_photo", true);
            //返回  资源名的  后缀
            String extension = FilenameUtils.getExtension(s);
            hashMap.put("filetype", extension);
            hashMap.put("filename", s);
            String str = s.split("_")[0];
            Long along = Long.valueOf(str);
            Date date = new Date(along);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime", format);
            list.add(hashMap);
        }
        map.put("file_list", list);
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");

        try {
            String scheme = request.getScheme();
            InetAddress localHost = InetAddress.getLocalHost();
            String localhost = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String current_url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/img/article/";
            map.put("current_url", current_url);
            map.put("total_count", imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("query")
    public List query() {
        List list = new ArrayList();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        return list;
    }

    @ResponseBody
    @RequestMapping("queryMap")
    public List<MapDto> queryMap() {
        List<MapDto> list = new ArrayList<>();

        return list;
    }


}
