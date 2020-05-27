package com.universe;

import com.google.gson.Gson;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;


public class QiNiuTest {

    @Test
    public void test01() {
        //构造一个带指定 Region 对象的配置类指定上传空间地区
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "3geTxUi9GGzNl_8V3SBT9ChzOT6T0H3DkCrwGnFh";
        String secretKey = "5P6iiS9cuw3AniMXywEAgbOv1-f22B-F59ty77w3";
        String bucket = "universe-bucket";
        //如果是Windows情况下，格式是 D:\\Pictures\\test.png
        String localFilePath = "/Users/x/Pictures/004.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "1001";
        // String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        // 第二个参数传入key，支持覆盖上传
        String upToken = auth.uploadToken(bucket, key);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }


        CdnManager c = new CdnManager(auth);

        //待刷新的链接列表
        String[] urls = new String[]{
                "http://qay5e5flp.bkt.clouddn.com/1001",
        };
        try {
            //单次方法调用刷新的链接不可以超过100个
            CdnResult.RefreshResult result = c.refreshUrls(urls);
            System.out.println(result.code);
            //获取其他的回复内容
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }
    }

    @Test
    public void test02() {
        // 文件刷新
        String accessKey = "3geTxUi9GGzNl_8V3SBT9ChzOT6T0H3DkCrwGnFh";
        String secretKey = "5P6iiS9cuw3AniMXywEAgbOv1-f22B-F59ty77w3";
        Auth auth = Auth.create(accessKey, secretKey);
        CdnManager c = new CdnManager(auth);
        //待刷新的链接列表
        String[] urls = new String[]{
                "http://qay5e5flp.bkt.clouddn.com/1001",
        };
        try {
            //单次方法调用刷新的链接不可以超过100个
            CdnResult.RefreshResult result = c.refreshUrls(urls);
            System.out.println(result.code);
            //获取其他的回复内容
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }

    }
}
