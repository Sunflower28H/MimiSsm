<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--导入标准标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
	</head>
    <script type="text/javascript">
        function fileChange(){
			<!--使用封装好的ajax方法-->
			$.ajaxFileUpload({
				url:"${pageContext.request.contextPath}/prod/ajaxImg.action",
				secureuri: false,<!--是否遵循安全协议-->
				fileElementId: 'pimage',<!--文件上传控件的id属性-->
				dataType:"json",
				success:function (obj) {
					<!--上传成功后的操作-->
					//创建一个图片标签
					var imgobj = $("<img>");
					//设置图片标签的属性
					imgobj.attr("src","/image_big/"+obj.imgurl);
					imgobj.attr("width","100px");
					imgobj.attr("height","100px");
					//将图片追加到对应div位置
					$("#imgDiv").append(imgobj);
				}
			})
		}
    </script>
	<body>
	<!--取出上一个页面上带来的page的值-->

		<div id="addAll">
			<div id="nav">
				<p>商品管理>新增商品</p>
			</div>

			<div id="table">
				<form  id="myform" action="${pageContext.request.contextPath}/prod/save.action">
					<table>
						<tr>
							<td class="one">商品名称</td>
							<td><input type="text" name="pName" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pnameerr"></span></td>
						</tr>
						<tr>
							<td class="one">商品介绍</td>
							<td><input type="text" name="pContent" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pcontenterr"></span></td>
						</tr>
						<tr>
							<td class="one">定价</td>
							<td><input type="number" name="pPrice" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="priceerr"></span></td>
						</tr>
						
						<tr>
							<td class="three">图片介绍</td>
                            <td> <br>
								<!--存放显示图片的div--><div id="imgDiv" style="display:block; width: 40px; height: 50px;"></div><br><br><br><br>
                            <input type="file" id="pimage" name="pimage" onchange="fileChange()" >
								<!--显示图片name的span-->
								<span id="imgName" ></span><br>

                            </td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						
						<tr>
							<td class="one">总数量</td>
							<td><input type="number" name="pNumber" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="numerr"></span></td>
						</tr>

						<tr>
							<td class="one">类别</td>
							<td>
								<!--name：提交给服务器端要使用的属性-->
								<select name="typeId" >
									<c:forEach items="${typeList}" var="type">
									<option value="${type.typeId}">${type.typeName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<input type="reset" value="取消" class="btn btn-default" onclick="myclose(${param.page})">
								<script type="text/javascript">
									function myclose(ispage) {
										window.location="${pageContext.request.contextPath}/prod/split.action?page="+ispage;
									}
								</script>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>