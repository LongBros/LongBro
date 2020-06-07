
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

import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.CommentDiary;
import com.longbro.note.service.CommentDiaryService;
import com.longbro.note.service.NoteBookService;
import com.longbro.util.TimeUtil;
/**
 * 日记评论表控制器
 * @author longbro
 * @date 2019-10-26 07:50:14
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/comment/")
public class CommentDiaryController{
    @Autowired
    CommentDiaryService commentDiaryService;
    @Autowired 
    NoteBookService noteBookService;
    /**
     * @desc 1.添加评论，评论某日记
     * @author zcl
     * @date 2019年10月26日
     * @param cd
     */
    @RequestMapping("commentDiary")
    @ResponseBody
    public BaseResult<Integer> commentDiary(CommentDiary cd){
    	BaseResult<Integer> bs=new BaseResult<>();
    	String time=TimeUtil.time();
    	cd.setCReviewTime(time);
    	cd.setCReadStatus(0);
    	bs.setCode(200);
    	bs.setMessage("评论成功，请勿重复提交！");
    	bs.setResult(commentDiaryService.create(cd));
    	noteBookService.alterTypeNumAdd(cd.getCReviewedDiary(),1);
    	return bs;
    }
    /**
     * @desc 2.得到某日记的所有评论
     * @author zcl
     * @date 2019年10月26日
     * @param id
     * @return
     */
    @RequestMapping("getComByDiaryId")
    @ResponseBody
    public BaseResult<HashMap<String, String>> getComByDiaryId(int id){
    	BaseResult<HashMap<String, String>> result=new BaseResult<>();
    	result.setResult(commentDiaryService.getComByDiaryId(id));
    	result.setCode(200);
    	result.setMessage("查询日记评论成功");
    	return result;
    }
    /**
     * 3.得到我的所有被评论的消息
     * @author LongBro
     * 2019年12月3日
     * 下午12:47:38
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
    	result.setResult(commentDiaryService.getMyMessage(userId));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * 4.得到登录人评论的信息
     * @author LongBro
     * 2019年12月17日
     * 下午12:56:03
     * @param userId
     * @return
     */
    @RequestMapping(value="getMyComment",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<HashMap<String, String>> getMyComment(String userId){
    	BaseResult<HashMap<String, String>> result=new BaseResult<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	result.setResult(commentDiaryService.getMyComment(userId));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
}
