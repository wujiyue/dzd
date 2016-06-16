<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.markbro.dzd.common.util.DatagridField " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="delim" value=","/>
<c:if test="${datagrid.initType == 1 }">
<table class="table-xsgl-common" cellspacing="0" cellpadding="0" style="width: 100%;overflow: hidden;">
	<thead>
		<tr>
			<c:if test="${datagrid.rownumbers}">
			<th style="width:50px;text-align: center;">序号</th>
			</c:if>
		<c:forEach var="ff" items="${datagrid.field }">
			<c:if test="${ff.checkbox}">
				<th style="width:25px;text-align: center;">
					<input type="<c:if test="${datagrid.singleSelect}">radio</c:if><c:if test="${!datagrid.singleSelect}">checkbox</c:if>" 
						value="" name="show-table-ipt-full" id="show-table-ipt-full-fst">
				</th>
			</c:if>
			<c:if test="${!ff.checkbox}">
				<th style="<c:out value="${ff.widthcss}" />;text-align:center">
					<c:out value="${ff.title}" escapeXml="true"/>
				</th>
			</c:if>
		</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${datagrid.rows}" varStatus="idx">
		<tr style="line-height: 25px;" <c:if test="${datagrid.edit}">class="rows-tr-isedit"</c:if> editidx="<c:out value="${idx.index}"/>"> 
			<c:if test="${datagrid.rownumbers}">
			<td style="text-align: center;"><c:out value="${idx.index+1}"/></td>
			</c:if>
		<c:forEach var="ff" items="${datagrid.field }">
		<c:if test="${ff.checkbox}">
			<td style="width:25px;text-align: center;">
				<input type="<c:if test="${datagrid.singleSelect}">radio</c:if><c:if test="${!datagrid.singleSelect}">checkbox</c:if>" 
					value="<c:out value="${row[ff.field]}"/>" name="show-table-ipt-full" id="show-table-ipt-<c:out value="${row[datagrid.idField]}"/>">
			</td>
		</c:if>
		<c:if test="${!ff.checkbox}">
			<td style="<c:out value="${ff.aligncss}"/>;${ff.linkStyle}" 
				<c:if test="${!empty ff.linkFunc}"> 
					onclick="javascript:${ff.linkFunc}(<c:forEach var="args" items="${ff.linkFuncArgs }">'${row[args]}',</c:forEach> ${idx.index})" 
				</c:if>
				>
				<c:if test="${!datagrid.edit || !ff.isedit}">
				<c:out value="${row[ff.field]}"/>
				</c:if>
				<c:if test="${datagrid.edit && ff.isedit}">
				<span name="show-td-<c:out value="${idx.index}"/>" id="show-td-<c:out value="${idx.index}"/>-<c:out value="${ff.field}"/>"
					class="edit-span-value"><c:out value="${row[ff.field]}"/></span>
				<input id="edit-td-<c:out value="${idx.index}"/>-<c:out value="${ff.field}"/>"
					name="edit-td-<c:out value="${idx.index}"/>" 
					value="<c:out value="${row[ff.field]}"/>" class="edit-input-value"
					style="display: none;">
				</c:if>
			</td>
			<c:forEach var="i" begin="0" end="${fn:length(datagrid.sumField)}">
			<c:if test="${ff.field eq datagrid.sumField[i] }">
			<fmt:parseNumber var="tmp_num" type="number" value="${row[ff.field]}" />
			<c:set target="${ff}" property="sumvalue">${ff.sumvalue + tmp_num }</c:set>
			</c:if>
			</c:forEach>
		</c:if>
		</c:forEach>
		</tr>
		</c:forEach>
		<c:if test="${!empty datagrid.rows }">
		<tr style="line-height: 25px;">
			<td style="text-align: center;">本页合计</td>
		<c:forEach var="ff" items="${datagrid.field }">
		<c:if test="${ff.checkbox}">
			<td style="width:25px;text-align: center;"></td>
		</c:if>
		<c:if test="${!ff.checkbox}">
			<td style="text-align: right;">
			<c:forEach var="i" begin="0" end="${fn:length(datagrid.sumField)}">
			<c:if test="${ff.field eq datagrid.sumField[i] }">
			<fmt:formatNumber value="${ff.sumvalue}" pattern="#.00"/>
			</c:if>
			</c:forEach>
			</td>
		</c:if>
		</c:forEach>
		</tr>
		</c:if>
		<c:if test="${!empty datagrid.sumrows}">
		<td style="text-align: center;">总计</td>
		<c:forEach var="ff" items="${datagrid.field }">
		<c:if test="${ff.checkbox}">
			<td style="width:25px;text-align: center;"></td>
		</c:if>
		<c:if test="${!ff.checkbox}">
			<td style="text-align: right;">
			<c:if test="${!empty datagrid.sumrows[ff.field]}">
			<fmt:formatNumber value="${datagrid.sumrows[ff.field]}" pattern="#.00"/>
			</c:if>
			</td>
		</c:if>
		</c:forEach>
		</c:if>
	</tbody>
	<c:if test="${datagrid.pagination}">
	<tfoot>
		<tr>
			<td colspan="<c:out value="${datagrid.tfootcolspan}"/>" style="text-align: left;padding-left: 20px;">
				每页显示&nbsp;${datagrid.pagerows}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
				总记录数/总页数&nbsp;[${datagrid.countrow}/${datagrid.pageAmount}]&nbsp;&nbsp;当前第&nbsp;
		<a href="javascript:void(0)" class="div-table-topage-goto page-1" id="" title="1">[1]</a>&nbsp;
		<c:if test="${datagrid.pageAmount!=1}">
			<c:choose>
				<c:when test="${datagrid.currentPage<=5}">
					<c:forEach var="i" begin="2" end="${datagrid.currentPage}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:otherwise>
					...&nbsp;
					<c:forEach var="i" begin="${datagrid.currentPage-3}" end="${datagrid.currentPage}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${datagrid.currentPage>=datagrid.pageAmount-4 || datagrid.pageAmount-4<=0}">
					<c:forEach var="i" begin="${datagrid.currentPage+1}" end="${datagrid.pageAmount}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="${datagrid.currentPage+1}" end="${datagrid.currentPage+3}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
					...&nbsp;
					<a href="javascript:void(0)" class="div-table-topage-goto page-${datagrid.pageAmount}" title="${datagrid.pageAmount}">[${datagrid.pageAmount}]</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:if>
		页
			</td>
		</tr>
	</tfoot>
	</c:if>
