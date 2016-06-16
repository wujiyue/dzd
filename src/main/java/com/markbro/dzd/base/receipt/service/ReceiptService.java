package com.markbro.dzd.base.receipt.service;
import com.markbro.dzd.base.receipt.bean.Receipt;
import com.markbro.dzd.base.receipt.dao.ReceiptMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Receipt Service
 * Created by wujiyue on 2016-03-03 23:01:38.
 */
@Service
public class ReceiptService{
    @Autowired
    private ReceiptMapper receiptMapper;
     /*基础公共方法*/
    public Receipt get(java.lang.String id){
        return receiptMapper.get(id);
    }
    public List<Receipt> find(PageBounds pageBounds,Map<String,Object> map){
        return receiptMapper.find(pageBounds,map);
    }
    public List<Receipt> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return receiptMapper.findByMap(pageBounds,map);
    }
    public void add(Receipt receipt){
        receiptMapper.add(receipt);
    }
    public void addBatch(List<Receipt> receipts){
        receiptMapper.addBatch(receipts);
    }
    public void update(Receipt receipt){
        receiptMapper.update(receipt);
    }
    public void updateByMap(Map<String,Object> map){
        receiptMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        receiptMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        receiptMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        receiptMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Receipt> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return receiptMapper.findByOrgid(pageBounds,orgid);
	}
	 public List<Receipt> findByDjfl(PageBounds pageBounds,java.lang.Integer djfl){
		return receiptMapper.findByDjfl(pageBounds,djfl);
	}
}
