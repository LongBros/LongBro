package com.longbro.note.controller;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.note.bean.Diary;
import com.longbro.note.bean.NoteBook;
import com.longbro.note.service.NoteBookService;
/**
 * 笔记本控制器
 * @author longbro
 * @date 2019-10-09 21:11:32
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/diary/")
public class NoteBookController{
    @Autowired
    NoteBookService noteBookService;
    /**
     * @desc 1.写日记-新建日记
     * @author zcl
     * @date 2019年10月19日
     * @param req
     * @param nb
     */
    @RequestMapping(value="addNote",method=RequestMethod.GET)
    @ResponseBody
    public void addNote(HttpServletRequest req,NoteBook nb){
//    	String loc=req.getParameter("location");
//    	String wea=req.getParameter("weather");
//    	String mood=req.getParameter("mood");
//    	String title=req.getParameter("title");
//    	String content=req.getParameter("content");
//    	String allowcomment=req.getParameter("allowcomment");
//    	String authority=req.getParameter("authority");
//    	String category=req.getParameter("category");
//    	NoteBook nb=new NoteBook();
//    	nb.setNLocation(loc);nb.setNWeather(Integer.parseInt(wea));
//    	nb.setNMood(Integer.parseInt(mood));nb.setNTitle(title);
//    	nb.setNContent(content);nb.setNType(Integer.parseInt(category));
//    	nb.setNAllowComment(Integer.parseInt(allowcomment));
//    	nb.setNAuthority(Integer.parseInt(authority));
    	noteBookService.addNote(nb);
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
    	int per=10;
    	HashMap<String, String> map=new HashMap<>();
    	if(StringUtils.isNotEmpty(request.getParameter("author")))
    		map.put("author", request.getParameter("author"));
    	if(StringUtils.isNotEmpty(request.getParameter("perPage")))
    	{
    		map.put("perPage", request.getParameter("perPage"));
    		per=Integer.parseInt(request.getParameter("perPage"));
    	}
    	if(StringUtils.isNotEmpty(request.getParameter("page")))
    		map.put("page", (Integer.parseInt(request.getParameter("page"))-1)*per+"");
    	
    	return noteBookService.getDiaryBy(map);
    }
    /**
     * 得到和当前日记同作者的上一篇和下一篇日记的ID
     * @desc 
     * @author zcl
     * @date 2019年11月3日
     * @param id	当前日记id
     * @param author当前日记的作者
     */
    @RequestMapping(value="getBeforeAndNextId",method=RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String,Object>> getBeforeAndNextId(int id,String author){
    	return noteBookService.getBeforeAndNextId(id, author);
    }
}
