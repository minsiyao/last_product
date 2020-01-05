package com.minsiyao.controller;

import com.minsiyao.entity.Guru;
import com.minsiyao.service.GuruService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("Guru")
public class GuruController {
    @Autowired
    private GuruService gs;

    @ResponseBody
    @RequestMapping("sevenDayRegistration")
    public List<Integer> sevenDayRegistration(HttpServletRequest request) {
        List<Integer> list = new ArrayList<>();
        list.add(gs.queryToDay());
        list.add(gs.queryOnTwoDay());
        list.add(gs.queryOnThreeDay());
        list.add(gs.queryOnFourDay());
        list.add(gs.queryOnFiveDay());
        list.add(gs.queryOnSixDay());
        list.add(gs.queryOnSeven());
        Collections.reverse(list);
        return list;
    }

    @ResponseBody
    @RequestMapping("onOneYearRegistration")
    public List<Integer> onOneYearRegistration() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(gs.queryOnMouth(i));
        }
        Collections.reverse(list);
        return list;
    }

    @ResponseBody
    @RequestMapping("deleteById")
    public void deleteById(String id, HttpServletRequest request) {
        try {
            gs.deleteById(id);
//            七日注册实时更新
            List<Integer> list = new ArrayList<>();
            list.add(gs.queryToDay());
            list.add(gs.queryOnTwoDay());
            list.add(gs.queryOnThreeDay());
            list.add(gs.queryOnFourDay());
            list.add(gs.queryOnFiveDay());
            list.add(gs.queryOnSixDay());
            list.add(gs.queryOnSeven());
            Collections.reverse(list);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
            goEasy.publish("test1", list.toString());
//            年注册量实时更新
            List<Integer> list1 = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                list1.add(gs.queryOnMouth(i));
            }
            Collections.reverse(list1);
            GoEasy goEasy1 = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
            goEasy1.publish("test2", list1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("insert")
    public void insert(Guru guru) {
        try {
            gs.insert(guru);
//            七日注册实时更新
            List<Integer> list = new ArrayList<>();
            list.add(gs.queryToDay());
            list.add(gs.queryOnTwoDay());
            list.add(gs.queryOnThreeDay());
            list.add(gs.queryOnFourDay());
            list.add(gs.queryOnFiveDay());
            list.add(gs.queryOnSixDay());
            list.add(gs.queryOnSeven());
            Collections.reverse(list);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
            goEasy.publish("test1", list.toString());
//            年注册量实时更新
            List<Integer> list1 = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                list1.add(gs.queryOnMouth(i));
            }
            Collections.reverse(list1);
            GoEasy goEasy1 = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
            goEasy1.publish("test2", list1.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
