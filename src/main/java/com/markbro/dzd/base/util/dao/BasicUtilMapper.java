package com.markbro.dzd.base.util.dao;


import com.markbro.dzd.common.SqlMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 共用方法：用于填写塔式数据
 * @author yangsl
 *
 */
@Repository
public interface BasicUtilMapper extends SqlMapper {

	/**
	 * 生成塔式数据的方法
	 * 
	 * 创建时间  2014-3-13 上午11:22:33 
	 * 创建人 yangsl
	 * @param table
	 * @param sjid
	 * @param pkVal
	 * @return
	 *
	 */
	public List<Map<String, Object>> queryTableColumnBySjid(@Param("tables") String table, @Param("fjidcol") String sjid, @Param("pkval") String pkVal);
	public int updateForSql(@Param("processsql") String sql);
	
	/**
	 * 查询某一组织下的部门
	 * 
	 * 创建时间  2014-3-13 上午11:22:15 
	 * 创建人 yangsl
	 * @param zzid　必填
	 * @param bmid　可为空
	 * @return
	 *
	 */
	public List<Map<String, Object>> queryBmListOption(@Param("zzid") String zzid, @Param("bmid") String bmid);
	/**
	 * 查询某一部门的用户：
	 * 
	 * 创建时间  2014-3-13 上午11:21:55 
	 * 创建人 yangsl
	 * @param zzid　必填
	 * @param bmid　可为空
	 * @return
	 *
	 */
	public List<Map<String, Object>> queryOrgTreeYh(@Param("zzid") String zzid, @Param("bmid") String bmid);
	//当前组织ID
	public String queryOrgBmSjzzid(@Param("zzid") String zzid);
	public int queryCountByTableSql(@Param("tables") String table, @Param("querySql") String sql);
	//系统参数
	public String queryYdxsSysPara(@Param("csdm") String csdm, @Param("zzid") String zzid);
	//品牌列表
	public List<Map<String, Object>> queryBasicBrand(@Param("zzidSql") String zzid, @Param("guid") String guid);
	//仓库列表：全部OR单条
	public List<Map<String, Object>> queryBasicStore(@Param("zzidSql") String zzid, @Param("guid") String guid, @Param("sjid") String sjid);
	//仓库列表：根据权限取
	public List<Map<String, Object>> queryBasicStoreByQx(@Param("yhid") String yhid);
	//仓库列表：根据所属部门
	public List<Map<String, Object>> queryBasicStoreByBm(@Param("zzidSql") String zzid, @Param("bmid") String bmid);
	//代码
	public List<Map<String, Object>> queryBasicDm(@Param("lbdm") String lbdm, @Param("zzidSql") String zzidSql);
	//物料分类
	public List<Map<String, Object>> queryBasicMaterials(@Param("zzidSql") String zzidSql, @Param("guid") String guid, @Param("fjdbm") String sjid);
	//计量单位
	public List<Map<String, Object>> queryBasicUnit(@Param("zzidSql") String zzidSql, @Param("zguid") String zguid, @Param("csz") String csz, @Param("group") String group);
	//物料查询
	public List<Map<String, Object>> queryBasicWlMaterials(@Param("guid") String guid, @Param("zzidSql") String zzidSql);
	
	public List<Map<String, Object>> queryOrgYhInfo(@Param("yhid") String yhid);
	//查询组织名称
	public String getZzmc(@Param("zzid") String zzid);
	public String getJtZzmc(@Param("zzid") String zzid);
	public int updateForSql(Map<String, Object> map);
}
