/**
 * 按照分页查询所有账单
 * 根据账单信息（支出/收入,分类，输入备注）等查询账单
 * 切换支出和收入的分类
 * 点击新增后显示表单
 * 输入表单后异步添加账单
 * 根据账单id编辑账单
 * 根据账单id删除账单
 */
/**
 *  按照分页查询所有账单
 * @param page
 */
function queryBill(page){
	$('#bill').text('');
	$.ajax({
		type:"POST",
		async:false,
		url:"../queryAllBill.do?page="+page,
		dataType:"Json",
		success:function(data){
			//alert("共"+data.length+"笔账单");
			for(var k=0;k<data.length;k++){
				var remark=data[k].remark+"";
				if(remark.length>10){
					remark=remark.substring(0,10)+"...";
				}
				var time=data[k].time+"";
				var date=time.substring(0,10);
				
				$('#bill').append("<tr><td><input type='checkbox'/></td><td>"+data[k].id+"</td><td>"+data[k].payutil+"</td><td>"+data[k].in_out+"</td><td>"+data[k].cate+"</td><td>"+data[k].amount+"</td><td title='"+data[k].remark+"'>"+remark+"</td><td title='"+time+"'>"+date+"</td><td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\"><button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[k].id+")'><span class=\"am-icon-pencil-square-o\"></span> 编辑</button><button class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick='deleteAccById("+data[k].id+")'><span class=\"am-icon-trash-o\"></span> 删除</button></div></div></td></tr>");

				
//				var picture=data[k].picture+"";
//				if(picture.length!=0){
//					picture="有图";
//				}
//				$('#bill').append("<td>"+picture+"</td>");
				
			
//				$('#bill').append("<button class=\"am-btn am-btn-default am-btn-xs am-hide-sm-only\"><span class=\"am-icon-copy\"></span> 复制</button>");

			}
		}
	});
}
/**
 * 根据账单信息（支出/收入,分类，输入备注）等查询账单
 */
function queryBillBy(){
	//强大搜索功能
	var payutil=document.getElementById("payutil").value+"";
	var inout=document.getElementById("searCate").value+"";
	var cate=document.getElementById("category1").value+"";
	var key=document.getElementById("key").value+"";
	var year=document.getElementById("year").value+"";
	var month=document.getElementById("month").value+"";
	var date="";
	if(month=="请选择"){
		date=year;
	}else{
		date=year+"-"+month;
	}
	if(payutil.substring(0, 3)=="请选择") payutil="";
	if(cate.substring(0, 3)=="请选择") cate="";
	if(inout.substring(0, 3)=="请选择") inout="";
	if(key.length==0&payutil.length==0&cate==0&inout.length==0&date.length==0){
		alert("必须选择搜索条件或输入搜索内容");
		return;
	}
	$('#bill').text('');
	var num=0;
	var allIn=0;
	var allOut=0;
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryBillBy.do?payutil="+payutil+"&category="+cate+"&in_out="+inout+"&key="+key+"&time="+date,
		dataType:"Json",
		success:function(data){
			num=data.length;
			for(var k=0;k<data.length;k++){
				if(data[k].in_out=="收入"){
					allIn=allIn+data[k].amount;
				}else{
					allOut=allOut+data[k].amount;
				}
				$('#bill').append("<tr>");
				$('#bill').append("<td><input type='checkbox'/></td>");
				$('#bill').append("<td>"+data[k].id+"</td>");
				$('#bill').append("<td>"+data[k].payutil+"</td>");

				$('#bill').append("<td>"+data[k].in_out+"</td>");
				$('#bill').append("<td>"+data[k].cate+"</td>");
				$('#bill').append("<td>"+data[k].amount+"</td>");
				var remark=data[k].remark+"";
				if(remark.length>10){
					remark=remark.substring(0,10)+"...";
				}
				$('#bill').append("<td title='"+data[k].remark+"'  class=\"am-hide-sm-only\">"+remark+"</td>");
//				var picture=data[k].picture+"";
//				if(picture.length!=0){
//					picture="有图";
//				}
//				$('#bill').append("<td>"+picture+"</td>");
				var time=data[k].time+"";
				var date=time.substring(0,10);
				
				$('#bill').append("<td title='"+time+"' class='am-hide-sm-only'>"+date+"</td>");
				$('#bill').append("<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">");
				$('#bill').append("<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[k].id+")'><span class=\"am-icon-pencil-square-o\"></span> 编辑</button>");
//				$('#bill').append("<button class=\"am-btn am-btn-default am-btn-xs am-hide-sm-only\"><span class=\"am-icon-copy\"></span> 复制</button>");
//				$('#bill').append("<button class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\"><span class=\"am-icon-trash-o\"></span> 删除</button>");
				$('#bill').append("</div></div></td>");
				$('#bill').append("</tr>");
			}
//			allOut=Math.round(allOut,2);
			allIn=allIn.toFixed(2);
			allOut=allOut.toFixed(2);
			if(allOut==0){
				alert("共"+num+"笔收入账单,合计收入"+allIn+"元");

			}else if(allIn==0){
				alert("共"+num+"笔支出账单,合计支出"+allOut+"元");
			}else{
				alert("共"+num+"笔账单,合计收入"+allIn+"元，支出"+allOut+"元");
			}
		}
	});
}
/**
 * 切换支出和收入的分类,因共有添加时的和搜索账单时的两种，所以传参数做以区分
 * 其中当是搜索时，要多一个请选择默认选项，不但要加载分类，还要加载对应的账单
 * @param type type="add"时是切换新增的，type="search"时是切换搜索的
 */
