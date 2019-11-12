package com.longbro.note.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.bean.AlarmUser;
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Author;
import com.longbro.note.bean.UserInfo;
import com.longbro.note.service.UserInfoService;
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
     * @desc 登录哆啦日记网的校验
     * @author zcl
     * @date 2019年10月27日
     * @param acc
     * @param pass
     * @return
     */
    @RequestMapping(value="loginNote",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<UserInfo> loginNote(String acc,String pass){
    	BaseResult<UserInfo> result=new BaseResult<UserInfo>();
    	UserInfo ui=userInfoService.loginNote(acc, pass);
    	if(ui!=null){
    		result.setCode(200);
    		result.setMessage("登录成功");
    		result.setResult(ui);
    		return result;
    	}
    	result.setCode(110);
    	result.setMessage("请检查你输入的账号");
    	result.setResult(null);
    	return result;
    }
}
