package com.xuecheng;

import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSTest {

    @Test
    public void test01(){
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            // 创建Tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            // 获取Tracker服务器连接
            TrackerServer connection = trackerClient.getConnection();
            // 从Tracker服务器中获取Storage服务器
            StorageServer storeStorage = trackerClient.getStoreStorage(connection);
            // 创建Storage客户端
            StorageClient1 storageClient1 = new StorageClient1(connection, storeStorage);
            // 上传本地文件
            String path = "/Users/x/Pictures/1.png";
            String fileId = storageClient1.upload_file1(path,"png",null);
            System.out.println(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){

    }
}
