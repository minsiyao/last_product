package com.minsiyao;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.minsiyao.dao.BannerDao;
import com.minsiyao.entity.Banner;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyPoiTest {
    @Autowired
    private BannerDao bd;

    @Test
    public void test() throws IOException {

        List<Banner> banners = bd.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("大标题", "小标题", "测试表明"),
                Banner.class, banners);
        workbook.write(new FileOutputStream("D:/test/easypoi.xls"));
        workbook.close();

    }

}




















