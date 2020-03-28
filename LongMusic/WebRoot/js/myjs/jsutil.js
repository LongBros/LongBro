function getMoodById(id){
	switch(id){
		case 0:
			return "开心";
			break;
		case 1:
			return "微笑";
			break;
		case 2:
			return "哭脸";
			break;
		case 3:
			return "愤怒";
			break;
	}
}
function getCateById(id){
	var cate="0";
	if(id=="0"){
		cate="生活日记";
	}else if(id=="1"){
		cate="工作笔记";
	}else if(id=="2"){
		cate="idea";
	}else if(id=="3"){
		cate="诗词(文学)";
	}else if(id=="4"){
		cate="深度好文";
	}
	return cate;
}
function getWeaById(id){
	var wea="0";
	if(id=="0"){
		wea="晴";
	}else if(id=="1"){
		wea="雾";
	}else if(id=="2"){
		wea="霾";
	}else if(id=="3"){
		wea="阴";
	}else if(id=="4"){
		wea="雨";
	}else if(id=="5"){
		wea="雪";
	}
	return wea;
}
function getAuthById(id){
	var au="0";
	if(id=="0"){
		au="完全公开";
	}else if(id=="1"){
		au="自己可见";
	}else if(id=="2"){
		au="登录可见";
	}else if(id=="3"){
		au="删除";
	}
	return au;
}