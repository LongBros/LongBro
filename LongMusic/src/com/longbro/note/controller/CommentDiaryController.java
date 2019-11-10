
package com.longbro.note.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.note.bean.CommentDiary;
import com.longbro.note.service.CommentDiaryService;
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
    @RequestMapping("commentDiary")
    @ResponseBody
    public void commentDiary(CommentDiary cd){
    	String time=TimeUtil.time();
    	cd.setCReviewer("123141414");
    	cd.setCReviewTime(time);
    	cd.setCReadStatus(0);
    	commentDiaryService.create(cd);
    }
    @RequestMapping("getComByDiaryId")
    @ResponseBody
    public List<CommentDiary> getComByDiaryId(int id){
    	
    	return commentDiaryService.getComByDiaryId(id);
    }
}
