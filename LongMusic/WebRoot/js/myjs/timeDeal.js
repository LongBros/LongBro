/*************************************
 * 处理时间的一些函数
 * 1.时分秒倒计时2.将word格式化后返回，去零格式化3.将word格式化后返回，补零格式化4.获取当前时间并显示
 * 5.将 Sat Jul 20 2019 15:33:35 GMT+0800 (中国标准时间) 转化为6.月份转换	英文简称->数字
 * 7.星期转换	英文简称->中文星期8.根据秒参数，将其转换为分和秒的组合00:00格式并返回
 *************************************/
/**
 * 1.时分秒倒计时
 */
function timeCount(){
	var timer=document.getElementById("timer").innerText+"";
	var h=timer.substring(0, 2);//24:00:00
	var m=timer.substring(3, 5);
	var s=timer.substring(6, 8); 
	if(formatW(s)>0){
		s--;
		s=formatW1(s);
	}else{//秒到0时，判断分，秒变为59
		s=59;
		if(formatW(m)>0){
			m--;
			m=formatW1(m);
		}else{//分到0时，判断时，分变为59
			m=59;
			if(formatW(h)>0){
				h--;
				h=formatW1(h);
			}
		}
	}
	document.getElementById("timer").innerText=h+":"+m+":"+s;
}
/**
 * 2.将word格式化后返回，去零格式化
 * 例：00返回0、06返回6、12返回12
 * @param word
 * @returns {String}
 */
function formatW(word){
	word=word+"";
	if(word.substring(0, 1)=="0"){
		word=word.substring(1, 2);
	}
	return word;
}
/**
 * 3.将word格式化后返回，补零格式化
 * 例0返回00、8返回08、12返回12
 * @param word
 * @returns
 */
function formatW1(word){
	word=word+"";
	if(word.length==1){
		word="0"+word;
		return word;
	}
	return word;
}
/**
 * 4.获取当前时间并显示
 */
function getNow(){
	var now=document.getElementById("nowTime");
	now.innerText=formatW2(new Date()+"");
}
/**
 * 5.将
 * Sat Jul 20 2019 15:33:35 GMT+0800 (中国标准时间)
 * 转化为
 * 2019-07-20 15:33:35
 * @param word
 * @returns
 */
function formatW2(word){
	word=word+"";
	var ss=word.split(" ");
	var year=ss[3];
	var month=formatMonth(ss[1]);
	var day=ss[2];
	var time=ss[4];
	var week=formatWeek(ss[0]);
	return year+"-"+month+"-"+day+" "+time+" "+week+" ";
}
/**
 * 6.月份转换	英文简称->数字
 * @param month
 * @returns
 */
function formatMonth(month){
	var ms=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var msm=["01","02","03","04","05","06","07","08","09","10","11","12"];
	for(var i=0;i<12;i++){
		if(month==ms[i]){
			month=msm[i];
			return month;
		}
	}
}
/**
 * 7.星期转换	英文简称->中文星期
 * @param week
 * @returns
 */
function formatWeek(week){
	var ws=["Mon","Tue","Wed","Thu","Fri","Sat","Sun"];
	var wsm=["星期一","星期二","星期三","星期四","星期五","星期六","星期日"];
	for(var i=0;i<7;i++){
		if(week==ws[i]){
			week=wsm[i];
			return week;
		}
	}
}
/**
 * 8.根据秒参数，将其转换为分和秒的组合00:00格式并返回
 * 例若time=195，返回03:15
 * @param time
 * @returns {String}
 */
function getTime(time) {//根据秒换算为分和秒
	time=time.substring(0, time.indexOf(".", 0));
    //var min=Math.round(time/60)+"";//分钟---此方法在秒为31时会直接将分得为1，不可靠
	//整型分钟数加空字符变为字符型方可执行下方的.length方法
	var min=parseInt(time/60)+"";
    var sec=time%60+"";//秒
    //保持格式为00:00
    if(min.length==1){
    	min="0"+min;
    }
    if(sec.length==1){
    	sec="0"+sec;
    }
    return min+":"+sec;
}
//9.根据时间获取相对于当前时间的 2020-03-08 23:16:11
function handleTime(time){
	var stime=time+"";
	
	if(stime.length>19){//含有星期
		stime=stime.substring(0,19);
	}
	stime = stime.replace(/-/g,'/');
	var date=new Date(stime);

	//转化为时间戳
	var launchTime=date.getTime();//发布时间
	var nowTime=new Date().getTime();//当前时间
	if(launchTime>nowTime){
		return time;
	}else{
		//时间差
		var t=convertTo1(parseInt((nowTime-launchTime)/1000));
		return t;
	}
}
//10.秒轉換為相對於當前時間的格式  1h=3600
function convertTo(time){
	if(time>0&&time<60){
		return time+"秒前";
	}else if(time>=60&&time<=3600){
		if(time%60==0){
			return parseInt(time/60)+"分钟前";
		}else{
			return parseInt(time/60)+"分钟"+parseInt(time%60)+"秒前";
		}
	}else if(time>3600&time<=60*60*24){//1~24h
		return parseInt(time/3600)+"小时"+parseInt(parseInt(time%3600)/60)+"分钟前";
	}else if(time>(60*60*24)&&time<=(60*60*24*7)){//1~7天
		return parseInt(time/(60*60*24))+"天"+(parseInt(parseInt(time%(60*60*24))/3600))+"小时前";
		//return parseInt(time/(60*60*24))+"天前";
	}else if(time>(60*60*24*7)&&time<=(60*60*24*31)){//1周~1月
		return parseInt(time/(60*60*24*7))+"周前";
	}else if(time>(60*60*24*31)&&time<=(60*60*24*31*12)){//1月~12月
		return parseInt(time/(60*60*24*31))+"月前";
	}else if(time>(60*60*24*31*12)){
		return parseInt(time/(60*60*24*31*12))+"年前";
	}
}
//11.第二種 秒轉換為相對於當前時間的格式  1h=3600
function convertTo1(time){
	if(time>0&&time<60){
		return time+"秒前";
	}else if(time>=60&&time<=3600){
		if(time%60==0){
			return parseInt(time/60)+"分钟前";
		}else{
			return parseInt(time/60)+"分钟"+parseInt(time%60)+"秒前";
		}
	}else if(time>3600&time<=60*60*24){//1~24h
		return parseInt(time/3600)+"小时"+parseInt(parseInt(time%3600)/60)+"分钟前";
	}else if(time>(60*60*24)&&time<=(60*60*24*3365)){//1~3365天
		return parseInt(time/(60*60*24))+"天"+(parseInt(parseInt(time%(60*60*24))/3600))+"小时前";
		//return parseInt(time/(60*60*24))+"天前";
	}
}