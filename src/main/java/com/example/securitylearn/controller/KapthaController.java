package com.example.securitylearn.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
public class KapthaController {

    @Autowired
    private Producer producer;

    @GetMapping("/captcha.jgp")
    public void getCaptcha(HttpServletRequest request,
                           HttpServletResponse response) throws Exception{
        response.setContentType("image/jpeg");
        String capText = producer.createText();

        request.getSession().setAttribute("captcha", capText);

        BufferedImage bi = producer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        }finally {
            out.close();
        }
    }
}
