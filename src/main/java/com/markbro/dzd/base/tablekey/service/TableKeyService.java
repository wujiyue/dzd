package com.markbro.dzd.base.tablekey.service;

import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.tablekey.IncrementNumber;
import com.markbro.dzd.base.tablekey.dao.TableKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2016/6/20.
 */

@Transactional
public class TableKeyService extends IncrementNumber {

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    private String prefix = null;

    private long dateEndMillis = 0l;

    private Date date = null;

    private String key_name;

    @Autowired
    private TableKeyMapper tableKeyMapper;
    public TableKeyService() {
        super(20, 99999999);
    }

    @Override
    public int initStartNum() throws Exception {
        Map map = tableKeyMapper.get(key_name);
        prefix =String.valueOf(map.get("prefix"));
        prefix=StringUtil.isNull(prefix);
        return Integer.valueOf(String.valueOf(map.get("cur_no")));
    }

    @Override
    public void updateStartNum(int intervalMax) throws Exception {
        Map map=new HashMap();
        map.put("cur_no",intervalMax);
        map.put("key_name",key_name);
        tableKeyMapper.update(map);
    }

    public String getStringId() {
        try {
            long now = System.currentTimeMillis();

            int  no = cal();

            return prefix + no;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("生成字符串id错误");
    }
    public Integer getIntegerId() {
        try {
            return Integer.valueOf( cal());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("生成整型id错误");
    }
}
