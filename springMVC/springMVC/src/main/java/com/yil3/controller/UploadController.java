package com.yil3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile img, HttpServletRequest request){
        if (img.getSize()>0){
            String path = request.getSession().getServletContext().getRealPath("upload");
//            System.out.println(path);
            String name = img.getOriginalFilename();
            File file = new File(path,name);
            try {
                img.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "单文件上传成功";
    }

    @PostMapping("/uploads")
    @ResponseBody
    public String uploads(MultipartFile[] imgs, HttpServletRequest request){
        List<String> files = new ArrayList<>();
        for (MultipartFile img : imgs) {
            if (img.getSize()>0){
                String path = request.getSession().getServletContext().getRealPath("upload");
                String name = img.getOriginalFilename();
                File file = new File(path,name);
                try {
                    img.transferTo(file);
                    files.add("/upload/"+name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        request.setAttribute("files",files);
        return "多文件上传成功"+files;
    }
}
