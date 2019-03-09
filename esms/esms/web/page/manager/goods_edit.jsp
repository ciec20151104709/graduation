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
<title>商品编辑</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="css/manager/style.css" type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>

<script language='javascript' src='jquery/ajaxfileupload.js'></script>
<style type="text/css">
.row-margin {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row row-margin">
			<div class="col-md-4 offset-1">
				<form id="uploadForm" class="form-horizontal"
					enctype="multipart/form-data" method="POST">
					<input type="hidden" id="id" value="${goods.get('id') }" />
					<div class="form-group">
						名称<input type="text" class="form-control form-control-sm"
							id="name" placeholder="输入名称" value="${goods.get('name') }" />
					</div>
					<div class="form-group">
						类别<select id="categoryId" class="form-control form-control-sm">
							<option value="">类别</option>
							<c:forEach items="${categoryList}" var="arr">
								<option value="${arr.id}"
									<c:if test="${arr.id == goods.get('category_id') }">selected</c:if>>${arr.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						数量<input type="number" class="form-control form-control-sm"
							id="stock" placeholder="输入数量" value="${goods.get('stock') }" />
					</div>
					<div class="form-group">
						单位<input type="text" class="form-control form-control-sm"
							id="unit" placeholder="输入单位" value="${goods.get('unit') }" />
					</div>
					<div class="form-group">
						<input type="file" name="upload_file" id="upload_file" /> <img
							id="uploadImg" width=80 height=60
							src="/upload/${goods.get('image') }" />
					</div>
					<input type="hidden" id="img" value="${goods.get('image') }" />
					<button type="button" class="btn btn-primary" onclick="saveGoods()">保存</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#upload_file").change(function() {
			var filePath = $("#upload_file").val();
			if (filePath == null || filePath == "") {
				alert("请选择上传文件");
			}
			var hz = filePath.substring(filePath.indexOf("."),
					filePath.length).toLowerCase();
			var imgHz = ".jpg.jpeg.bmp.gif.png";

			if (imgHz.indexOf(hz) > -1) {
				var formData = new FormData($("#uploadForm")[0]);
				$.ajax({
					type : "POST",
					url : "upload",
					data : formData,
					dataType : "text",
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					error : function() {
						alert("上传失败");
					},
					success : function(data) {
						if (data == "ERROR") {
							alert("上传失败，请重新上传");
						} else {
							$("#img").val(data);
							$("#uploadImg").attr("src",
									"/upload/" + data);
						}
					}
				});
			} else {
				alert("请上传图片格式文件");
			}
		});

		function saveGoods() {
			var id = $("#id").val();
			var name = $("#name").val();
			var categoryId = $("#categoryId").val();
			var stock = $("#stock").val();
			var unit = $("#unit").val();
			var img = $("#img").val();
			if (name == "" || categoryId == "" || stock == "" || unit == ""
					|| img == "") {
				alert("请全部填写！");
				return;
			}
			//通过ajax保存
			$.ajax({
				url : "manager/goods_save",
				type : "POST",
				dataType : "text",
				data : {
					id: id,
					name : name,
					categoryId : categoryId,
					stock : stock,
					unit : unit,
					img : img
				},
				success : function(ret) {
					if (ret == "ERROR") {
						alert("保存失败");
					} else {
						alert("保存成功");
						window.close();
					}
				},
				error : function(res) {
					alert("保存失败，请重新操作！");
				}
			});
		}
	</script>
</body>
</html>