package com.mmx.myshop.admin.views.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "upload")
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";
    @RequestMapping( value = "image",method = RequestMethod.POST)
    public Map<String,Object> UpdateImage (MultipartFile dropzFile, HttpServletRequest request,MultipartFile[] wangEditorFiles){

        if(dropzFile != null){
             return dropUpload(request, dropzFile);
        }
        else {
            return  wangEditorUpload(request, wangEditorFiles);
        }
    }

   /* @RequestMapping( value = "image",method = RequestMethod.GET)
    public Map<String,Object> getimage (HttpServletRequest request,CommonsMultipartFile upfile,MultipartFile dropzFile){
        Map<String, Object> map = new HashMap<>();
        String filepath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File file = new File(filepath);
        if(!file.exists()){
            file.mkdirs();
        }

        String fileName = upfile.getFileItem().getFieldName();
        System.out.println(fileName);
        try {
        //获取文件后缀
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            file = new File(filepath+"/"+UUID.randomUUID()+fileSuffix);
            dropzFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = String.format("%s://%s:%s/%s/%s", request.getScheme(), request.getServerName(), request.getServerPort(), UPLOAD_PATH, file.getName());
        map.put("imgUrl",imgUrl);
        return map;
    }*/

    public Map<String,Object>  dropUpload(HttpServletRequest request,MultipartFile dropzFile){
        Map<String, Object> map = new HashMap<>();
        String dropImgUrl = commonUpload(request, dropzFile);
        // 获取服务端路径
        map.put("dropImgUrl",dropImgUrl );
        return map;
    }


    public Map<String,Object>  wangEditorUpload(HttpServletRequest request,MultipartFile[] wangEditorFiles){
           Map<String, Object> map = new HashMap<>();
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile wangEditorFile : wangEditorFiles) {
            filePaths.add(commonUpload(request, wangEditorFile));
        }
            // 返回给 wangEditor 的数据格式
            map.put("errno", 0);
             map.put("data",filePaths);
             return map;
    }


    public String commonUpload(HttpServletRequest request,MultipartFile dropzFile){
        String fileName = dropzFile.getOriginalFilename();
        String filepath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File file = new File(filepath);
        if(!file.exists()){
            file.mkdirs();
        }

        //获取文件后缀
        try {
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            file = new File(filepath+"/"+UUID.randomUUID()+fileSuffix);
            dropzFile.transferTo(file);

            return String.format("%s://%s:%s/%s/%s", request.getScheme(),  request.getServerName(),  request.getServerPort(), UPLOAD_PATH, file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
