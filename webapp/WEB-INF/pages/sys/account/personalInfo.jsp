
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%--<%@include file="/WEB-INF/pages/include/common.jsp"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息设置</title>
    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
	<link  href="/resources/css/account/personal.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>

    <script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
    
    <script>

	$(function(){

	});



    </script>
</head>
<script>

</script>
<body>
<!-- 网页主体 -->	
<div class="content_wrapper clearfix mt-5">
	<div class="set-tab clearfix">
		<ul class="tab-normal">
			<li class="selected"><a  title="基本信息">基本信息</a></li>
			<li class=""><a  title="头像设置">头像设置</a></li>
			<li class=""><a  title="更改密码">更改密码</a></li>
			<li class=""><a  title="邮箱设置">邮箱设置</a></li>
		</ul>
	</div>
    <div class="mainContent">
	    <div class="mt-20">
		    <!--基本信息-->
		    <div id="widget-logininfo">
			    <div class="usrSetting-wrap clearfix">
				<a href="http://www.iqiyi.com/u/icon" class="fl userSetting-head">
					<img width="70" height="70" src="/resources/images/headicons/boy-1.png" alt="">
					<div class="userSetting-changehead">更换头像</div>
				</a>
				<div class="fl ml-30">
					<div class="userSetting-item" data-logininfo-elem="nicknameline">
                          <span class="vl-inline">
                                <em class="mainColor">昵称：</em>
                                <em class="mainColor" data-logininfo-elem="nickname">武继跃1</em>
                                <input class="input-common input-size-small mb5" type="text" data-logininfo-elem="nicknamein" placeholder="" style="display:none;">
                          </span>
                          <span class="vl-inline ml10">
                                  <a class="disabled" href="javascript:;">修改</a>
                                  <a class="btn-care btn-care-normal" href="javascript:void(0);" data-logininfo-elem="savenickname" style="display:none;">
                                      <span class="btn-care_tx btn-care_tx-care">保存</span>
                                  </a>
                          </span>
						  <span class="c-999 ml10">(24小时内只能修改一次)</span>
					</div>
					<div class="userSetting-item">
                          <span class="vl-inline"><em class="mainColor">会员状态:<a href="http://vip.iqiyi.com" target="_blank" data-vipheader-elem="vipicon" class="qyv-rank qyvr-gray"></a>
							您还不是vip会员</em>
                          </span>
						<a class="btn-renew vl-inline ml5" href="http://serv.vip.iqiyi.com/order/preview.action?pid=a0226bd958843452&amp;fc=bdd74f057b2b10ac">立即开通VIP</a>
					</div>
					<div class="userSetting-item">
						<span class="vl-inline"><em class="mainColor">UID：2221427520</em></span>
					</div>
					<%--<div class="userSetting-item pr">
						<span class="vl-inline"><em class="mainColor">资料完整度：</em></span>
                  <span class="vl-inline">
                      <span class="userSetting_progressBar" data-logininfo-elem="bar">
                          <span class="userSetting_progressBar_inner" data-logininfo-elem="innerbar" style="width: 25%; display: block;" data-private-display="block"></span>
                      </span>
                      <em class="vl-inline mainColor ml5"><span data-logininfo-elem="percent">25</span>%</em>
                  </span>
						<span class="vl-inline ml10"><a class="green" href="javascript:;" data-logininfo-elem="fill">完善资料</a></span>--%>
						<!-- 弹层 -->
						<div class="mod-pop-upload" data-logininfo-elem="barhint" style="top: 30px; left: 75px; display: none;" data-private-display="block">
							<div class="pop-upload_inner">
								<!-- 弹层箭头位置包括lt(左上), lb(左下), rt(右上), rb(右下) -->
								<div class="pop-upload_arrow arrow-lt">
									<i class="arrow-inner"></i>
									<i class="arrow-outer"></i>
								</div>
								<div class="mainColor">头像、性别、生日等资料完善后，积分获取倍率会增加哦！</div>
								<!--整体输出部分_END-->
							</div>
							<iframe class="ugc-frameLayer" data-elem="bg"></iframe>
						</div>
					</div>
				</div>
			</div>

			<div class="usrSetting-wrap-twoCol clearfix">
				<!-- 手机未绑定 -->
				<div class="fl w50" data-logininfo-phonestate="nobind" style="display:none;">
					<div class="fl">
						<!--
                            phone-done, mail-done // 绑定且验证
                            phone-uncheck, mail-uncheck // 绑定未验证
                            phone-default, mail-default // 未绑定未验证
                           -->
						<a class="userSetting-icon_edit phone-default" href="http://passport.iqiyi.com/pages/secure/account/bind_phone.action"></a>
					</div>
					<div class="fl ml-20">
						<div class="userSetting-item">
						</div>
						<div class="userSetting-item"><a class="c-green" href="http://passport.iqiyi.com/pages/secure/account/bind_phone.action" title="立即绑定">立即绑定</a></div>
						<div class="userSetting-item"><em class="c-999">您可以享受手机相关的登录、安全及提醒服务</em></div>
					</div>
				</div>
				<!-- 手机已绑定 -->
				<div class="fl w50" data-logininfo-phonestate="binded">
					<div class="fl" style="width: 20%;">
                        <a  class="userSetting-icon_edit phone-done" href="http://passport.iqiyi.com/pages/secure/account/modify_phone_index.action"></a>
                    </div>
					<div class="fl  ml-20" >
                        <div class="userSetting-item">
							<span class="vl-inline"><em class="mainColor f-16">手机已验证</em></span>
							<!--<span class="vl-inline ml10"><a class="green" href="javascript:;" title="更换手机号" data-logininfo-elem="replacephone">更换手机号</a></span>-->
						</div>
						<div class="userSetting-item">
							<em class="mainColor" data-logininfo-elem="phone">133****7980</em>
							<a href="http://passport.iqiyi.com/pages/secure/account/modify_phone_index.action" class="c-green" rseat="1411251_altmbl">修改手机号</a>
						</div>
						<div class="userSetting-item"><em class="c-999">您可以享受手机相关的登录、安全及提醒服务</em></div>
					</div>
				</div>
				<div class="cutLine"></div>
				<!-- 邮箱未绑定 -->
				<div class="fl w50" data-logininfo-mailstate="nobind">
					<div class="fl" style="width: 20%;">
						<!--
                            phone-done, mail-done // 绑定且验证
                            phone-uncheck, mail-uncheck // 绑定未验证
                            phone-default, mail-default // 未绑定未验证
                           -->
						<a class="userSetting-icon_edit mail-default" href="http://passport.iqiyi.com/pages/secure/account/bind_email.action"></a>
					</div>
					<div class="fl ml-20" style="width: 80%;">
						<div class="userSetting-item">
                      <span class="vl-inline">
                        <em class="mainColor f-16"><a href="http://passport.iqiyi.com/pages/secure/account/bind_email.action">邮箱未绑定</a></em>
                      </span>
						</div>
						<div class="userSetting-item"><a data-logininfo-elem="bindmail" class="c-green" href="http://passport.iqiyi.com/pages/secure/account/bind_email.action" title="立即绑定">立即绑定</a></div>
						<div class="userSetting-item"><em class="c-999">绑定后，您可以使用邮箱登录及保证您的帐号安全</em></div>
					</div>
				</div>
				<!-- 邮箱绑定未验证 -->
				<div class="fl w50" style="display:none" data-logininfo-mailstate="binded">
					<div class="fl">
						<a class="userSetting-icon_edit mail-uncheck" href="http://passport.iqiyi.com/pages/secure/account/index.action"></a>
					</div>
					<div class="fl ml-20">
						<div class="userSetting-item">
                      <span class="vl-inline">
                        <em class="mainColor f-16">
							<a href="http://passport.iqiyi.com/pages/secure/account/index.action" title="立即验证">邮箱未验证</a>
						</em>
                      </span>
							<span class="vl-inline ml-10"><a class="c-green" href="http://passport.iqiyi.com/pages/secure/account/index.action" title="立即验证" data-logininfo-elem="confirmmail">立即验证</a></span>

						</div>
						<div class="userSetting-item"><em class="mainColor"></em></div>
						<div class="userSetting-item"><em class="c-999">绑定后，您可以使用邮箱登录及保证您的帐号安全</em></div>
					</div>
				</div>
				<!-- 邮箱绑定且验证 -->
				<div class="fl w50" style="display:none" data-logininfo-mailstate="confirmed">
					<div class="fl">
						<a class="userSetting-icon_edit mail-done" href="http://passport.iqiyi.com/pages/secure/account/modify_em.action"></a>
					</div>
					<div class="fl ml-20">
						<div class="userSetting-item">
							<span class="vl-inline"><em class="mainColor f-16">邮箱已验证</em></span>
							<span class="vl-inline ml10"><a class="c-green" href="http://passport.iqiyi.com/pages/secure/account/modify_em.action" title="更换邮箱">更换邮箱</a></span>
						</div>
						<div class="userSetting-item"><em class="mainColor"></em></div>
						<div class="userSetting-item"><em class="c-999">绑定后，您可以使用邮箱登录及保证您的帐号安全</em></div>
					</div>
				</div>
			</div>
		</div>

	<div id="widget-otherinfo" class="mt-20">
		<div class="userSetting-setWrap">

			<div class="userSetting-setWrap-item odd" data-otherinfo-line="gender">
				<span class="vl-inline item-title"><label for="">性别：</label></span>
            <span data-line-elem="editline" >
              <span class="vl-inline">
                  <label class="radio-item  mr10 radio-selected" data-line-elem="genderin" data-pcradio-selectedclass="radio-selected" data-pcradio-name="gender" data-pcradio-value="1">
					  <i class="site-icons icon-radio"></i>
					  <span class="title">男</span>
				  </label>
                  <label class="radio-item" data-line-elem="genderin" data-pcradio-selectedclass="radio-selected" data-pcradio-name="gender" data-pcradio-value="0">
					  <i class="site-icons icon-radio"></i>
					  <span class="title">女</span>
				  </label>
              </span>
              <span class="item-opt">
                <a href="javascript:;" id="cancel_sex">取消</a>
                <a class="ml10" href="javascript:;" id="save_sex">保存</a>
              </span>
            </span>
            <span data-line-elem="displayline" style="display:none;">
              <span class="vl-inline">
                <em class="c666" data-line-elem="display">女</em>
              </span>
              <span class="item-opt"><a href="javascript:;" id="edit_sex">修改</a></span>
            </span>
			</div>
			<div class="userSetting-setWrap-item even" data-otherinfo-line="blood">
				<span class="vl-inline item-title"><label for="">血型：</label></span>
            <span data-line-elem="editline">
              <span class="checkBox_wrap checkBox_wrap-normal clearfix" data-line-elem="bloodtype" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox">O</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option"><a href="#">请选择<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">A<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">B<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">AB<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option" class="selected"><a href="#">O<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">稀有血型<i class="site-icons icon-check"></i></a></li></ul>
              </span>
              <span class="item-opt">
                <a href="javascript:;" data-line-elem="cancel">取消</a>
                <a class="ml10" href="javascript:;" data-line-elem="save">保存</a>
              </span>
            </span>
            <span data-line-elem="displayline"  style="display:none;" >
              <span class="vl-inline">
                <em class="c-666" data-line-elem="display">O</em>
              </span>
              <span class="item-opt"><a href="javascript:;" data-line-elem="edit">修改</a></span>
            </span>
			</div>
			<div class="userSetting-setWrap-item odd" data-otherinfo-line="birthday">
				<span class="vl-inline item-title"><label for="">生日：</label></span>
            <span data-line-elem="editline" style="display:none;">
              <span class="checkBox_wrap checkBox_wrap-min clearfix" data-line-elem="yearin" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox">1987</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option"><a href="#">请选择<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2016<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2015<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2014<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2013<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2012<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2011<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2010<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2009<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2008<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2007<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2006<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2005<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2004<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2003<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2002<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2001<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2000<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1999<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1998<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1997<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1996<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1995<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1994<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1993<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1992<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1991<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1990<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1989<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1988<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option" class="selected"><a href="#">1987<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1986<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1985<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1984<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1983<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1982<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1981<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1980<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1979<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1978<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1977<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1976<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1975<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1974<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1973<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1972<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1971<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1970<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1969<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1968<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1967<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1966<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1965<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1964<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1963<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1962<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1961<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1960<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1959<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1958<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1957<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1956<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1955<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1954<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1953<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1952<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1951<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1950<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1949<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1948<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1947<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1946<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1945<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1944<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1943<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1942<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1941<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1940<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1939<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1938<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1937<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1936<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1935<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1934<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1933<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1932<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1931<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1930<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1929<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1928<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1927<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1926<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1925<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1924<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1923<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1922<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1921<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1920<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1919<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1918<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1917<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1916<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1915<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1914<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1913<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1912<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1911<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1910<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1909<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1908<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1907<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1906<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1905<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1904<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1903<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1902<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1901<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1900<i class="site-icons icon-check"></i></a></li></ul>
              </span>
              <span class="mainColor mr5">&nbsp;年</span>
              <span class="checkBox_wrap checkBox_wrap-min clearfix" data-line-elem="monthin" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox">7</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option"><a href="#">请选择<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">3<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">4<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">5<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">6<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option" class="selected"><a href="#">7<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">8<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">9<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">10<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">11<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">12<i class="site-icons icon-check"></i></a></li></ul>
              </span>
              <span class="mainColor mr5">&nbsp;月</span>
              <span class="checkBox_wrap checkBox_wrap-min clearfix" data-line-elem="dayin" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox">20</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option"><a href="#">请选择<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">3<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">4<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">5<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">6<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">7<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">8<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">9<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">10<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">11<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">12<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">13<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">14<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">15<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">16<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">17<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">18<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">19<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option" class="selected"><a href="#">20<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">21<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">22<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">23<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">24<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">25<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">26<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">27<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">28<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">29<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">30<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">31<i class="site-icons icon-check"></i></a></li></ul>
              </span>

              <span class="mainColor mr5">&nbsp;日</span>
              <span class="item-opt">
                <a href="javascript:;" data-line-elem="cancel">取消</a>
                <a class="ml10" href="javascript:;" data-line-elem="save">保存</a>
              </span>
            </span>
            <span data-line-elem="displayline">
              <span class="vl-inline">
                <em class="c666" data-line-elem="display">1987年07月20日</em>
              </span>
              <span class="item-opt"><a href="javascript:;" data-line-elem="edit">修改</a></span>
            </span>
			</div>
			<div class="userSetting-setWrap-item even" data-otherinfo-line="location">
				<span class="vl-inline item-title"><label for="">居住地：</label></span>
            <span data-line-elem="editline" style="display:none;">
              <span class="checkBox_wrap checkBox_wrap-normal checkBox_wrap-selected mr10 clearfix" data-line-elem="provincein" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox checkBox_wrap-default" data-elem="province">请选择</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option" class="selected"><a href="#">请选择</a></li><li data-pcselect-elem="option"><a href="#">北京</a></li><li data-pcselect-elem="option"><a href="#">天津</a></li><li data-pcselect-elem="option"><a href="#">河北</a></li><li data-pcselect-elem="option"><a href="#">山西</a></li><li data-pcselect-elem="option"><a href="#">内蒙古</a></li><li data-pcselect-elem="option"><a href="#">辽宁</a></li><li data-pcselect-elem="option"><a href="#">吉林</a></li><li data-pcselect-elem="option"><a href="#">黑龙江</a></li><li data-pcselect-elem="option"><a href="#">上海</a></li><li data-pcselect-elem="option"><a href="#">江苏</a></li><li data-pcselect-elem="option"><a href="#">浙江</a></li><li data-pcselect-elem="option"><a href="#">安徽</a></li><li data-pcselect-elem="option"><a href="#">福建</a></li><li data-pcselect-elem="option"><a href="#">江西</a></li><li data-pcselect-elem="option"><a href="#">山东</a></li><li data-pcselect-elem="option"><a href="#">河南</a></li><li data-pcselect-elem="option"><a href="#">湖北</a></li><li data-pcselect-elem="option"><a href="#">湖南</a></li><li data-pcselect-elem="option"><a href="#">广东</a></li><li data-pcselect-elem="option"><a href="#">广西</a></li><li data-pcselect-elem="option"><a href="#">海南</a></li><li data-pcselect-elem="option"><a href="#">重庆</a></li><li data-pcselect-elem="option"><a href="#">四川</a></li><li data-pcselect-elem="option"><a href="#">贵州</a></li><li data-pcselect-elem="option"><a href="#">云南</a></li><li data-pcselect-elem="option"><a href="#">西藏</a></li><li data-pcselect-elem="option"><a href="#">陕西</a></li><li data-pcselect-elem="option"><a href="#">甘肃</a></li><li data-pcselect-elem="option"><a href="#">青海</a></li><li data-pcselect-elem="option"><a href="#">宁夏</a></li><li data-pcselect-elem="option"><a href="#">新疆</a></li><li data-pcselect-elem="option"><a href="#">台湾</a></li><li data-pcselect-elem="option"><a href="#">香港</a></li><li data-pcselect-elem="option"><a href="#">澳门</a></li><li data-pcselect-elem="option"><a href="#">海外</a></li></ul>
              </span>
              <span class="checkBox_wrap checkBox_wrap-normal clearfix" data-line-elem="cityin" style="z-index: 0;">
                  <span data-pcselect-elem="handler"><span class="checkBox checkBox_wrap-default" data-elem="province">请选择</span><span class="checkBoxBtn"></span></span>
                  <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option" class="selected"><a href="#">请选择</a></li></ul>
              </span>
              <span class="item-opt">
                <a href="javascript:;" data-line-elem="cancel">取消</a>
                <a class="ml10" href="javascript:;" data-line-elem="save">保存</a>
              </span>
            </span>
            <span data-line-elem="displayline">
              <span class="vl-inline">
                <em class="c666" data-line-elem="display">山东 - 济南</em>
              </span>
              <span class="item-opt"><a href="javascript:;" data-line-elem="edit">修改</a></span>
            </span>
			</div>
			<div class="userSetting-setWrap-item odd" data-otherinfo-line="qq">
				<span class="vl-inline item-title"><label for="">QQ：</label></span>
            <span data-line-elem="editline" >
              <span class="vl-inline item-input"><input class="input-common" type="text" placeholder="请填写你的QQ号" data-line-elem="qqin"></span>
              <span class="item-opt">
                <a href="javascript:;" data-line-elem="cancel">取消</a>
                <a class="ml10" href="javascript:;" data-line-elem="save">保存</a>
              </span>
            </span>
            <span data-line-elem="displayline" style="display:none;">
              <span class="vl-inline">
                <em class="c666" data-line-elem="display">未设置 </em>
              </span>
              <span class="item-opt"><a href="javascript:;" data-line-elem="edit">设置</a></span>
            </span>
			</div>

			<div class="userSetting-setWrap-item even" data-block-elem="line">
					<span class="vl-inline item-title"><label for="">公司：</label></span>
                <span data-line-elem="editline" style="display:none;">
                  <span class="vl-inline item-input"><input class="input-common input-size-small mr5" type="text" placeholder="请输入公司名称" data-line-elem="companyin" value=""></span>
                  <span class="vl-inline item-input"><input class="input-common input-size-small mr5" type="text" placeholder="请输入部门名称" data-line-elem="departmentin" value=""></span>
                  <span class="checkBox_wrap checkBox_wrap-normal clearfix" data-line-elem="fromin" style="z-index: 0;">
                    <span data-pcselect-elem="handler"><span class="checkBox checkBox_wrap-default">工作时间</span><span class="checkBoxBtn"></span></span>
                    <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option" class="selected"><a href="#">工作时间<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2016<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2015<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2014<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2013<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2012<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2011<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2010<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2009<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2008<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2007<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2006<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2005<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2004<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2003<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2002<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2001<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2000<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1999<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1998<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1997<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1996<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1995<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1994<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1993<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1992<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1991<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1990<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1989<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1988<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1987<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1986<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1985<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1984<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1983<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1982<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1981<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1980<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1979<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1978<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1977<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1976<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1975<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1974<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1973<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1972<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1971<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1970<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1969<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1968<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1967<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1966<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1965<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1964<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1963<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1962<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1961<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1960<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1959<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1958<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1957<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1956<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1955<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1954<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1953<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1952<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1951<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1950<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1949<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1948<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1947<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1946<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1945<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1944<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1943<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1942<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1941<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1940<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1939<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1938<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1937<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1936<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1935<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1934<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1933<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1932<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1931<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1930<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1929<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1928<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1927<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1926<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1925<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1924<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1923<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1922<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1921<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1920<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1919<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1918<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1917<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1916<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1915<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1914<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1913<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1912<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1911<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1910<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1909<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1908<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1907<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1906<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1905<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1904<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1903<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1902<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1901<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1900<i class="site-icons icon-check"></i></a></li></ul>
                  </span>
                  <span class="mainColor mr5">&nbsp;至</span>
                  <span class="checkBox_wrap checkBox_wrap-normal clearfix" data-line-elem="toin" style="z-index: 0;">
                    <span data-pcselect-elem="handler"><span class="checkBox checkBox_wrap-default">工作时间</span><span class="checkBoxBtn"></span></span>
                    <ul class="checkBoxList" style="display: none; max-height: 200px; overflow-y: scroll;" data-pcselect-elem="optionList"><li data-pcselect-elem="option" class="selected"><a href="#">工作时间<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2016<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2015<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2014<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2013<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2012<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2011<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2010<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2009<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2008<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2007<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2006<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2005<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2004<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2003<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2002<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2001<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">2000<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1999<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1998<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1997<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1996<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1995<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1994<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1993<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1992<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1991<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1990<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1989<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1988<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1987<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1986<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1985<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1984<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1983<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1982<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1981<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1980<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1979<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1978<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1977<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1976<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1975<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1974<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1973<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1972<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1971<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1970<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1969<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1968<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1967<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1966<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1965<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1964<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1963<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1962<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1961<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1960<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1959<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1958<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1957<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1956<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1955<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1954<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1953<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1952<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1951<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1950<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1949<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1948<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1947<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1946<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1945<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1944<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1943<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1942<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1941<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1940<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1939<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1938<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1937<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1936<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1935<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1934<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1933<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1932<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1931<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1930<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1929<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1928<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1927<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1926<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1925<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1924<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1923<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1922<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1921<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1920<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1919<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1918<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1917<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1916<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1915<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1914<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1913<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1912<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1911<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1910<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1909<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1908<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1907<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1906<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1905<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1904<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1903<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1902<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1901<i class="site-icons icon-check"></i></a></li><li data-pcselect-elem="option"><a href="#">1900<i class="site-icons icon-check"></i></a></li></ul>
                  </span>
                  <span class="item-opt">
                      <a href="javascript:;" data-line-elem="remove">删除</a>
                      <a class="ml10" href="javascript:;" data-line-elem="save">保存</a>
                  </span>
                </span>
                <span data-line-elem="displayline">
                  <span class="vl-inline">
                    <em class="c666" data-line-elem="display">未设置</em>
                  </span>
                  <span class="item-opt">
                    <a href="javascript:;" data-line-elem="add" style="display:none">+添加</a>
                    <a class="ml10" href="javascript:;" data-line-elem="edit">设置</a>
                  </span>
                </span>
				</div>


		</div>


        </div>
	</div>
    <!-- 头像设置 start-->
    <div class="mainContent" style="display: none;">
        <div class="usrSetting-wrap mt-20 clearfix">

            <div class="user-header-upload clearfix" >
                <img src="/resources/images/headicons/girl-1.png">
                <a href="javascript:void(0);" class="green_btn" id="saveBtn" style="width:130px;height:30px;line-height:30px;" >选择图片</a>

            </div>
           <!-- 系统提供的头像列表 start-->
           <div class="header-list-div" >
               <a class="scrollListBtn scrollList_leftBtn "></a>
               <div class="header-list" >
                   <div class="header-box" >
                       <img src="/resources/images/headicons/boy-1.png">
                       <p class="desc">呆萌GG</p>
                   </div>
                   <div class="header-box" >
                       <img src="/resources/images/headicons/girl-1.png">
                       <p class="desc">乖巧可爱MM</p>
                   </div>
                   <div class="header-box" >
                       <img src="/resources/images/headicons/boy-3.png">
                       <p class="desc">呆萌GG</p>
                   </div>
                   <div class="header-box" >
                       <img src="/resources/images/headicons/girl-1.png">
                       <p class="desc">乖巧可爱MM</p>
                   </div>
                   <div class="header-box" >
                       <img src="/resources/images/headicons/boy-3.png">
                       <p class="desc">呆萌GG</p>
                   </div>
                   <div class="header-box" >
                       <img src="/resources/images/headicons/boy-3.png">
                       <p class="desc">呆萌GG</p>
                   </div>
               </div>
               <a class="scrollListBtn scrollList_rightBtn disabled"></a>
           </div>
           <!-- 系统提供的头像列表 end-->
        </div>
    </div>
    <!-- 头像设置 end-->
</div>  
<!-- 网页主体 end-->

<script type="text/javascript">
    $(function(){
        $(".set-tab ul li a").click(function(){
            var $obj=$(this).parent();
            var i=$obj.index();
            $obj.addClass("selected");
            $obj.siblings().removeClass("selected");
            $(".content_wrapper .mainContent").eq(i).show().siblings(".mainContent").hide();
        });
    })
    function changeTab(){

    }

</script>

</body>
</html>