function setCate(type){
	var paycate=new Array("交通","早餐","午餐","晚餐","生活","话费","住","娱乐","日常","她们","零食","饮料","服装","学习");
	var incate=new Array("刷单","工资","兼职","软件","代缴电费","其他");
	if(type=="add"){
		$("#category").text('');
		var what=document.getElementById("form").in_out.value+"";
//		alert(what);
		if(what=="支出"){
			for(i=0;i<paycate.length;i++){
				$("#category").append("<option value="+paycate[i]+">"+paycate[i]+"</option>")
			}
		}else{
			for(i=0;i<incate.length;i++){
				$("#category").append("<option value="+incate[i]+">"+incate[i]+"</option>")
			}
		}
	}else if(type=="search"){
		//加载支出或收入对应的账单
//		queryBillBy('1');取消此处直接搜索功能，仅保留搜索按钮的搜索
		//将搜索对应的分类加载出来
		$("#category1").text('');
		var what=document.getElementById("searCate").value+"";
//		alert(what)
		if(what=="支出"){
			$("#category1").append("<option>请选择支出分类</option>")
			for(i=0;i<paycate.length;i++){
				$("#category1").append("<option value="+paycate[i]+">"+paycate[i]+"</option>")
			}
		}else if(what=="收入"){
			$("#category1").append("<option>请选择收入分类</option>")
			for(i=0;i<incate.length;i++){
				$("#category1").append("<option value="+incate[i]+">"+incate[i]+"</option>")
			}
		}else{
			$("#category1").append("<option>请选择收入/支出</option>")
		}
	}
	
}
/**
 * 点击新增后显示表单
 */
function showAdd(){
	var what=document.getElementById("form").style.display;
	if(what=="none"){
		document.getElementById("form").style.display="inline-block";
	}else{
		document.getElementById("form").style.display="none";
	}
//	document.getElementById("form").style.display="inline-block";
}
/**
 * 输入表单后异步添加账单
 */
function addAcc(){
//	alert("");
	var form=document.getElementById("form");
	var util=form.payutil.value;
	var in_out=form.in_out.value;
	var cate=form.category.value;
	
	var amount=form.amount.value+"";
	var remark=form.remark.value+"";
	var time=form.time.value+"";
	if(remark.length==0){//备注为空则将类别作为备注
		remark=cate;
	}
	if(amount.length==0||time.length==0){
		alert("部分信息未输入！！！")
		return ;
	}
	var picture="";
//	alert(util);
	$.ajax({
		type:"Get",
		async:true,
		url:"../addBill.do?time="+time+"&payutil="+util+"&in_out="+in_out+"&category="+cate+"&amount="+amount+"&remark="+remark+"&picture="+picture+"",
		dataType:"Json",
	});
	alert("添加成功");
	queryBill('1');
}
/**
 * 清空输入框
 */
function clearAcc(){
	var form=document.getElementById("form");
	form.amount.value="";
	form.remark.value="";
}
/**
 * 根据账单id编辑账单
 * @param id
 */
function edit(id){
	//alert(id);
	var form=document.getElementById("form");

	$.ajax({
		type:"Get",
		async:false,
		url:"../queryBillById.do?id="+id,
		dataType:"Json",
		success:function(data){
			alert(data.remark)
			form.payutil.value=data[0].payutil;
			form.in_out.value=data[0].in_out;
			form.category.value=data[0].category;
			form.amount.value=data[0].amount;
			form.remark.value=data[0].remark;
			form.time.value=data[0].time;
		}
	});

}
/**
 * 根据账单id删除账单
 * @param id
 */
function deleteAccById(id){
	var r=confirm("确定删除该条账单？");
	if(r==true){
		$.ajax({
			type:"Get",
			async:false,
			url:"../deleteAccById.do?id="+id,
			dataType:"text",
			success:function(data){
				alert(data);
			}
		});
	}else{
		return;
	}
	
}
var value = prompt('本站登录密码：', '');//这么执着的吗？尊重隐私喔
if(value=="145989"){
	queryBill(1);
}else{
	alert("password is error,Exit right now!");
window.open('','_self');
window.close();
}