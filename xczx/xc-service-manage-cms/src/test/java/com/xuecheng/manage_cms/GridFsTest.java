package com.xuecheng.manage_cms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {
    @Resource
    GridFsTemplate gridFsTemplate;
    @Resource
    GridFSBucket gridFSBucket;

    @Test
    public void save() throws FileNotFoundException {
        // 打开文件
        File file = new File("/Users/x/Desktop/course.html");
        // 创建文件流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 存储到mongodb
        ObjectId store = gridFsTemplate.store(fileInputStream, "course.html");
        System.out.println(store);
    }

    @Test
    public void test() throws IOException {
        // 查询文件 Criteria对象拼接查询条件
        GridFSFile gridFSFile = gridFsTemplate
                .findOne(Query.query(Criteria.where("_id").is("5ed3deb608cd8b791f9ae76d")));
        // 获取文件下载流
        assert gridFSFile != null;
        GridFSDownloadStream stream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource resource = new GridFsResource(gridFSFile, stream);
        // 转String输出
        String content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(content);
    }
}
