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
<title>电子超市</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="css/manager/style.css" type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery.page.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>
<style type="text/css">
header {
	width: 100%;
	height: 40px;
	background: #333;
	color: #b0b0b0;
}

header span {
	height: 40px;
	line-height: 40px;
	font-size: 14px;
}

.naver-ul {
	text-decoration: none;
	list-style-type: none;
	height: 100%;
}

.naver-li {
	float: left;
	font-size: 12px;
	color: #CCC;
	padding-left: 5px;
	height: 40px;
	line-height: 40px;
	display: block;
}

.naver-a {
	font-size: 12px;
	font-family: 'Times New Roman';
	color: #b0b0b0;
	cursor: pointer;
}

.banner {
	width: 100%;
	height: 100px;
	border-bottom: 1px solid #CCC;
	padding: 25px 0;
}

.banner-ul {
	text-decoration: none;
	list-style-type: none;
	height: 100%;
}

.banner-li {
	float: left;
	font-size: 12px;
	color: #CCC;
	padding-left: 5px;
	height: 40px;
	line-height: 40px;
	display: block;
}

.banner-a {
	font-size: 14px;
	font-family: 'Times New Roman';
	color: #b0b0b0;
	cursor: pointer;
}

.logo {
	width: 50px;
	height: 50px;
}

.goods-logo {
	width: 150px;
	height: 50px;
}

.content-title {
	width: 100%;
	height: 40px;
	line-height: 40px;
	color: black;
	font-weight: bold;
}

.content-item {
	height: 280px;
	width: 90%;
	border: 1px solid #CCC;
	margin: 0 auto;
	cursor: pointer;
}

.footer {
	line-height: 100px;
	height: 100px;
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

.activity {
	font-weight: bold;
	font-size:16px;
}
</style>
</head>
<body>
	<%
		int pageCount = (int) request.getAttribute("pageCount");
		int curPage = (int) request.getAttribute("page");
	%>
	<header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8 height40px">
				<!-- 图片 -->
				<span>电子超市欢迎您</span>
			</div>
			<div class="col-md-4 float-right height40px">
				<!-- 操作按钮 -->
				<ul class="naver-ul float-right">
					<li class="naver-li"><a class="naver-a">登录</a></li>
					<li class="naver-li">|</li>
					<li class="naver-li"><a class="naver-a">注册</a></li>
					<li class="naver-li">|</li>
					<li class="naver-li"><a class="naver-a">我的订单</a></li>
				</ul>
			</div>
		</div>
	</div>
	</header>
	<div class="banner">
		<div class="container-fluid height100p">
			<div class="row height100p">
				<div class="col-md-3 float-right">
					<img class="logo" src="image/logo.png" /> <img class="goods-logo"
						src="image/goods-logo.gif" />
				</div>
				<div class="col-md-6">
					<!-- 操作按钮 -->
					<ul id="banner-ul" class="banner-ul">
						<li class="banner-li <c:if test='${categoryId == "" }'>activity</c:if>" onclick="changeCategory(this,'')" ><aclass="banner-a" style="color: #000">全部</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "01" }'>activity</c:if>" onclick="changeCategory(this,'01')"><a class="banner-a" style="color: #000">酒饮</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "02" }'>activity</c:if>" onclick="changeCategory(this,'02')"><a class="banner-a" style="color: #000">洗涤</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "03" }'>activity</c:if>" onclick="changeCategory(this,'03')"><a class="banner-a" style="color: #000">冲调</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "04" }'>activity</c:if>" onclick="changeCategory(this,'04')"><a class="banner-a" style="color: #000">家居</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "05" }'>activity</c:if>" onclick="changeCategory(this,'05')"><a class="banner-a" style="color: #000">休闲</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "06" }'>activity</c:if>" onclick="changeCategory(this,'06')"><a class="banner-a" style="color: #000">文体</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "07" }'>activity</c:if>" onclick="changeCategory(this,'07')"><a class="banner-a" style="color: #000">粮油</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "08" }'>activity</c:if>" onclick="changeCategory(this,'08')"><a class="banner-a" style="color: #000">针织</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "09" }'>activity</c:if>" onclick="changeCategory(this,'09')"><a class="banner-a" style="color: #000">果鲜</a>
						</li>
						<li class="banner-li">|</li>
						<li class="banner-li <c:if test='${categoryId == "10" }'>activity</c:if>" onclick="changeCategory(this,'10')"><a class="banner-a" style="color: #000">家电</a>
						</li>
					</ul>
				</div>
				<div class="col-md-3">
					<form class="form-inline float-right">
						<div class="form-group">
							<input id="name" type="text"
								class="form-control form-control-sm" placeholder="输入内容搜索"
								value="${name }" />
						</div>
						<button type="button" class="btn btn-sm btn-info" id="searchBtn">搜索</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="content-title" style="font-size: 22px;">商品展示</div>
		<div class="container-fluid">
			<div class="row">
				<c:if test="${goodsList.size() > 0}">
					<c:forEach items="${goodsList}" var="arr" varStatus="status">
						<div class="col-md-2 height300px" style="padding-top: 10px;">
							<div class="content-item">
								<img src="/upload/${arr.image }" width="100%" height="200px" />
								<div style="height:100px;padding-top:15px;">
									<div style="width: 100%;text-align: center; color:#000; font-size: 14px;">${arr.name}</div>
									<div style="width: 100%;text-align: center; color:#ff6700; font-size: 14px;">${arr.stock}${arr.unit}</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${goodsList.size() == 0}">
					<div class="col-md-12 text-center">暂无数据</div>
				</c:if>
				<div class="col-md-12 text-center">
					<c:if test="${goodsList.size() > 0}">
						<div class="tcdPageCode"></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer height100px bg-light">
	<div class="col-md-12 text-center footer-content">Copyright ©
		2019 电子超市管理系统 SuperMarket - All Rights Reserved.</div>
	</footer>
	<input type="hidden" value="${page}" id="page" />
	<input type="hidden" value="${categoryId}" id="categoryId" />
	<script type="text/javascript">
		$("#searchBtn").click(function() {
			$("#page").val(1);
			jump();
		});

		$(".tcdPageCode").createPage({
			pageCount: <%=pageCount%>,
			current: <%=curPage%>,
			backFn : function(p) {
				$("#page").val(p);
				jump();
			}
		});

		function jump() {
			var categoryId = $("#categoryId").val();
			var name = $("#name").val();
			var page = $("#page").val();
			window.location.href = "user/main_page?categoryId="
					+ categoryId + "&name=" + name + "&page=" + page;
		}
		
		function changeCategory(sel, obj) {
			$("#categoryId").val(obj);
			
			
			jump();
			$(sel).attr("class", "banner-li activity");
		}

	</script>
</body>
</html>