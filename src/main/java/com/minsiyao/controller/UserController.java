package com.minsiyao.controller;

import com.alibaba.fastjson.JSON;
import com.minsiyao.dto.MapDto;
import com.minsiyao.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("User")
public class UserController {
    @Autowired
    private UserService us;

    @ResponseBody
    @RequestMapping("queryCountByAddress")
    public List<MapDto> queryCountByAddress() {
        List<MapDto> list = new ArrayList<>();
        list.add(new MapDto("湖北", us.queryCountByAddress("湖北")));
        list.add(new MapDto("上海", us.queryCountByAddress("上海")));
        list.add(new MapDto("北京", us.queryCountByAddress("北京")));
        list.add(new MapDto("湖南", us.queryCountByAddress("湖南")));
        return list;
    }

    @ResponseBody
    @RequestMapping("deleteById")
    public void deleteById(String id) {
        try {
            us.deleteById(id);
//            查询每个地区的人数分布
//            List11<Map<String,Integer>> list = new ArrayList<>();
//            Map<String,Integer> map = new HashMap<>();
//            map.put("湖北",us.queryCountByAddress("湖北"));
//            map.put("上海",us.queryCountByAddress("上海"));
//            map.put("北京",us.queryCountByAddress("北京"));
//            map.put("湖南",us.queryCountByAddress("湖南"));
//            list.add(map);
            List<MapDto> list = new ArrayList<>();
            list.add(new MapDto("湖北", us.queryCountByAddress("湖北")));
            list.add(new MapDto("上海", us.queryCountByAddress("上海")));
            list.add(new MapDto("北京", us.queryCountByAddress("北京")));
            list.add(new MapDto("湖南", us.queryCountByAddress("湖南")));
            String jsonList = JSON.toJSONString(list);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
            goEasy.publish("test3", jsonList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
