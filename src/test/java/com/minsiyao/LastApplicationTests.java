package com.minsiyao;

import com.minsiyao.dto.MapDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LastApplicationTests {

    @Test
    public void contextLoads() {
//        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-999c3af17d1643b5a60a78fd1c46825d");
//                goEasy.publish("test2", "Hello, GoEasy!");
        List<MapDto> list = new ArrayList<>();
        list.add(new MapDto("湖北", 1));
        System.out.println(list.toString());
    }

}
