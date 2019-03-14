<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>评价列表</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="css/manager/style.css" type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery.page.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 100%;
	font-size: 14px;
}

/* page */
.tcdPageCode {
	padding: 10px 20px;
	text-align: left;
	color: #ccc;
	text-align: center;
}

.tcdPageCode a {
	display: inline-block;
	color: #428bca;
	display: inline-block;
	height: 25px;
	line-height: 25px;
	padding: 0 10px;
	border: 1px solid #ddd;
	margin: 0 2px;
	border-radius: 4px;
	vertical-align: middle;
}

.tcdPageCode a:hover {
	text-decoration: none;
	border: 1px solid #428bca;
}

.tcdPageCode span.current {
	display: inline-block;
	height: 25px;
	line-height: 25px;
	padding: 0 10px;
	margin: 0 2px;
	color: #fff;
	background-color: #428bca;
	border: 1px solid #428bca;
	border-radius: 4px;
	vertical-align: middle;
}

.tcdPageCode span.disabled {
	display: inline-block;
	height: 25px;
	line-height: 25px;
	padding: 0 10px;
	margin: 0 2px;
	color: #bfbfbf;
	background: #f2f2f2;
	border: 1px solid #bfbfbf;
	border-radius: 4px;
	vertical-align: middle;
}
.div-overflow {
	 text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
}
</style>
</head>
<body>
	<%
		int pageCount = (int) request.getAttribute("pageCount");
		int curPage = (int) request.getAttribute("page");
	%>
	<div class="container-fluid height100p">
		<div class="row height100p">
			<div class="col-md-12 height30px">
				<div class="col-md-6 offset-6">
					<form class="form-inline float-right">
						<div class="form-group">
							<input id="goodsName" type="text"
								class="form-control form-control-sm" placeholder="输入商品名称搜索"
								value="${goodsName}" />
						</div>
						<div class="form-group">
							<input id="content" type="text"
								class="form-control form-control-sm" placeholder="输入评价内容搜索"
								value="${content}" />
						</div>
						<button type="button" class="btn btn-sm btn-success"
							id="searchBtn">筛选</button>
					</form>
				</div>
			</div>
			<div class="col-md-12 height70p">
				<table class="table border-bottom" style="table-layout: fixed; ">
					<thead>
						<tr>
							<th width="60px">序号</th>
							<th width="100px">评价人</th>
							<th width="100px">商品名称</th>
							<th>评价内容</th>
							<th width="60px	">分数</th>
							<th width="200px">时间</th>
							<th width="100px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${evaluateList.size() > 0}">
							<c:forEach items="${evaluateList}" var="arr" varStatus="status">
								<tr>
									<td><c:out value="${status.count}" /></td>
									<td><c:out value="${arr.user_name}" /></td>
									<td><c:out value="${arr.goods_name}" /></td>
									<td class="div-overflow" title="<c:out value="${arr.content}" />"><c:out value="${arr.content}" /></td>
									<td><c:out value="${arr.score}" /></td>
									<td>
										<c:out value="${arr.create_date.substring(0,19)}" />
									</td>
									<td>
										<button onclick="del('${arr.id}')" type="button"
											class="btn btn-danger btn-sm">删除</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${evaluateList.size() == 0}">
					<div class="col-md-12 text-center">暂无数据</div>
				</c:if>
			</div>
			<div class="col-md-12">
				<c:if test="${evaluateList.size() > 0}">
					<div class="tcdPageCode float-right"></div>
				</c:if>
			</div>
		</div>
	</div>
	<input type="hidden" value="${page}" id="page" />
	<script type="text/javascript">
		$("#searchBtn").click(function(){
			$("#page").val(1);
			jump();
		});
		
		$(".tcdPageCode").createPage({
		    pageCount:<%=pageCount%>,
		    current:<%=curPage%>,
		    backFn:function(p){
		    	$("#page").val(p)
		    	jump();
		    }
	    });
		
		function jump() {
			var content = $("#content").val();
			var goodsName = $("#goodsName").val();
			var page = $("#page").val();
			window.location.href = "manager/evaluate_list?content="+content+"&goodsName="+goodsName+"&page="+page;
		}
		
		function del(id) {
			if (window.confirm("确认是否删除？")) {
				//通过ajax删除
				$.ajax({
					url: "manager/evaluate_delete",
					type: "POST",
					dataType: "text",
					data: {
						id: id
					},
					success: function(ret) {
						jump();
				    },
				    error: function(res){
				    	alert("删除失败，请重新操作！");
				    }
				});
			}
		}
	</script>
</body>
</html>