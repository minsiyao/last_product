package com.minsiyao;

import com.minsiyao.dao.BannerDao;
import com.minsiyao.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutPoiTest {
    @Autowired
    private BannerDao bd;

    @Test
    public void test() throws IOException {
//        创建excle文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
//        设置时间格式
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
//      设值字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("微软雅黑");
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
//       创建样式，将字体设置给样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        创建  工作薄
        HSSFSheet sheet = workbook.createSheet("连接数据库");
//        设值列宽
        sheet.setColumnWidth(2, 20 * 256);

//        创建行
        HSSFRow row = sheet.createRow(0);
        //  自定义标题行
        String[] titles = {"标题", "图片路径", "创建时间", "状态"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }
//        遍历查询出来的数据
        List<Banner> banners = bd.selectAll();
        for (int i = 0; i < banners.size(); i++) {
            Banner banner = banners.get(i);
//            给表格分别设置数据内容
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(banner.getTitle());
            row1.createCell(1).setCellValue(banner.getImg());
            HSSFCell cell = row1.createCell(2);
            cell.setCellValue(banner.getCreate_date());
            cell.setCellStyle(cellStyle1);

            row1.createCell(3).setCellValue(banner.getStatus());

        }
        workbook.write(new File("D:/test/连接数据库.xls"));
        workbook.close();
    }


}




















