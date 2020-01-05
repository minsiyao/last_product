package com.minsiyao.controller;

import com.minsiyao.dao.BannerDao;
import com.minsiyao.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("Poi")
public class PoiController {
    @Autowired
    private BannerDao bd;

    @ResponseBody
    @RequestMapping("showAllBanner")
    public List<Banner> showAllBanner() {
        List<Banner> banners = bd.selectAll();
        return banners;
    }

    @ResponseBody
    @RequestMapping("load")
    public void load(HttpServletResponse response) throws IOException {
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
        response.setHeader("content-disposition", "attchment;fileName=banner.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile myFile) throws IOException {
        System.out.println(myFile);
//        1.接受用户上传的excle的流
        InputStream inputStream = myFile.getInputStream();
//        2.获得excle文件
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        3.获得工作簿
        HSSFSheet sheet = workbook.getSheet("连接数据库");
//        4.根据工作簿获取到最后一行
        List<Banner> banners = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
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
