package com.longbro.note.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.bean.AlarmUser;
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Author;
import com.longbro.note.bean.UserInfo;
import com.longbro.note.service.CommentDiaryService;
import com.longbro.note.service.PraiseDiaryService;
import com.longbro.note.service.StoreDiaryService;
import com.longbro.note.service.UserInfoService;
import com.longbro.service.CommentService;
import com.longbro.util.Strings;
import com.longbro.util.TimeUtil;
/**
 * d_user_info控制器
 * @author longbro
 * @date 2019-10-26 13:07:00
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/userinfo/")
public class UserInfoController{
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    CommentDiaryService commentDiaryService;
    @Autowired
    PraiseDiaryService praiseDiaryService;
    @Autowired
    StoreDiaryService storeDiaryService;
    /**
     * @desc 1.根据userId获取用户信息
     * @author zcl
     * @date 2019年10月26日
     * @param UUserId
     * @return
     */
    @RequestMapping("getAuthorInfoByUserId")
    @ResponseBody
    public Author getAuthorInfoByUserId(int UUserId){
    	return userInfoService.get(UUserId);
    }
    /**
     * 2.执行此方法必向数据库插入一条AlarmUser
     * 先判断数据库是否已有该userId，再插入不同的userId
     * @desc 
     * @author zcl
     * @date 2019年10月1日
     * @return
     */
    @RequestMapping(value="genNoteUserId",method=RequestMethod.GET)
    @ResponseBody
    public String genUserId(String source){
    	String userId=Strings.allotNum(8)+"";//随机生成userId
    	UserInfo ui=userInfoService.get(Integer.parseInt(userId));
    	if(ui!=null){//该用户id已存在，需重新生成
    		genUserId(source);
    	}
    	ui=new UserInfo();
    	ui.setUUserId(Integer.parseInt(userId));
    	ui.setUJoinTime(TimeUtil.time());
//    	au.setSource(source);
    	userInfoService.create(ui);
    	return userId;
    }
    /**
     * @desc 3.登录哆啦日记网的校验
     * @author zcl
     * @date 2019年10月27日
     * @param acc
     * @param pass
     * @return
     */
    @RequestMapping(value="loginNote",method=RequestMethod.POST)
    @ResponseBody
    public BaseResult<String> loginNote(String acc,String pass,HttpServletResponse response){
    	BaseResult<String> result=new BaseResult<String>();
    	UserInfo ui=userInfoService.loginNote(acc, pass);
    	if(ui!=null){
    		Cookie cookie=new Cookie("userId", acc);
    		cookie.setMaxAge(30*24*60*60);
    		cookie.setPath("/");
    		response.addCookie(cookie);
    		Cookie cookie1=new Cookie("userNick", ui.getUUserName());
    		cookie1.setMaxAge(30*24*60*60);
    		cookie1.setPath("/");
    		response.addCookie(cookie1);
    		Cookie cookie2=new Cookie("userAddr", ui.getLocation());
    		cookie2.setMaxAge(30*24*60*60);
    		cookie2.setPath("/");
    		response.addCookie(cookie2);
    		System.out.println(cookie.getValue());
    		result.setCode(200);
    		result.setMessage("登录成功");
    		result.setResult(1);
    		return result;
    	}
    	result.setCode(110);
    	result.setMessage("请检查你输入的账号");
    	result.setResult(null);
    	return result;
    }
    /**
     * @desc 4.查询某用户或所有用户的未读数量
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     */
    @RequestMapping("queryUnReadNum")
    @ResponseBody
    public BaseResult<String> queryUnReadNum(String userId){
    	BaseResult<String> result=new BaseResult<String>();
    	List<HashMap<String, Object>> map=new ArrayList<HashMap<String, Object>>();
    	result.setCode(200);
    	result.setResult(userInfoService.queryUnReadNum(userId));
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * @desc 5.设置所有未读消息为已读
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     * @return
     */
    @RequestMapping("setAsReaded")
    @ResponseBody
    public BaseResult<String> setAsReaded(String userId){
    	BaseResult<String> result=new BaseResult<String>();
    	commentDiaryService.setAsReaded(userId);
    	praiseDiaryService.setAsReaded(userId);
    	storeDiaryService.setAsReaded(userId);
    	result.setCode(200);
    	result.setMessage("已设置所有未读消息为已读");
    	result.setResult(null);
    	return result;
    }
    /**
     * @desc 加载某用户或所有用户的互动信息：
     * 赞、评论、收藏、关注,被赞、被评论、被收藏、被关注
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     * @return
     */
    @RequestMapping("queryInteractNum")
    @ResponseBody
	public BaseResult<List<HashMap<String, Object>>> queryInteractNum(String userId){
		BaseResult<List<HashMap<String, Object>>> result=new BaseResult<List<HashMap<String, Object>>>();
		result.setCode(200);
    	result.setMessage("已加载完毕");
    	result.setResult(userInfoService.queryInteractNum(userId));
    	
    	return result;
	}
}
