package com.markbro.dzd.base.orgOrganization.service;
import com.markbro.dzd.base.orgOrganization.bean.Organization;
import com.markbro.dzd.base.orgOrganization.dao.OrganizationMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import com.markbro.asoiaf.core.model.Msg;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
/**
 * 组织机构 Service
 * Created by wujiyue on 2016-07-10 10:38:28.
 */
@Service
public class OrganizationService{
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Organization get(java.lang.String id){
        return organizationMapper.get(id);
    }
    public List<Organization> find(PageBounds pageBounds,Map<String,Object> map){
        return organizationMapper.find(pageBounds,map);
    }
    public List<Organization> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return organizationMapper.findByMap(pageBounds,map);
    }
    public void add(Organization organization){
        organizationMapper.add(organization);
    }
    public Object save(Organization organization){
          Msg msg=new Msg();
                 try{
                     if(organization.getId()==null||"".equals(organization.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         organization.setId(id);
                         organizationMapper.add(organization);
                     }else{
                         organizationMapper.update(organization);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Organization> organizations){
        organizationMapper.addBatch(organizations);
    }
    public void update(Organization organization){
        organizationMapper.update(organization);
    }
    public void updateByMap(Map<String,Object> map){
        organizationMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        organizationMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        organizationMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        organizationMapper.deleteBatch(ids);
    }
     /*自定义方法*/

}
