package com.markbro.dzd.cms.article.web;
import com.markbro.dzd.cms.article.bean.Article;
import com.markbro.dzd.cms.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.interceptor.ActionLog;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.markbro.asoiaf.utils.string.StringUtil;
import org.springframework.ui.Model;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文章管理
 * Created by wujiyue on 2016-08-14 20:02:46.
 */
@Controller
@RequestMapping("/cms/article")
public class ArticleController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected ArticleService articleService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/cms/article/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        return "/cms/article/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        String id=(String)map.get("id");
        Map<String,Object> article=articleService.getMap(id);
        model.addAttribute("article",article);
        return "/cms/article/edit";
    }
    /**
     * 删除数据并重定向到列表页面
     */
     @RequestMapping ("/delete/{id}")
     public String delete (@PathVariable java.lang.String id, RedirectAttributes redirectAttributes) {
            if (false){//判断不能删除的条件
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "不能删除当前数据!"));
            }else {
                try {
                    articleService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/cms/article/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object articles=null;
        articles=articleService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("articles",articles);
        model.addAttribute("pageParam",pageParam);
        return "/cms/article/list";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(Article article,
                           RedirectAttributes redirectAttributes,Model model){
            if(article.getId()==null){//新增保存
                try {
                    articleService.add(article);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("article", article);
                    return "redirect:/cms/article/add";
                }
            }else{//编辑保存
                try {
                    article.setAvailable(1);//如果不设置1，默认0会有问题的
                    articleService.update(article);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("article", article);
                    return "redirect:/cms/article/edit";
                }
            }
            return "redirect:/cms/article/list";
        }
    //-----------json数据接口--------------------
    
    /**
	*找到已删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/findDeleted")
	public Object findDeleted() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		return articleService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		articleService.updateByMapBatch(map);
	}
    /**
	* 找到无效的数据（未删除deleted=1，但是available=0）
	*/
	@ResponseBody
	 @RequestMapping("/json/findUnAvailable")
	public Object findUnAvailable() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("available",0);
		map.put("deleted",0);
		return articleService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return articleService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(articleService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    /**
     * 不分页查询数据
     */
    @ResponseBody
    @RequestMapping("/json/findAll")
    public Object findAll() {
        Map map=getMap(request);
        String sortString=String.valueOf(map.get("sortString"));
        PageBounds pageBounds=null;
        if(StringUtil.notEmpty(sortString)){
             pageBounds=new PageBounds(Order.formString(sortString));
        }else{
             pageBounds=new PageBounds();
        }
        resultMap=getPageMap(articleService.find(pageBounds,map));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增文章")
    public void add(Article m) {
        
        articleService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Article m) {
        articleService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存文章")
    public Object save(Article m) {
        return  articleService.save(m);
    }
    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("id",id);
		articleService.updateByMap(map);
		msg.setType(Msg.MsgType.success);
		msg.setContent("删除成功！");
	}catch (Exception e){
		msg.setType(Msg.MsgType.error);
		msg.setContent("删除失败！");
	}
	return msg;
	}
    /**
	* 批量逻辑删除的数据
	*/
	@ResponseBody
	@RequestMapping("/json/removes/{ids}")
	public Object removes(@PathVariable java.lang.String[] ids){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("ids",ids);
		articleService.updateByMapBatch(map);
		msg.setType(Msg.MsgType.success);
		msg.setContent("批量删除成功！");
	}catch (Exception e){
		msg.setType(Msg.MsgType.error);
		msg.setContent("批量删除失败！");
	}
	return msg;
	}
     @ResponseBody
     @RequestMapping(value = "/json/delete/{id}", method = RequestMethod.POST)
     public Object delete(@PathVariable java.lang.String id) {
        Msg msg=new Msg();
        try{
        	
            articleService.delete(id);
            msg.setType(Msg.MsgType.success);
            msg.setContent("删除成功！");
        }catch (Exception e){
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除失败！");
        }
            return msg;
     }
     @ResponseBody
     @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
     public Object deletes(@PathVariable java.lang.String[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
        Msg msg=new Msg();
        try{
        	 
             articleService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
        }catch (Exception e){
             msg.setType(Msg.MsgType.error);
             msg.setContent("删除失败！");
        }
             return msg;
     }



}
