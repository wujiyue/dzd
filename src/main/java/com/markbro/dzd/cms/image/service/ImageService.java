package com.markbro.dzd.cms.image.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.asoiaf.utils.date.DateUtil;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.image.bean.Image;
import com.markbro.dzd.cms.image.dao.ImageMapper;
import com.markbro.dzd.common.util.Guid;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * 图片 service
 * Created by wujiyue on 2016-08-12 21:03:06.
 */
@Service
public class ImageService{
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Image get(java.lang.String id){
        return imageMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return imageMapper.getMap(id);
    }
    public List<Image> find(PageBounds pageBounds,Map<String,Object> map){
        return imageMapper.find(pageBounds,map);
    }
    public List<Image> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return imageMapper.findByMap(pageBounds,map);
    }
    public void add(Image image){
        imageMapper.add(image);
    }
    public Object save(Image image){
          Msg msg=new Msg();
                 try{
                     if(image.getId()==null||"".equals(image.getId().toString())){
                         java.lang.String id= keyService.getStringId();


                         image.setId(id);
                         imageMapper.add(image);
                     }else{

                         imageMapper.update(image);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Image> images){
        imageMapper.addBatch(images);
    }
    public void update(Image image){
        imageMapper.update(image);
    }
    public void updateByMap(Map<String,Object> map){
        imageMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        imageMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        imageMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        imageMapper.deleteBatch(ids);
    }



     /*自定义方法*/

    public Map<String, Object> uploadSingle(Map<String, Object> map, HttpServletRequest req, MultipartFile mFile) {
        //资源图片路径(相对于webapp下的路径)
        String basepath=req.getSession().getServletContext().getRealPath("/");
        String relativePath=null;
        try {
            req.setCharacterEncoding("utf-8");
            relativePath= SysPara.getValue("cms_image_basepath");
        } catch (Exception e) {
            relativePath= "/resources/static/cms/images";
        }
        relativePath= StringUtil.subEndStr(relativePath,"/");
        File dirFile=new File(basepath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        try {
            long size = 0;
            String path = "";
            String filename = "";
            String name = "";
            String dir = "";
            String suffixes ="";
            String guid= Guid.get();
            if (!mFile.isEmpty()) {
                BufferedInputStream in = new BufferedInputStream(mFile.getInputStream());
                filename = mFile.getOriginalFilename();
                name=filename.substring(0,filename.indexOf("."));
                // 取得文件后缀
                suffixes = filename.substring(filename.lastIndexOf("."), filename.length());
                path= relativePath+"/"+DateUtil.formatDate(new Date(),"yyyyMMdd")+"/"+guid + suffixes;//相对路径
                dir = basepath+File.separatorChar+path;// 绝对路径
                String tempDir=dir.substring(0,dir.lastIndexOf("/"));
                File tempDirFile=new File(tempDir);
                if(!tempDirFile.exists()){
                    tempDirFile.mkdirs();
                }
                File ffout = new File(dir);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ffout));
                Streams.copy(in, out, true);
                size = ffout.length();
            }
            map.put("path", path);
            map.put("name", name);
            map.put("suffixes", suffixes);
            map.put("size", size);
        }catch (IOException e) {
                e.printStackTrace();
        }
        return map;
    }
    public List<Map<String, Object>> uploadMulti(Map<String, Object> map, HttpServletRequest req, List<MultipartFile> mFiles) {
        List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
        //资源图片路径(相对于webapp下的路径)
        String basepath=req.getSession().getServletContext().getRealPath("/");
        String relativePath=null;
        try {
            req.setCharacterEncoding("utf-8");
            relativePath= SysPara.getValue("cms_image_basepath");
        } catch (Exception e) {
            relativePath= "/resources/static/cms/images";
        }
        relativePath= StringUtil.subEndStr(relativePath,"/");
        File dirFile=new File(basepath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        try {
            long size = 0;
            String path = "";
            String filename = "";
            String name = "";
            String dir = "";
            String suffixes ="";
            String guid= null;
            MultipartFile mFile=null;
            int n=mFiles.size();
            Map<String, Object> tmap=null;
            for(int i=0;i<n;i++) {
                if (!mFile.isEmpty()) {
                    tmap=new HashMap<String, Object>();
                    guid= Guid.get();
                    BufferedInputStream in = new BufferedInputStream(mFile.getInputStream());
                    filename = mFile.getOriginalFilename();

                    name = filename.substring(0, filename.indexOf("."));
                    // 取得文件后缀
                    suffixes = filename.substring(filename.lastIndexOf("."), filename.length());
                    path = relativePath + "/" + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/" + guid + suffixes;//相对路径
                    dir = basepath + File.separatorChar + path;// 绝对路径
                    String tempDir = dir.substring(0, dir.lastIndexOf("/"));
                    File tempDirFile = new File(tempDir);
                    if (!tempDirFile.exists()) {
                        tempDirFile.mkdirs();
                    }
                    File ffout = new File(dir);
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ffout));
                    Streams.copy(in, out, true);
                    size = ffout.length();
                }
                tmap.put("path", path);
                tmap.put("name", name);
                tmap.put("suffixes", suffixes);
                tmap.put("size", size);
                resultList.add(tmap);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
