package com.universe.controller;

import com.universe.entity.User;
import com.universe.entity.UserFile;
import com.universe.service.UserFileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ShowAllController {

    @Autowired
    private UserFileService userFileService;

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) throws FileNotFoundException {
        UserFile userFile = userFileService.findById(id);
        String loadPath =  ResourceUtils.getURL("classpath:").getPath() + "static" + userFile.getPath();
        File file = new File(loadPath,userFile.getNewFileName());
        if (file.exists()) file.delete();

        userFileService.deleteById(id);
        return "redirect:/showAll";
    }

    @GetMapping("download/{id}/{openStyle}")
    public String download(@PathVariable Integer id, @PathVariable String openStyle, HttpServletResponse response) throws IOException {
        //判断在线打开还是下载
        openStyle =  "inline".equals(openStyle) ? "inline" : "attachment";
        UserFile userFile =  userFileService.findById(id);
        //获取文件真实路径
        String loadPath =  ResourceUtils.getURL("classpath:").getPath() + "static" + userFile.getPath();
        //响应附件下载
        response.setHeader("content-disposition",openStyle + ";fileName=" + URLEncoder.encode(userFile.getOldFileName(),"utf-8"));
        //获取文件输入流
        FileInputStream fileInputStream = new FileInputStream(new File(loadPath,userFile.getNewFileName()));
        //获取文件响应输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(fileInputStream,outputStream);
        //关闭流释放资源
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);

        //更新下载次数
        if ("attachment".equals(openStyle)) {
            userFile.setDowncounts(userFile.getDowncounts() + 1);
            userFileService.update(userFile);
        }
        return "redirect:/showAll";
    }

    @PostMapping("upload")
    public String upload(MultipartFile file,HttpSession session) throws IOException {
        //获取上传文件用户的ID
        User user = (User) session.getAttribute("user");
        //获取旧文件名称
        String oldFileName = file.getOriginalFilename();
        //获取后缀名
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //新文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + UUID.randomUUID().toString().replace("-","") + extension;
        //文件大小
        String size = String.valueOf(file.getSize());
        //文件类型
        String type = file.getContentType();
        //根据日期创建文件上传目录
        String path =  ResourceUtils.getURL("classpath:").getPath() + "static";
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = path + "/upload/" + dateFormat;
        File dateDir = new File(dateDirPath);
        if (!dateDir.exists()) dateDir.mkdirs();

        //处理文件上传
        if (file.getSize() > 0) file.transferTo(new File(dateDir,newFileName));

        //保存文件信息到mysql数据库
        UserFile userFile = new UserFile();
        userFile.setOldFileName(oldFileName);
        userFile.setNewFileName(newFileName);
        userFile.setSize(size);
        userFile.setExt(extension);
        userFile.setType(type);
        userFile.setPath("/upload/" + dateFormat);
        userFile.setUid(user.getId());


        if(file.getSize() > 0) userFileService.save(userFile);

        return "redirect:/showAll";


    }

    @GetMapping("showAll")
    public String showAll(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<UserFile> userFiles = userFileService.findByUid(user.getId());
        model.addAttribute("files",userFiles);
        return "showAll";
    }



}
