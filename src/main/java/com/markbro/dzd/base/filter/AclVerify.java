package com.markbro.dzd.base.filter;

import com.markbro.asoiaf.core.exception.ForbiddenException;
import com.markbro.asoiaf.core.exception.UnAuthorizedException;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.dzd.common.util.ConstantUtil;

import java.util.List;

/**
 * 路径检查
 * @author yangsl
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class AclVerify {
	
	
	public static void verify(String yhid, String uri) throws ForbiddenException, UnAuthorizedException {
		boolean returnBool = false;
		/*if (uri.indexOf(".jsp") > 0) {
			List<Map> list = (List) EhCacheUtils.getUserInfo(yhid, ConstantUtil.CACHE_YH_URL);
			for (Map tempMap : list) {
				if (String.valueOf(tempMap.get("url")).indexOf(uri) >= 0) {
					returnBool = true;
					break;
				}
			}
			if (!returnBool) {
				List<Map> url_list = (List) EhCacheUtils.getSysInfo(ConstantUtil.CACHE_GLOBAL_URL);
				Iterator localIterator2;
				Map map;
				for (localIterator2 = url_list.iterator(); localIterator2.hasNext();) {
					map = (Map) localIterator2.next();
					if (String.valueOf(((Map) map).get("url")).indexOf(uri) == 0) {
						returnBool = true;
						break;
					}
				}
				if (returnBool)
					throw new ForbiddenException();//当前用户没有访问页面的权限-403
			}
		} else if ((uri.indexOf(".do") > 0) && (method != null)
				&& (!method.equals(""))) {
			method = uri + method;
			List _list = (List) EhCacheUtils.getUserInfo(yhid, "method");
			for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
				Map tempMap = (Map) iterator.next();
				if (method.equals((String) tempMap.get("method"))) {
					returnBool = true;
					break;
				}
			}
			if (!returnBool) {
				List<Map> all_list = (List) EhCacheUtils.getSysInfo("method");
				for (Map tempMap : all_list) {
					if (String.valueOf(tempMap.get("method")).equals(method)) {
						returnBool = true;
						break;
					}
				}
				if (returnBool)
					throw new UnAuthorizedException();//未授权访问-401
			}
		}*/
		if(uri.indexOf("/json/")>0&&!(uri.indexOf("/remove")>0)&&!(uri.indexOf("/delete")>0)){
			uri = uri.substring(0,uri.indexOf("/json/"));
		}
		if(uri.indexOf("/json/")>0&&((uri.indexOf("/remove")>0)||(uri.indexOf("/delete")>0))){
			if(uri.indexOf("/remove")>0){
				uri = uri.substring(0,uri.indexOf("/remove")+7);
			}
			if(uri.indexOf("/remove")>0){
				uri = uri.substring(0,uri.indexOf("/delete")+7);
			}
		}
		List<String> list = (List<String>) EhCacheUtils.getUserInfo(ConstantUtil.CACHE_YH_URL,yhid);
		for (String s : list) {
			if (s.indexOf(uri) >= 0) {
				returnBool = true;
				break;
			}
		}
		if (!returnBool) {
			/*List<Map> url_list = (List) EhCacheUtils.getSysInfo(ConstantUtil.CACHE_GLOBAL_URL);
			Iterator localIterator2;
			Map map;
			for (localIterator2 = url_list.iterator(); localIterator2.hasNext();) {
				map = (Map) localIterator2.next();
				if (String.valueOf(((Map) map).get("url")).indexOf(uri) == 0) {
					returnBool = true;
					break;
				}
			}
			if (returnBool)*/
				throw new ForbiddenException();//当前用户没有访问页面的权限-403
		}
	}
}