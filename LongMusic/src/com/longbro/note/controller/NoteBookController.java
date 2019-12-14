package com.longbro.note.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Diary;
import com.longbro.note.bean.NoteBook;
import com.longbro.note.service.NoteBookService;
import com.longbro.util.SpideLapuda;
import com.longbro.util.TimeUtil;
/**
 * 笔记本控制器
 * @author longbro
 * @date 2019-10-09 21:11:32
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/diary/")
public class NoteBookController{
//	Logger log = Logger.getLogger(AttentionController.class);
    @Autowired
    NoteBookService noteBookService;
    /**
     * @desc 1.写日记或编辑日记
     * @author zcl
     * @date 2019年10月19日&11-24
     * @param req
     * @param nb
     */
    @RequestMapping(value="addOrEditNote",method=RequestMethod.POST)
    @ResponseBody
    public BaseResult addOrEditNote(NoteBook nb){
    	BaseResult result=new BaseResult<>();
    	System.out.println("id:"+nb.getNId());
    	if(nb.getNId()==0){//无id时新增
    		nb.setnTop(0);
    		nb.setnUserTop(0);
        	noteBookService.addNote(nb);
        	result.setMessage("日记发布成功，请勿重复提交");
//        	log.info("日记发布成功");
    	}else{
        	nb.setUpdateTime(TimeUtil.time());
        	noteBookService.editDiary(nb);
        	result.setMessage("日记修改成功，请勿重复提交");
//        	log.info("日记修改成功");
    	}
    	result.setCode(200);
    	return result;
    }
    /**
     * @desc 2.分页加载笔记(弃用，使用5)
     * @author zcl
     * @date 2019年10月13日
     * @param page
     */
    @RequestMapping(value="getDiaryByPage",method=RequestMethod.GET)
    @ResponseBody
    public List<NoteBook> getDiaryByPage(int page){
//    	log.info("开始加载第"+page+"页的笔记");
    	return noteBookService.getDiaryByPage(page);
    }
    /**
     * @desc 3.加载某日记
     * @author zcl
     * @date 2019年10月13日
     * @param id
     * @return
     */
    @RequestMapping(value="getDiaryById",method=RequestMethod.GET)
    @ResponseBody
    public NoteBook getDiaryById(int id){
//    	log.info("开始加载id为："+id+"的笔记");
    	return noteBookService.getDiaryById(id);
    }
    /**
     * @desc 4.根据……获取对应日记数量，比如时间、分类、作者、、
     * @author zcl
     * @date 2019年10月19日
     * @return
     */
    @RequestMapping(value="getDiaryNumBy",method=RequestMethod.GET)
    @ResponseBody
    public int getDiaryNumBy(NoteBook diary){
//    	log.info("开始获取指定笔记数量");
    	return noteBookService.getDiaryNumBy(diary);
    }
    /**
     * @desc 5.根据……加载日记，例如:作者，分类，心情等
     * @author zcl
     * @date 2019年10月19日
     * @param diary
     * @return
     */
    @RequestMapping(value="getDiaryBy",method=RequestMethod.GET)
    @ResponseBody
    public List<Diary> getDiaryBy(HttpServletRequest request){
    	genDiary();
//    	log.info("开始加载笔记");
    	int per=10;
    	HashMap<String, String> map=new HashMap<>();
    	if(StringUtils.isNotEmpty(request.getParameter("author")))
    		map.put("author", request.getParameter("author"));
    	if(StringUtils.isNotEmpty(request.getParameter("perPage")))
    	{
    		map.put("perPage", request.getParameter("perPage"));
    		per=Integer.parseInt(request.getParameter("perPage"));
    	}
    	if(StringUtils.isNotEmpty(request.getParameter("authority")))
    		map.put("authority", request.getParameter("authority"));
    	if(StringUtils.isNotEmpty(request.getParameter("page")))
    		map.put("page", (Integer.parseInt(request.getParameter("page"))-1)*per+"");
    	//12-05新增，黑名单功能使用
    	if(StringUtils.isNotEmpty(request.getParameter("user")))
    		map.put("user", request.getParameter("user"));
    	System.out.println(TimeUtil.time()+"-------->查询日记列表所传参数:"+new Gson().toJson(map));
    	return noteBookService.getDiaryBy(map);
    }
    /**
     * 6.得到和当前日记同作者的上一篇和下一篇日记的ID
     * @desc 
     * @author zcl
     * @date 2019年11月3日
     * @param id	当前日记id
     * @param author当前日记的作者
     */
    @RequestMapping(value="getBeforeAndNextId",method=RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String,Object>> getBeforeAndNextId(int id,String author){
//    	log.info("开始查询当前笔记的上下篇");
    	return noteBookService.getBeforeAndNextId(id, author);
    }
    /**
     * @desc 7.每天随机生成歌词网和古诗网的日记
     * @author zcl
     * @date 2019年11月30日
     */
    public void genDiary(){
    	int num=noteBookService.ifHasGen(TimeUtil.getToday(), "66666666");
    	if(num>0){//今日已生成过
        	System.out.println("今日已生成过");
    		return;
    	}
    	int machines[]={66666666,88888888};
		for(int account:machines){
			System.out.println(">>>>>>>>>>>>>将生成用户"+account+"的日记");
			String table="poem";
			String idd="";//日记在原本数据表中的ID，方便修改状态为“已使用”状态
			if(account==88888888){
				table="song";
			}
			List<HashMap<String, Object>> list=noteBookService.getDiaryByTable(table);
			System.out.println(">>>>>>>>>>>>>用户"+account+"未被使用的内容数量为"+list.size());
			int i=new Random().nextInt(list.size());
			System.out.println(">>>>>>>>>>>>>用户"+account+"今日将被使用的内容的序号为"+i);
			HashMap<String, Object> map=list.get(i);
			NoteBook nb=new NoteBook();
			nb.setNWritter(account+"");
			nb.setNTime(TimeUtil.getToday()+" "+TimeUtil.genRandomTime());
			nb.setNAuthority(0);//所有人可见
			nb.setNType(3);
			nb.setNAllowComment(0);//允许评论
			nb.setNWeather(0);
			nb.setNMood(0);
			nb.setnTop(0);
			nb.setnUserTop(0);
			System.out.println(">>>>>>>>>>>>>用户"+account+"今日被使用的内容的实体为"+new Gson().toJson(map));
			if(account==88888888){//歌曲
				nb.setNTitle(map.get("songName")+"-"+map.get("singer"));
				nb.setNContent(map.get("lyric")+"");
				nb.setnSongId(map.get("sourceId")+"");
				nb.setNLocation("河南省平顶山市");
				idd=map.get("id")+"";
			}else{//古诗
				nb.setNTitle(map.get("p_Name")+"-"+map.get("p_Poet"));
				nb.setNContent(map.get("p_PoemCons")+"");
				nb.setNLocation("河南省邓州市");
				idd=map.get("p_Id")+"";
			}
			noteBookService.addNote(nb);//插入笔记
			//修改使用状态，万万不可用i
			noteBookService.alterUseStatus(table, TimeUtil.time(), idd);
	    	System.out.println(">>>>>>>>>>>>>已生成今日"+account+"的日记为第"+idd);

		}
    }
    /**
     * 8.庆兔兔日记每日录入
     * @author LongBro
     * 2019年12月13日
     * 下午3:03:56
     * @param id
     */
    @RequestMapping(value="spideLapuda",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult spideLapuda(int id){
    	BaseResult result=new BaseResult<>();
    	if(StringUtils.isEmpty(id+"")){
    		result.setCode(100);
    		result.setMessage("待爬取的日记id不能为空");
    		return result;
    	}
    	HashMap<String, String> map=SpideLapuda.spideLapuda(id);
    	NoteBook nb=new NoteBook();
    	nb.setNWritter("65313340");
    	nb.setNType(0);
    	nb.setNTitle(map.get("title"));
    	nb.setNContent(map.get("content"));
    	nb.setNTime(map.get("time").substring(0, 10)+" "+TimeUtil.genRandomTime());
    	nb.setNLocation("兔子窝");
    	nb.setNWeather(0);
    	nb.setNMood(0);
    	nb.setNAllowComment(0);
    	nb.setNAuthority(0);
    	nb.setnTop(0);
    	nb.setnUserTop(0);
    	noteBookService.addNote(nb);
    	result.setCode(200);
    	result.setMessage("爬取庆兔兔日记成功");
    	result.setResult(map.get("title"));
    	return result;
    }
}
