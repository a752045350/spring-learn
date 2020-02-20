package com.springboot.learn.controller;


import com.springboot.learn.ExcelUtils.ExportExcelUtils;
import com.springboot.learn.ExcelUtils.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RequestMapping("/api")
@RestController
public class ExporotExcelController {

    @Autowired
    ExportExcelUtils exportExcelUtils;

    @RequestMapping("/export")
    public void export(HttpServletRequest request , HttpServletResponse response){

     /*   List<Product> list = new ArrayList<>();
        for (int i = 0 ; i<60000 ; i++) {
            //组装测试数据
            Product product = new Product();
            product.setName("爱奇艺会员"+i);
            product.setPrice(9.99);
            product.setDate(new Date());
            list.add(product);
        }
     try {
         exportExcelUtils.excelExport(list,"测试",Product.class,1,response,request);
     }catch (Exception e){
         System.out.println(e.getMessage());
     }*/
     if(request.getSession().getAttribute("user") == null){
         Cookie cookie = new Cookie("times","123456");
        // cookie.setSecure(true);
         request.getSession().setAttribute("user","users");
         response.addCookie(cookie);
     }else{
         Cookie[] cookie = request.getCookies();

         for (Cookie cookie1: cookie
              ) {
             System.out.println(cookie.toString());
         }
         Object obj = request.getSession().getAttribute("user");

         System.out.println(obj.toString());
     }



    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