</table>
</c:if>
<c:if test="${datagrid.initType == 0 }">
<div class="div-table-common">
<div class="div-table-thead">
	<ul>
		<c:if test="${datagrid.rownumbers}">
		<li style="width:50px;float: left;">序号</li>
		</c:if>
		<c:forEach var="ff" items="${datagrid.field }">
		<c:if test="${ff.checkbox}">
		<li style="width:25px;text-align: center;float: left;padding-top:2px;">
			<input type="<c:if test="${datagrid.singleSelect}">radio</c:if><c:if test="${!datagrid.singleSelect}">checkbox</c:if>" 
				value="" name="show-table-ipt-full" id="show-table-ipt-full-fst">
		</li>
		</c:if>
		<c:if test="${!ff.checkbox}">
		<li style="<c:out value="${ff.widthcss}"/><c:out value="${ff.aligncss}"/>;float: left;">
			<c:out value="${ff.title}"/>
		</li>
		</c:if>
		</c:forEach>
	</ul>
</div>
<div style="clear: both;"></div>
<c:forEach var="row" items="${datagrid.rows}" varStatus="idx">
<div class="div-table-tbody">
	<ul	<c:if test="${datagrid.edit}">class="rows-tr-isedit"</c:if> editidx="<c:out value="${idx.index}"/>">
		<c:if test="${datagrid.rownumbers}">
		<li style="width:50px;float: left;"><c:out value="${idx.index+1 }" /></li>
		</c:if>
		<c:forEach var="ff" items="${datagrid.field }">
		<c:if test="${ff.checkbox}">
		<li style="width:25px;text-align: center;float: left;">
			<input type="<c:if test="${datagrid.singleSelect}">radio</c:if><c:if test="${!datagrid.singleSelect}">checkbox</c:if>" 
					value="<c:out value="${row[ff.field]}"/>" name="show-table-ipt-full" id="show-table-ipt-<c:out value="${row[datagrid.idField]}"/>">
		</li>
		</c:if>
		<c:if test="${!ff.checkbox}">
		<li style="<c:out value="${ff.widthcss}"/><c:out value="${ff.aligncss}"/>display:block;float: left;${ff.linkStyle}"
			<c:if test="${!empty ff.linkFunc}"> 
				onclick="javascript:${ff.linkFunc}(<c:forEach var="args" items="${ff.linkFuncArgs }">'${row[args]}',</c:forEach> ${idx.index})" 
			</c:if>>
			<c:if test="${!datagrid.edit || !ff.isedit}"><c:out value="${row[ff.field]}"/></c:if>
			<c:if test="${datagrid.edit && ff.isedit}">
			<span name="show-td-<c:out value="${idx.index}"/>" id="show-td-<c:out value="${idx.index}"/>-<c:out value="${ff.field}"/>"
				class="edit-span-value"><c:out value="${row[ff.field]}"/></span>
			<input id="edit-td-<c:out value="${idx.index}"/>-<c:out value="${ff.field}"/>"
				name="edit-td-<c:out value="${idx.index}"/>" 
				class="edit-input-value"
				value="<c:out value="${row[ff.field]}"/>"
				style="display: none;">
			</c:if>
		</li>
		<c:forEach var="i" begin="0" end="${fn:length(datagrid.sumField)}">
		<c:if test="${ff.field eq datagrid.sumField[i] }">
		<fmt:parseNumber var="tmp_num" type="number" value="${row[ff.field]}" />
		<c:set target="${ff}" property="sumvalue">${ff.sumvalue + tmp_num }</c:set>
		</c:if>
		</c:forEach>
		</c:if>
		</c:forEach>
	</ul>
	<div style="clear: both;"></div>
	<ul style="line-height: 25px;">
		<li style="width:50px;float: left;text-align: center;">本页合计</li>
	<c:forEach var="ff" items="${datagrid.field }">
	<c:if test="${ff.checkbox}">
		<li style="width:25px;text-align: center;float: left;"></li>
	</c:if>
	<c:if test="${!ff.checkbox}">
		<li style="display:block;float: left;text-align: right;">
		<c:forEach var="i" begin="0" end="${fn:length(datagrid.sumField)}">
		<c:if test="${ff.field eq datagrid.sumField[i] }">
		<c:out value="${ff.sumvalue}"></c:out>
		</c:if>
		</c:forEach>
		</li>
	</c:if>
	</c:forEach>
	</ul>
