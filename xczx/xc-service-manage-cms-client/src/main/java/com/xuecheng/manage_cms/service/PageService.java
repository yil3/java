package com.xuecheng.manage_cms.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.manage_cms.dao.CmsPageDao;
import com.xuecheng.manage_cms.dao.CmsSiteDao;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@Log4j2
public class PageService {
    @Resource
    CmsPageDao cmsPageDao;
    @Resource
    CmsSiteDao cmsSiteDao;
    @Resource
    GridFsTemplate gridFsTemplate;
    @Resource
    GridFSBucket gridFSBucket;

    public void savePageToServerPath(String pageId){
        CmsPage cmsPage = this.findCmsPageById(pageId);
        String htmlFileId = cmsPage.getHtmlFileId();
        InputStream file = this.getFileById(htmlFileId);
        if (file == null){
            log.error("getFileById获取文件为空,文件id：{}",htmlFileId);
            return;
        }
        String siteId = cmsPage.getSiteId();
        CmsSite site = this.findCmsSiteById(siteId);
        // 获取站点物理路径
        String sitePhysicalPath = site.getSitePhysicalPath();
        // 拼接页面的真实物理路径
        String pagePath = sitePhysicalPath + cmsPage.getPageWebPath() + cmsPage.getPageName();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(pagePath));
            IOUtils.copy(file,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public InputStream getFileById(String fileId){
        // 文件对象
        GridFSFile fsFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        assert fsFile != null;
        // 文件对象下载流
        GridFSDownloadStream stream = gridFSBucket.openDownloadStream(fsFile.getObjectId());
        GridFsResource fsResource = new GridFsResource(fsFile, stream);

        try {
            return fsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CmsPage findCmsPageById(String pageId){
        Optional<CmsPage> optional = cmsPageDao.findById(pageId);
        return optional.orElse(null);
    }

    public CmsSite findCmsSiteById(String siteId){
        Optional<CmsSite> optional = cmsSiteDao.findById(siteId);
        return optional.orElse(null);
    }
}
