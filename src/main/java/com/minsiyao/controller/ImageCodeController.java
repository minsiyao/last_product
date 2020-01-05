package com.minsiyao.controller;

import com.minsiyao.code.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("ImageCode")
public class ImageCodeController {
    @RequestMapping("code")
    public void code(HttpServletResponse response, HttpServletRequest request) throws IOException {
//        1.回执验证码中的字符
        String code = ValidateImageCodeUtils.getSecurityCode();
        request.getSession().setAttribute("code", code);
//        2.通过生成的字符回执图片
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
//        3.通过图片的输出流，写到页面
//        参数1  图片  参数2  格式  参数3  输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }
    }
}
