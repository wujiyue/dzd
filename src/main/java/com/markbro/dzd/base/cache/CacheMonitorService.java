package com.markbro.dzd.base.cache;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.utils.string.StringUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/10/30.
 */
@Service
public class CacheMonitorService {

    private final String cacheValueKey = "key";
    private final String cacheNameKey = "cachename";
    private final String cacheSizeKey = "size";
    private final String cacheMsSizeKey = "memorystoresize";
    private final String cacheDsSizeKey = "diskstoresize";
    private final String cachesImKey = "sizeinmemory";
    private final String cacheExplainKey = "explain";
    //系统缓存
    public Map<String, Object> sysCacheState(){
        //获得系统缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put(cacheNameKey, cache.getName());
        m.put(cacheSizeKey, Integer.valueOf(cache.getSize()));
        m.put(cacheMsSizeKey, Long.valueOf(cache.getMemoryStoreSize()));
        m.put(cacheDsSizeKey, Integer.valueOf(cache.getDiskStoreSize()));
        m.put(cachesImKey, Long.valueOf(cache.calculateInMemorySize()));
        return m;
    }
    //用户缓存
    public Map<String, Object> userCacheState(){
        //获得用户缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.USER_CACHE);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put(cacheNameKey, cache.getName());
        m.put(cacheSizeKey, Integer.valueOf(cache.getSize()));
        m.put(cacheMsSizeKey, Long.valueOf(cache.getMemoryStoreSize()));
        m.put(cacheDsSizeKey, Integer.valueOf(cache.getDiskStoreSize()));
        m.put(cachesImKey, Long.valueOf(cache.calculateInMemorySize()));
        return m;
    }

    /**
     * 获得特定key的系统缓存详情
     * @param map
     * @return
     */
    public Map<String, Object> getSysCacheByKey(Map<String, Object> map) {
        //获得系统缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        Map<String, Object> m = new HashMap<String, Object>();
        Object key = map.get(cacheValueKey);
        if (key==null||"".equals(key.toString())) {
            m.put(cacheExplainKey, "");
        } else {
            m.put(cacheValueKey, key.toString());
            Element element = cache.getQuiet(key.toString());
            if (element != null) {
                m.put(cacheExplainKey, element.toString());
            } else {
                m.put(cacheExplainKey, "");
            }
        }
        return m;
    }
    /**
     * 获得系统缓存详情
     * @param map
     * @return
     */
    public Object getSysCacheInfo(Map<String, Object> map) {
        //获得系统缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        try {
            String page_str=(String)map.get("page");//当前页
            String limit_str=(String)map.get("limit");//每页几条数据
            page_str= StringUtil.assertNotNullOrEmpty(page_str,"1");
            limit_str= StringUtil.assertNotNullOrEmpty(limit_str,"10");
            int page=Integer.parseInt(page_str);
            int limit = Integer.parseInt(limit_str);
            int start = page-1;
            if(page == 1 || page == 0){
                start = 0;
            }
            List keysList = cache.getKeys();
            int keysCount=keysList.size();
            long serializeLong = 0L;
            for (int i = 0; i < keysList.size(); i++) {
                Element e = cache.getQuiet(keysList.get(i).toString());
                serializeLong += e.getSerializedSize();
            }
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (int i = start * limit; i < keysCount && i < (start+1) * limit; i++) {
                Element element = cache.getQuiet(keysList.get(i).toString());
                long l = element.getSerializedSize();
                Map<String, Object> elMap = new HashMap<String, Object>();
                elMap.put(cacheValueKey, keysList.get(i).toString());
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(element.getCreationTime());
                elMap.put("creattime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLatestOfCreationAndUpdateTime());
                elMap.put("lastupdatetime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLastAccessTime());
                elMap.put("lastaccesstime", formatter.format(calendar.getTime()));
                elMap.put("hittimes", Long.valueOf(element.getHitCount()));
                if (serializeLong == 0L) {
                    elMap.put(cacheSizeKey, "0");
                    elMap.put("proprotion", "0%");
                } else {
                    double d = l * 100.0D / serializeLong;
                    elMap.put(cacheSizeKey, new DecimalFormat("####,###").format(l));
                    elMap.put("proprotion", new DecimalFormat("####.00").format(d) + "%");
                }
                mapList.add(elMap);
            }
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("total", keysList.size());
            m.put("rows", mapList);
            return m;
        }catch (Exception e) {
            Msg msg=new Msg();
            msg.setType(Msg.MsgType.error);
            msg.setContent("获得系统缓存详情！");
            return msg;
        }

    }
}
