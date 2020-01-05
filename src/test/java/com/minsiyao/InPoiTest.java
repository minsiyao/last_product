package com.minsiyao;

import com.minsiyao.dao.BannerDao;
import com.minsiyao.entity.Banner;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InPoiTest {
    @Autowired
    private BannerDao bd;

    @Test
    public void test() throws IOException {
//      1.创建excle文件  并且读入对应的excle
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:/test/连接数据库.xls"));
//      2.获得sheet  工作簿
        HSSFSheet sheet = workbook.getSheet("连接数据库");
//      3.获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
        ArrayList<Banner> banners = new ArrayList<>();
        for (int i = 0; i < lastRowNum; i++) {
            String title = sheet.getRow(i + 1).getCell(0).getStringCellValue();
            String img = sheet.getRow(i + 1).getCell(1).getStringCellValue();
            Date create_date = sheet.getRow(i + 1).getCell(2).getDateCellValue();
            String status = sheet.getRow(i + 1).getCell(3).getStringCellValue();
            Banner banner = new Banner();
            banner.setTitle(title);
            banner.setImg(img);
            banner.setCreate_date(create_date);
            banner.setStatus(status);
            banner.setId(UUID.randomUUID().toString());
            banners.add(banner);
        }
        System.out.println(banners);
        System.out.println(banners.size());
        bd.bulkInsert(banners);

    }

}
