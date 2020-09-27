/**
 * 1.hui账单
 * 
 */
function queryBill(page){
	$('#bill').text('');
	$.ajax({
		type:"POST",
		async:false,
		url:"../queryAllBill.do?page="+page,
		dataType:"Json",
		success:function(data){
			//alert("共"+data.length+"笔账单
			for(var k=0;k<data.length;k++){
				var remark=data[k].remark+"";
				if(remark.length>10){
					remark=remark.substring(0,10)+"...";
				}
				var time=data[k].time+"";
				var date=time.substring(0,10);
				
				$('#bill').append("<tr class=\"text-c\">" +
				"<td><input type=\"checkbox\" value=\"\" name=\"\"></td><td>"+data[k].id+"</td>" +
				"<td>"+data[k].payutil+"</td><td>"+data[k].in_out+"</td><td>"+data[k].cate+"</td><td>"+data[k].amount+"</td><td>"+remark+"</td><td>"+time+"</td>" +
				"<td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> " +
				"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit('账单编辑','article-add.html','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
				"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td></tr>");
			}
		}
	});
}

/**
 * 2.按分页查询歌曲
 * @param page
 */
function querySongs(page){
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryAllSongs.do?page="+page,
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				var na=data[k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				na=na+"("+data[k].playNum+")";
				var url="";//源网址
				var id=data[k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				$('#song').append("<tr class=\"text-c\">" +
						"<td><input type=\"checkbox\" value=\"\" name=\"\"></td>" +
						"<td>"+data[k].id+"</td><td>"+na+"</td><td>"+data[k].singer+"</td>" +
						"<td>"+data[k].duration+"</td><td>"+data[k].album+"</td>" +
						"<td>"+data[k].releaseTime+"</td><td>"+data[k].inputTime+"</td>" +
						"<td>"+web+"</td>" +
						"<td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"songEdit('歌曲编辑','add-song.html','"+data[k].id+"','"+data[k].id+"')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"delSong(this,'"+data[k].id+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td></tr>");
			}
		}
	});
}
//3.加载歌单
function loadSongList(){
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongList.do",
		dataType:"Json",
		success:function(data){
			for(var k=0;k<data.length;k++){
				$('#songList').append("<tr class=\"text-c\"><td><input name=\"\" type=\"checkbox\" value=\"\"></td><td>"+data[k].id+"</td><td>"+data[k].name+"</td><td>"+data[k].name+"</td><td>"+data[k].songs+"</td><td>"+data[k].time+"</td><td>"+data[k].descr+"</td><td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit('资讯编辑','song-add.html','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td></tr>");
			}
		}
	});
}
//4.加载评论
function loadCom(){
	var ac="../queryComment.do";
	$.ajax({
		type:"Get",
		async:false,
		url:ac,
		dataType:"json",
		success:function(data){
			for(var k=0;k<data.length;k++){
				$('#comments').append("<tr class=\"text-c\"><td><input name=\"\" type=\"checkbox\" value=\"\"></td><td>"+data[k].id+"</td><td>"+data[k].c_Type+"</td><td>"+data[k].c_Reviewed+"</td><td>"+data[k].c_Content+"</td><td>"+data[k].c_Time+"</td><td>"+data[k].c_Reviewer+"</td><td>"+data[k].c_Address+"</td><td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit('资讯编辑','article-add.html','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td></tr>");
			}
		}
	});
}
//5.加载日记
function queryDiarys(page){
	//清空原有歌曲列表
	$('#diary').text('');
	$('#num').text('');
	//加载歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"http://www.duola.vip/note/diary/getDiaryBy.do",
		data:{
			page:page,
			perPage:20,
			authority:'0,1,2'
		},
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				$('#diary').append("<tr class=\"text-c\">" +
						"<td><input type=\"checkbox\" value=\"\" name=\"\"></td>" +
						"<td>"+data[k].nid+"</td><td>"+data[k].ntitle+"</td><td>"+data[k].nwritter+"</td>" +
						"<td>"+data[k].userName+"</td><td>"+data[k].nSongId+"</td>" +
						"<td>"+data[k].audioInfo+"</td><td>"+data[k].ntime+"</td>" +
						"<td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"diaryEdit('日记编辑','add-diary.html','"+data[k].nid+"','"+data[k].nid+"')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"delDiary(this,'"+data[k].nid+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td></tr>");
			}
		}
	});
}