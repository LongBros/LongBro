/**
 * 评论Song或SongList
 * @param type 表示评论音乐，1表示评论歌单
 * @param id	被评论的歌曲的id
 */
function commentMusic(type,id){
	var con=document.getElementById("comment").value+"";
	if(con.length==0){
		alert("评论内容不能为空");
		return;
	}
	var au="../addComment.do?c_Type="+type+"&c_Reviewed="+id+"&c_Content="+con;
	$.ajax({
		type:"Post",
		async:false,
		url:au,
		dataType:"text",
		success:function(data){
			
		}
	});
	loadComment(type,id);//发表成功后重新加载评论
	document.getElementById("comment").value="";
}
function loadComment(c_Type,id){
	$('#comments').text('');
	var ac="../queryComment.do?c_Type="+c_Type+"&c_Reviewed="+id;
	$.ajax({
		type:"Get",
		async:false,
		url:ac,
		dataType:"json",
		success:function(data1){
			for(var k=0;k<data1.length;k++){
				$('#comments').append("<hr>");
				$('#comments').append(data1[k].c_Reviewer+"&nbsp;"+data1[k].c_Time);
				$('#comments').append("<br>"+data1[k].c_Content);
			}
		}
	});
}