package com.minsiyao.controller;

import com.minsiyao.entity.Chapter;
import com.minsiyao.entity.Paging;
import com.minsiyao.service.ChapterService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("Chapter")
public class ChapterController {
    @Autowired
    private ChapterService cs;

    @RequestMapping("queryByAlbumId")
    @ResponseBody
    public List<Chapter> queryByAlbumId(String[] album_id) {
        List<Chapter> chapters = cs.queryByAlbumId(album_id);
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
        return cs.queryByAlbumId(album_id);
    }

    @ResponseBody
    @RequestMapping("queryByAlbumIdPaging")
    public Paging queryByAlbumIdPaging(Integer page, Integer rows, String[] album_id) {
        return cs.queryByAlbumIdPaging(page, rows, album_id);
    }

    @RequestMapping("editChapter")
    @ResponseBody
    public Map<String, String> editChapter(Chapter chapter, String[] id, String oper, HttpServletRequest request) {
        if ("".equals(chapter.getAudio())) {
            chapter.setAudio(null);
        }
        System.out.println("开始编辑");
        System.out.println(chapter);
        if ("add".equals(oper)) {
            System.out.println("开始添加");
            String id1 = UUID.randomUUID().toString();
            chapter.setId(id1);
            Map<String, String> map = cs.addChapter(chapter);
            System.out.println(chapter);
            return map;
        }
        if ("del".equals(oper)) {
            for (String s : id) {
                System.out.println("s========" + s);
            }
            List<Chapter> chapter1 = cs.queryById(id);
            for (Chapter chapter2 : chapter1) {
                String audio = chapter2.getAudio();
                String realPath = request.getSession().getServletContext().getRealPath("/audio");
                File f = new File(realPath + "/" + audio);
                f.delete();//删除图片
            }
            cs.deleteById(id);//删除数据库
            return null;
        }
        if ("edit".equals(oper)) {
            cs.updateById(chapter);
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", chapter.getId());
            map.put("audio", chapter.getAudio());
            return map;
        }

        return null;
    }

    @RequestMapping("upload")
    @ResponseBody
    public void upload(String id, MultipartFile audio, HttpServletRequest request) throws IOException {

        String realPath = request.getSession().getServletContext().getRealPath("/audio");
        String fileName = new Date().getTime() + "_" + audio.getOriginalFilename();
        fileName = fileName.replaceAll(" ", "");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(realPath, fileName);
        String timeLong = null;
        try {
            audio.transferTo(file1);
            //获取文件时长
            AudioFile read = AudioFileIO.read(file1);
            AudioHeader audioHeader = read.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            String s = trackLength % 60 + "秒";
            String m = trackLength / 60 + "分";
            timeLong = m + s;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(audio);
        System.out.println("文件名字========" + fileName);
        System.out.println("文件大小========" + audio.getSize());
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setSize((double) audio.getSize());//设置文件大小单位  B
        chapter.setAudio(fileName);//设置文件名字
        chapter.setDuration(timeLong);//设置文件时长
        System.out.println("最终的数据===" + chapter);
        cs.updateById(chapter);
    }

    @ResponseBody
    @RequestMapping("download")
    public String download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("fileName=======" + fileName);
        String path = request.getSession().getServletContext().getRealPath("/audio");
        FileInputStream is = new FileInputStream(new File(path, fileName));
//        通过响应输出流给client打印数据
        ServletOutputStream os = response.getOutputStream();
        //文件传输  读取过程重点client响应数据
        byte[] bytes = new byte[4096 * 1024];
        int b = 1;
        response.setContentType("application/force-download");
        while (true) {

            System.out.println("返回第" + b + "次--开始");
//            返回值代表读取数据的个数   如果达到文件末尾   返回-1
            int i = is.read(bytes, 0, bytes.length);
            if (i == -1) break;
//            向外响应
            os.write(bytes, 0, i);
            System.out.println("返回第" + b + "次--结束");
            b++;
        }
        //关闭资源
        is.close();
        os.close();
        return null;
    }

}
