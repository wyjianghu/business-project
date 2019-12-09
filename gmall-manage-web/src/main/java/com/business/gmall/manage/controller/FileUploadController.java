package com.business.gmall.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class FileUploadController {

    //完成spu商品文件上传到fdfs
    //1.从配置文件获取文件的上传到服务器的ip
    @Value("${fileServer.url}")
    private String fileUrl;

    @PostMapping("fileUpload")
    public String fileUpload(MultipartFile file) throws IOException, MyException {
        String imgUrl = fileUrl;
        //2、判断file不能为空
        if(file != null){
            //获取tracker.conf配置文件信息
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            //初始化配置文件
            ClientGlobal.init(configFile);
            //创建文件上传对象
            TrackerClient trackerClient = new TrackerClient();
            //获取连接
            TrackerServer trackerServer = trackerClient.getConnection();
            //设置存储对象
            StorageClient storageClient = new StorageClient(trackerServer,null);

            //获取源文件名
            String filename = file.getOriginalFilename();
            String subFileName = StringUtils.substringAfterLast(filename, ".");
            
            //上传文件
            String[] uploadFile = storageClient.upload_appender_file(file.getBytes(), subFileName, null);

            for (int i = 0; i < uploadFile.length; i++) {
                imgUrl += "/"+uploadFile[i];
            }

        }
        System.out.println("上传文件的路径："+imgUrl);
        return imgUrl;
    }
}
