package com.markbro.dzd.base.login.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Area dao
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Repository
public interface LoginMapper {

    public String queryUserSession(@Param("token")String token);
    public int updateUserSessionATime(@Param("token")String token);
    public Map<String,Object> queryYhidByDlmc(@Param("dlmc")String dlmc);
    public int updateUserSession(@Param("yhid")String yhid);
    public String queryLoginToken(@Param("yhid")String yhid);
    public int deleteUserSession(@Param("yhid")String yhid);
    public int deleteUserSessionToken(@Param("yhid")String yhid, @Param("token")String token);
    public int insertUserSession(@Param("yhid")String yhid, @Param("ip")String ip, @Param("token")String token, @Param("mei")String mei);
    //查询登录用户的组织信息
    public Map<String,Object> queryOrgMapByYhid(@Param("yhid")String yhid);

    //查询登录用户的角色信息
    public List<Map<String,String>> queryLoginJsList(@Param("zzid")String zzid,@Param("yhid")String yhid);
    //查询登录用户的岗位信息
    public List<Map<String,String>> queryLoginGwList(@Param("zzid")String zzid,@Param("yhid")String yhid);
    //查询登录用户的部门信息
    public List<Map<String,String>> queryLoginBmList(@Param("zzid")String zzid,@Param("yhid")String yhid);
    //登录用户的部门岗位信息（bm_mc,gw_mc,bmid,gwid）
    public List<Map<String, Object>> queryLoginOrgInfo(@Param("yhid")String yhid);
}
