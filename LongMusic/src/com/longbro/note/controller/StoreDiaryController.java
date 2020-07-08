
package com.longbro.note.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.longbro.common.BaseResult;
import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.bean.StoreDiary;
import com.longbro.note.service.NoteBookService;
import com.longbro.note.service.StoreDiaryService;

import common.Logger;
/**
 * d_store控制器
 * @author longbro
 * @date 2019-10-22 20:28:10
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/store/")
public class StoreDiaryController{
    @Autowired
    StoreDiaryService storeDiaryService;
    @Autowired 
    NoteBookService noteBookService;
    private Logger logger=Logger.getLogger(PraiseDiaryController.class);
    /**
     * @desc 1.添加收藏记录
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="storeDiary",method=RequestMethod.GET)
    @ResponseBody
    public void storeDiary(StoreDiary diary){
    	storeDiaryService.create(diary);
    	noteBookService.alterTypeNumAdd(diary.getSDiary(), 3);
    }
    /**
     * @desc 2.取消收藏
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="removeStoreDiary",method=RequestMethod.GET)
    @ResponseBody
    public void removeStoreDiary(StoreDiary diary){
    	storeDiaryService.remove(diary);
    }
    /**
     * @desc 3.根据日记id和当前登录用户判断登录用户是否已收藏
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="getStore",method=RequestMethod.GET)
    @ResponseBody
    public StoreDiary getStore(StoreDiary diary){
    	return storeDiaryService.get(diary);
    }
    /**
     * @desc 4.查询某用户收藏的日记
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     * @return
     */
    @RequestMapping(value="getMyStoreDiary",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<HashMap<String, Object>>> getMyStoreDiary(String userId,
    		String author,int page,int perPage){
    	BaseResult<List<HashMap<String, Object>>> result=new BaseResult<>();
    	int start=perPage*(page-1);
    	HashMap<String,Object> map=new HashMap<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	map.put("userId",userId);map.put("author",author);
    	map.put("start",start);map.put("perPage",perPage);
    	result.setResult(storeDiaryService.getStoreDiarybyUser(map));;
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    
    /**
     * 5.得到我的所有被收藏的消息
     * @author LongBro
     * 2019年12月5日
     * 下午12:36:39
     * @param userId
     * @return
     */
    @RequestMapping(value="getMyMessage",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<HashMap<String, String>> getMyMessage(String userId){
    	BaseResult<HashMap<String, String>> result=new BaseResult<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	result.setResult(storeDiaryService.getMyMessage(userId));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * @desc 6.得到收藏数量
     * @author zcl
     * @date 2020年1月29日
     * @param diary
     * @return
     */
    @RequestMapping(value="getStoreNum",method=RequestMethod.GET)
    @ResponseBody
    public int getStoreNum(StoreDiary diary){
    	logger.debug("getStoreNum=========>"+new Gson().toJson(diary));
    	int num=storeDiaryService.getStoreNum(diary);
    	return num;
    }
}