</div>
</c:forEach>
<div class="div-table-tfoot">
	<ul>
		<li style="text-align: left;padding-left: 20px;">
			每页显示&nbsp;${datagrid.pagerows}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
			总记录数/总页数&nbsp;[${datagrid.countrow}/${datagrid.pageAmount}]&nbsp;&nbsp;当前第&nbsp;
		<a href="javascript:void(0)" class="div-table-topage-goto page-1" id="" title="1">[1]</a>&nbsp;
		<c:if test="${datagrid.pageAmount!=1}">
			<c:choose>
				<c:when test="${datagrid.currentPage<=5}">
					<c:forEach var="i" begin="2" end="${datagrid.currentPage}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:otherwise>
					...&nbsp;
					<c:forEach var="i" begin="${datagrid.currentPage-3}" end="${datagrid.currentPage}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${datagrid.currentPage>=datagrid.pageAmount-4 || datagrid.pageAmount-4<=0}">
					<c:forEach var="i" begin="${datagrid.currentPage+1}" end="${datagrid.pageAmount}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="${datagrid.currentPage+1}" end="${datagrid.currentPage+3}">
						<a href="javascript:void(0)" class="div-table-topage-goto page-${i}" title="${i}">[${i }]</a>&nbsp;
					</c:forEach>
					...&nbsp;
					<a href="javascript:void(0)" class="div-table-topage-goto page-${datagrid.pageAmount}" title="${datagrid.pageAmount}">[${datagrid.pageAmount}]</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:if>
		页
		</li>
	</ul>
</div>
</div>
</c:if>