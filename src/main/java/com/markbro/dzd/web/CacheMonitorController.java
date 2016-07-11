package com.markbro.dzd.web;

import com.markbro.asoiaf.core.utils.ServletUtil;
import com.markbro.dzd.base.cache.CacheMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 缓存监控
 * Created by Administrator on 2015/10/30.
 */
@Controller
@RequestMapping("/sys/cache")
public class CacheMonitorController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    CacheMonitorService cacheMonitorService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/sys/cache/list";
    }

    @ResponseBody
    @RequestMapping("/json/sysCacheState")
    public Object sysCacheState(){
        return cacheMonitorService.sysCacheState();
    }
    @ResponseBody
    @RequestMapping("/json/userCacheState")
    public Object userCacheState(){
        return cacheMonitorService.userCacheState();
    }

  @ResponseBody
    @RequestMapping("/json/getSysCacheByKey")
    public Object getSysCacheByKey(HttpServletRequest req){
        Map map= ServletUtil.getMap(req);
        return cacheMonitorService.getSysCacheByKey(map);
    }

    @ResponseBody
    @RequestMapping("/json/getCacheInfo")
    public Object getCacheInfo(HttpServletRequest req){
        Map map= ServletUtil.getMap(req);
        return cacheMonitorService.getCacheInfo(map);
    }
}
