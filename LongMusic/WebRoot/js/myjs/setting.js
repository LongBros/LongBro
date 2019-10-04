//将云歌曲添加至铃声库
function add() {
    var items = listbox1.getSelecteds();
    var items2 = myMusic.getData();
    if(items.length==0){
    	mini.alert("请至少选中一首歌曲！");
    	return;
    }
    if((items.length+items2.length)>20){
    	mini.alert("你最多可设置20条铃声！");
    	return;
    }
    listbox1.removeItems(items);
    myMusic.addItems(items);
}
//添加所有歌曲至铃声库
function addAll() {        
    var items = listbox1.getData();
    var items2 = myMusic.getSelecteds();
    if((items.length+items2.length)>20){
    	mini.alert("你最多可设置20条铃声！");
    	return;
    }
    listbox1.removeItems(items);
    myMusic.addItems(items);
}
//移出铃声库
function removes() {
    var items = myMusic.getSelecteds();
    if(items.length==0){
    	mini.alert("请至少选中一首歌曲再移出！");
    	return;
    }
    myMusic.removeItems(items);
    listbox1.addItems(items);
}
//清空铃声库
function removeAll() {
    var items = myMusic.getData();
    myMusic.removeItems(items);
    listbox1.addItems(items);
}
//试听歌曲
function listen(){
	var items = listbox1.getSelecteds();
	if(items.length<1){
    	mini.showTips(myAlert("请选中左侧歌曲以试听！"));
    	return;
	}
	mini.showTips(myAlert("将试听歌曲-'"+items[0].songName+"'"));
	var sid=items[0].sourceId+"";
	var url="";
	if(sid.substring(sid.length-4)=="html"){//QQ音乐
		url="http://link.hhtjim.com/qq/"+sid.substring(0, sid.length-5)+".mp3";
	}else{
		url="http://music.163.com/song/media/outer/url?id="+sid+".mp3";
	}
	//alert(url);
	var audio=document.getElementById("audio");
	audio.setAttribute("src", url);
	audio.play();
}
//铃声库某首歌曲上移
function upItem() {
    var items = myMusic.getSelecteds();
    if(items.length==0){
    	mini.alert("请至少选中一首歌曲再上移！");
    	return;
    }
    for (var i = 0, l = items.length; i < l; i++) {
        var item = items[i];
        var index = myMusic.indexOf(item);
        myMusic.moveItem(item, index-1);
    }
}
//铃声库歌曲下移
function downItem() {            
    var items = myMusic.getSelecteds();   
    if(items.length==0){
    	mini.alert("请至少选中一首歌曲再下移！");
    	return;
    }
    for (var i = items.length-1; i >=0; i--) {
        var item = items[i];
        var index = myMusic.indexOf(item);
        myMusic.moveItem(item, index + 1);
    }
}

//保存或更新我的库
function saveData1() {
    var data = myMusic.getData();
    var sd=data[0].sourceId+":::"+data[0].songName;
    //var re = /^[0-9]+.?[0-9]*/;
    for(var i=1;i<data.length;i++){
    	var sid=data[i].sourceId+"";
    	if(sid.substring(sid.length-4)=="html"){//剔除QQ音乐歌曲
    		mini.showTips(myAlert1("'"+data[i].songName+"'暂不支持设置为铃声，已去除！","center","top"))
    	}else{
    		sd=sd+"&&&"+data[i].sourceId+":::"+data[i].songName;
    	}
    }
    var json = sd;
    var userId=getCookie("user")+"";
    $.ajax({
    	type:"GET",
    	url:"updateMusicGit.do",
    	async:true,
    	data:{
    		userId:userId,
    		music:json
    	},
    	
    })
    mini.showTips(myAlert("更新成功，上一页面需刷新后生效"))
}


//加载某页的歌曲
function loadPage(page){
	listbox1.setUrl("queryAllSongs.do?page="+page);
}
//新增提示语
function saveData2() {
    var tip = mini.get("ntip").value+"";
    if(tip==""){
        mini.showTips(myAlert("你输入的内容为空！"))
        return ;
    }
	var ts=mini.get("myTips").getData();
	var tsD="",tsP="";
	for(var i=0;i<ts.length;i++){
		if(tip==ts[i].tip){
	        mini.showTips(myAlert("提示语库中已存在，无需新增！"))
	        return;
		}
		tsD=ts[i].tip+"&&&"+tsD;//数据库
		//页面显示格式
		tsP=tsP+"{'tip':'"+ts[i].tip+"','createtime':''},";
	}
	//把新建的附到尾部
	tsP="["+tsP+"{'tip':'"+tip+"','createtime':''}]";
	tsD=tsD+tip;
	mini.get("myTips").setData(tsP);//刷新提示语区域
	//存至数据库
	var userId=getCookie("user")+"";
    $.ajax({
    	type:"GET",
    	url:"updateTipsGit.do",
    	async:true,
    	data:{
    		userId:userId,
    		aTips:tsD
    	}
    	
    })
    mini.showTips(myAlert("successfully!新增提示语成功"))
}
//
function openWords(){
	var words=document.getElementById("words").style.display;
	if(words==="inline-block"){//打开状态时关闭
		document.getElementById("words").style.display="none";
		document.getElementById("oacb").innerHTML="展开更多<i class='Hui-iconfont' style='font-size:18px'>&#xe6d5;</i>";
	}else{
		document.getElementById("words").style.display="inline-block";
		document.getElementById("oacb").innerHTML="折叠<i class='Hui-iconfont' style='font-size:18px'>&#xe6d6;</i>";
	}
}