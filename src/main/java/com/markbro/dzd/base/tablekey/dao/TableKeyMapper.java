package com.markbro.dzd.base.tablekey.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Area dao
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Repository
public interface TableKeyMapper {
    public Map get(String key_name);
    public void add(Map map);
    public void update(Map map);
}
