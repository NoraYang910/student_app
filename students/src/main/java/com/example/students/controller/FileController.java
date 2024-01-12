package com.example.students.controller;

import com.example.students.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    String path = "";
//    String path = "/students/images/";

    public FileController(){
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = currentDirectory + "\\students\\images\\";
        path = relativePath;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+suffix;
        try{
            file.transferTo(new File(path+fileName));
        }catch (Exception e){
//           e.printStackTrace();
            System.out.println("file not found");
        }
        return Result.success(fileName);
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        System.out.println("start downloading");
        System.out.println(name);
        System.out.println("path1:"+path+name);
        try {
            //read file
            FileInputStream in = new FileInputStream(path+name);

            //write file to browser
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ( (len = in.read(bytes))!=-1){
                out.write(bytes,0,len);
                out.flush();
            }
            in.close();
            out.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("file not found");
        }
    }
}
