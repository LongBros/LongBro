package com.longbro.note.controller;
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

import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.bean.StoreDiary;
import com.longbro.note.service.PraiseDiaryService;
/**
 * 日记点赞表控制器
 * 1.
 * 2.
 * @author longbro
 * @date 2019-10-25 21:51:50
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
public class PraiseDiaryController{
    @Autowired
    PraiseDiaryService praiseDiaryService;
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
    public int getPraiseNum(String diaryId){
    	return praiseDiaryService.getPraiseNum(diaryId);
    }
    
}
