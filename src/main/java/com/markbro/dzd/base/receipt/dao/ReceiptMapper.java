package com.markbro.dzd.base.receipt.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.receipt.bean.Receipt;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Receipt dao
 * Created by wujiyue on 2016-03-03 23:01:38.
 */
@Repository
public interface ReceiptMapper{
    public Receipt get(java.lang.String id);
    public void add(Receipt receipt);
    public void addBatch(List<Receipt> receipts);
    public void update(Receipt receipt);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Receipt> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Receipt> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Receipt> findByOrgid(PageBounds pageBounds,java.lang.String orgid);
	public List<Receipt> findByDjfl(PageBounds pageBounds,java.lang.Integer djfl);
}
