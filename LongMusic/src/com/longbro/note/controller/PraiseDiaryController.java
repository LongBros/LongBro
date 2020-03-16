package com.longbro.note.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.bean.StoreDiary;
import com.longbro.note.service.PraiseDiaryService;
import common.Logger;
/**
 * 日记点赞表控制器
 * 1.添加点赞记录2.取消点赞3.根据日记id和当前登录用户判断登录用户是否已点赞
 * 4.根据日记id得到对应日记的被点赞数量、查询当前登录用户未读点赞消息数
 * @author longbro
 * @date 2019-10-25 21:51:50
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/praise/")
public class PraiseDiaryController{
    @Autowired
    PraiseDiaryService praiseDiaryService;
    private Logger logger=Logger.getLogger(PraiseDiaryController.class);
    /**
     * @desc 1.添加点赞记录
     * @author zcl
     * @date 2019年10月25日
     * @param diary
     */
    @RequestMapping(value="praiseDiary",method=RequestMethod.GET)
    @ResponseBody
    public void praiseDiary(PraiseDiary diary){
    	praiseDiaryService.create(diary);
    }
    /**
     * @desc 2.取消点赞
     * @author zcl
     * @date 2019年10月25日
     * @param diary
     */
    @RequestMapping(value="removePraiseDiary",method=RequestMethod.GET)
    @ResponseBody
    public void removePraiseDiary(PraiseDiary diary){
    	praiseDiaryService.remove(diary);
    }
    /**
     * @desc 3.根据日记id和当前登录用户判断登录用户是否已点赞
     * @author zcl
     * @date 2019年10月25日
     * @param diary
     * @return
     */
    @RequestMapping(value="getPraise",method=RequestMethod.GET)
    @ResponseBody
    public PraiseDiary get(PraiseDiary diary){
    	logger.debug("=========>"+new Gson().toJson(diary));
    	return praiseDiaryService.get(diary);
    }
    /**
     * @desc 4.根据日记id得到对应日记的被点赞数量、查询当前登录用户未读点赞消息数
     * @author zcl
     * @date 2019年10月25日
     * @param diary
     * @return
     */
    @RequestMapping(value="getPraiseNum",method=RequestMethod.GET)
    @ResponseBody
    public int getPraiseNum(PraiseDiary diary){
    	logger.debug("getPraiseNum=========>"+new Gson().toJson(diary));
    	int num=praiseDiaryService.getPraiseNum(diary);
    	return num;
    }
    /**
     * @desc 5.得到某用户喜欢的日记
     * @author zcl
     * @date 2019年11月10日
     */
    @RequestMapping(value="getMyLikeDiary",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<PraiseDiary>> getMyLikeDiary(String userId,
    		String author,int page,int perPage){
    	BaseResult<List<PraiseDiary>> result=new BaseResult<>();
    	int start=perPage*(page-1);
    	HashMap<String,Object> map=new HashMap<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	map.put("userId",userId);map.put("author",author);
    	map.put("start",start);map.put("perPage",perPage);
    	result.setResult(praiseDiaryService.getMyLikeDiary(map));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * 6.得到我的所有被赞的消息
     * @author LongBro
     * 2019年12月3日
     * 下午12:40:00
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
    	result.setResult(praiseDiaryService.getMyMessage(userId));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
}
