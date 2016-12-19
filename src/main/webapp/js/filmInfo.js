
var rows = 10;
$(function(){
	doQuery();
});


function doQuery(){
	var page = 1;
	$.ajax({
		url:'film.do',
		type:'post',
		data:{
			func:'find',
			page:page,
			rows:rows
		},
		async:'false',
		success:function(datas){
			var info = JSON.parse(datas); 
			$("#myTable").empty();
			var data = info[0].rows;		
			var total = info[0].total;
			for(var i = 0;i < data.length;i++){
				var pendstr = "<tr>";
				pendstr = pendstr+"<td>"+data[i].FILM_ID+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].TITLE+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].DESCRIPTION+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].NAME+"</td>";
	    		
	    		pendstr = pendstr+"<td> <a href=\"javascript:void(0);\" onclick=\"doDelete('" + i + "');\">删除</a> " +
	    				  " <a href=\"javascript:void(0);\" onclick=\"doModify('" + i + "');\">编辑</a>  </td>";
	    		pendstr = pendstr+"</tr>";
				$("#myTable").append(pendstr);	
			}
			if(data.length > 0){
				$("#page").show();
				$("#page").pagination({
	    			items:total,
	    	        itemsOnPage:rows,
	    			currentPage:page,
	    			prevText:"<",
	    			nextText:">",
	    			cssStyle: "light-theme",
	    			onPageClick: changePage
	    		});			
			}else{
				$("#page").hide();
			}
		},
		error:function(){
		}
	});
}


function changePage(pageNumber, event){
	var page = pageNumber;
	$.ajax({
		url:'film.do',
		type:'post',
		data:{
			func:'find',
			page:page,
			rows:rows
		},
		async:'false',
		success:function(datas){
			var info = JSON.parse(datas); 
			$("#myTable").empty();
			var data = info[0].rows;		
			var total = info[0].total;
			for(var i = 0;i < data.length;i++){
				var pendstr = "<tr>";
				pendstr = pendstr+"<td>"+data[i].FILM_ID+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].TITLE+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].DESCRIPTION+"</td>";
	    		pendstr = pendstr+"<td>"+data[i].NAME+"</td>";
	    		
	    		pendstr = pendstr+"<td> <a href=\"javascript:void(0);\" onclick=\"doDelete('" + i + "');\">删除</a> " +
	    				  " <a href=\"javascript:void(0);\" onclick=\"doModify('" + i + "');\">编辑</a>  </td>";
	    		pendstr = pendstr+"</tr>";
				$("#myTable").append(pendstr);	
			}
			if(data.length > 0){
				$("#page").show();
				$("#page").pagination({
	    			items:total,
	    	        itemsOnPage:rows,
	    			currentPage:page,
	    			prevText:"<",
	    			nextText:">",
	    			cssStyle: "light-theme",
	    			onPageClick: changePage
	    		});			
			}else{
				$("#page").hide();
			}
		},
		error:function(){
		}
	});
	
	return false;
}

function doDelete(i){
	var row=parseInt(i);
	var items_id=$("#myTable tr:eq("+row+")").find("td").eq(0).text();
	$("#deleteText").text("确认删除？");
	$("#delete_film_id").text(items_id);

	$("#deleteBox").modal("show");
	

}
function deleteItem(){
	var delete_film_id = $("#delete_film_id").text();
	$.ajax( {  
	     url:'film.do',// 跳转到 action  
	     async: false,
	     data:{  
	    	 film_id:delete_film_id,
	    	 "func":"delete"
	     },  
	     type:'post',  
	     success:function(data) {  
	    	 if(data.indexOf("成功") > 0 ){
	    		 $('#deleteBox').modal('hide');
	    		 doQuery();
	    		 alert("操作成功！");
	    	 }else
	    		 alert("操作失败！");
	      },  
	      error : function() {  
	    	  alert("操作失败！");
	    	 // $.messager.alert("操作失败", textStatus);
	      }  
	 });
}



function doAdd(){
	$("#addBox").modal("show");
}

function addItem(){
	var add_film_title = $("#add_film_title").val(); 
	var add_language = $("#add_language").val(); 
	var add_description = $("#add_description").val(); 

	if(add_film_title == ""){
		alert("标题不能为空！");
		return;
	}
	if(add_language == "0"){
		alert("请选择语言");
		return;
	}


	$.ajax({
		url:"film.do",
		type:"post",
		data:{
			title:add_film_title,
			language:add_language,
			description:add_description,
			func:"addFilm"
		},
		async:"false",
		success:function(data){
			if(data.indexOf("成功") > 0 ){
	    		$('#addBox').modal('hide');
	    		doQuery();
	    		alert("操作成功！");
	    	}else
	    		alert("操作失败！");
	    },  
	    error : function() {  
	    	alert("操作失败！");
	    	 // $.messager.alert("操作失败", textStatus);
	      }  
	});
}

function doModify(i){
	var row=parseInt(i);
	$("#modifyBox").modal("show");
	$("#mod_film_id").val($("#myTable tr:eq("+row+")").find("td").eq(0).text());
	$("#mod_film_title").val($("#myTable tr:eq("+row+")").find("td").eq(1).text());
	$("#mod_description").val($("#myTable tr:eq("+row+")").find("td").eq(2).text());
	//$("#mod_language").text($("#myTable tr:eq("+row+")").find("td").eq(3).text());


	var language=$("#myTable tr:eq("+row+")").find("td").eq(3).text();
	if(language=='')language='--请选择--';
	$("#mod_language option:contains('"+language+"')").attr("selected",true);
}

function modifyItem(){
	var mod_film_id = $("#mod_film_id").val();
	//alert(mod_film_id); 
	var mod_film_title = $("#mod_film_title").val(); 
	var mod_language = $("#mod_language").val(); 
	var mod_description = $("#mod_description").val(); 

	if(mod_film_title == ""){
		alert("标题不能为空！");
		return;
	}
	if(mod_language == "0"){
		alert("请选择语言");
		return;
	}

	$.ajax({
		url:"film.do",
		type:"post",
		data:{
			filmId:mod_film_id,
			title:mod_film_title,
			language:mod_language,
			description:mod_description,
			func:"modifyFilm"
		},
		async:"false",
		success:function(data){
			if(data.indexOf("成功") > 0 ){
	    		$('#modifyBox').modal('hide');
	    		doQuery();
	    		alert("操作成功！");
	    	}else
	    		alert("操作失败！");
	    },  
	    error : function() {  
	    	alert("操作失败！");
	    	 // $.messager.alert("操作失败", textStatus);
	      }  
	});
}