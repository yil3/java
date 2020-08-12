package com.xuecheng.manage_media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFile {
    // 文件分块测试
    @Test
    public void test01() throws IOException {
        // 源文件
        File sourceFile = new File("/Users/x/Movies/xczx/lucene.avi");
        // 分块存储路经
        String FileFolder = "/Users/x/Movies/xczx/chunks/";
        // 分块大小
        long fileSize = 1024 * 1024;
        // 总分块数
        long fileNum = (long) Math.ceil(sourceFile.length() * 1.0 / fileSize);
        // 读文件对象
        RandomAccessFile r = new RandomAccessFile(sourceFile, "r");
        // 缓存区
        byte[] bytes = new byte[1024];
        for (int i = 0; i < fileNum; i++) {
            File chunkFile = new File(FileFolder + i);
            // 写文对象
            RandomAccessFile rw = new RandomAccessFile(chunkFile, "rw");
            int len = -1;
            // 读文件
            while ((len = r.read(bytes)) != -1) {
                rw.write(bytes, 0, len);
                // 写入大小大于等于每个分块大小跳出循环
                if (chunkFile.length() >= fileSize) break;
            }
            rw.close();
        }
        r.close();

    }
    // 文件合并测试
    @Test
    public void test02() throws IOException {
        // 块文件路经
        String filePath = "/Users/x/Movies/xczx/chunks/";
        // 块文件对象
        File fileFolder = new File(filePath);
        // 获取块文件列表
        File[] files = fileFolder.listFiles();
        assert files != null;
        List<File> chunkFiles = Arrays.asList(files);
        // 对文件按照名称排序
        chunkFiles.sort((a,b) -> {
            if (Integer.parseInt(a.getName()) < Integer.parseInt(b.getName())) return -1;
            return 1;
        });

        File mergeFile = new File("/Users/x/Movies/xczx/lucene_1.avi");
        boolean newFile = mergeFile.createNewFile();

        RandomAccessFile rw = new RandomAccessFile(mergeFile, "rw");

        byte[] b = new byte[1024];
        for (File chunkFile : chunkFiles) {
            RandomAccessFile r = new RandomAccessFile(chunkFile, "r");
            int len = -1;
            while ((len = r.read(b)) != -1){
                rw.write(b,0,len);
            }
            r.close();
        }
        rw.close();
    }

    @Test
    public void test03 (){
        List<Integer> integers = Arrays.asList(2, 42, 32, 5534, 6423, 1, 3, 532, 64);
        integers.stream().sorted((a, b) -> {
            if (a < b ) return -1;
            return 1;
        }).forEach(System.out::println);
    }
}
