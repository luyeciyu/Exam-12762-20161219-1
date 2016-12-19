<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.hand.saklia.util.DBUtil,java.util.List,java.util.ArrayList ,java.util.Map" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/simplePagination.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simplePagination.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/filmInfo.js"></script>
<title>信息显示</title>
</head>
<body>
	<div class="container" style="margin: 50px auto;">
		<div class="row" > 
			<div class="col-md-4" onclick="doAdd()" style="cursor: pointer;">新增</div>
			<!-- <div class="col-md-4">编辑</div> -->
		</div>
		<div class="row">
			<div>
				<table class="table table-bordered table-hover table-condensed">
					<!-- 表头 -->
					<thead>
						<tr>
							<th>序号</th>
							<th>标题</th>
							<th max-widtn="100px">描述</th>
							<th>语言</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="myTable">
						<!-- <tr>
							<td>爱情公寓</td>
							<td>都市爱情喜剧</td>
							<td>2010</td>
						</tr> -->
						
					</tbody>
			</table>
			</div>
			<div id="page" style="display: none;margin-left: 30%"></div>
		</div>
	</div>
	
	<!-- 删除窗口 -->
	<div class="modal" id="deleteBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  	&times;
	            	</button>
	            	<h4 class="modal-title" id="myModalLabel">
	              	 	删除
	          		 </h4>
				</div>
				<div class="modal-body">
					<div id="deleteText"></div>
         			<div id="delete_film_id" style="display:none;"></div>
				</div>
				<div class="modal-footer">
		            <button type="button" class="btn btn-primary btn-default" onclick="deleteItem();">增加</button>
		            <button type="button" class="btn" data-dismiss="modal">关闭</button>
	         	</div>

			</div>
		</div>
	</div>
	
	<%
		DBUtil dbUtil = new DBUtil();
		String sql = "select name,language_id from language";
		ArrayList<Map> language = (ArrayList<Map>)dbUtil.queryForList(sql);
	%>
	
	<!-- 新增窗口 -->
	<div class="modal" id="addBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  	&times;
	            	</button>
	            	<h4 class="modal-title" id="myModalLabel">
	              	 	新增
	          		 </h4>
				</div>
				<div class="modal-body">
					<table style="width: 100%">
		         		<tr height="40px">
						<td align="right">标题:</td>
		         		<td align="left"><input id="add_film_title" type="text" name="add_film_title"  size="20"  />
									<span style="color: red">*</span>
							</td>
						<td align="right">语言:</td>
		         		<td align="left">
	         				<select name="add_language" id="add_language" style="padding:1px;" >
	         					<option value="0">--请选择--</option>
	         				<%
	         				if(language != null && language.size() > 0){
	         					for(int i = 0;i < language.size();i++){
				        			out.write("<option value='"+language.get(i).get("LANGUAGE_ID")+"'>" +language.get(i).get("NAME") +"</option>");
				        		}
	         				}
	         				%>
						    </select>
						    <span style="color: red">*</span>
						</td>
		          		</tr>
		         
			        <tr height="40px">
			        <td align="right">描述:</td>
			        <td align="left"> 
			         	<textarea id="add_description" name="add_description"></textarea>		
					</td>
			        </tr>
		
	       			</table>
				</div>
				<div class="modal-footer">
		            <button type="button" class="btn btn-primary btn-default" onclick="addItem();">增加</button>
		            <button type="button" class="btn" data-dismiss="modal">关闭</button>
	         	</div>

			</div>
		</div>
	</div>
	
	
	<!-- 编辑窗口 -->
	<div class="modal" id="modifyBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  	&times;
	            	</button>
	            	<h4 class="modal-title" id="myModalLabel">
	              	 	编辑
	          		 </h4>
				</div>
				<div class="modal-body">
					<!-- <div id="mod_film_id" style="display:none;"></div> -->
					<input type="text" name="mod_film_id" id="mod_film_id" hidden="true">
					<table style="width: 100%">
		         		<tr height="40px">
						<td align="right">标题:</td>
		         		<td align="left"><input id="mod_film_title" type="text" name="mod_film_title"  size="20"  />
									<span style="color: red">*</span>
							</td>
						<td align="right">语言:</td>
		         		<td align="left">
	         				<select name="mod_language" id="mod_language" style="padding:1px;" >
	         					<option value="0">--请选择--</option>
	         				<%
	         				if(language != null && language.size() > 0){
	         					for(int i = 0;i < language.size();i++){
				        			out.write("<option value='"+language.get(i).get("LANGUAGE_ID")+"'>" +language.get(i).get("NAME") +"</option>");
				        		}
	         				}
	         				%>
						    </select>
						    <span style="color: red">*</span>
						</td>
		          		</tr>
		         
			        <tr height="40px">
			        <td align="right">描述:</td>
			        <td align="left"> 
			         	<textarea id="mod_description" name="mod_description"></textarea>		
					</td>
			        </tr>
		
	       			</table>
				</div>
				<div class="modal-footer">
		            <button type="button" class="btn btn-primary btn-default" onclick="modifyItem();">确定</button>
		            <button type="button" class="btn" data-dismiss="modal">关闭</button>
	         	</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>