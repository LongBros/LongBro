
package com.longbro.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.bean.AlarmUser;
import com.longbro.service.AlarmUserService;
import com.longbro.util.Strings;
import com.longbro.util.TimeUtil;
/**
 * 闹铃用户表控制器
 * @author longbro
 * @date 2019-10-01 00:42:29
 */
@Controller
public class AlarmUserController{
    @Autowired
    AlarmUserService alarmUserService;
    /**
     * 执行此方法必向数据库插入一条AlarmUser
     * 先判断数据库是否已有该userId，再插入不同的userId
     * @desc 
     * @author zcl
     * @date 2019年10月1日
     * @return
     */
    @RequestMapping(value="genUserId",method=RequestMethod.GET)
    @ResponseBody
    public String genUserId(){
    	String userId=Strings.allotNum(8)+"";//随机生成userId
    	AlarmUser au=alarmUserService.getById(userId);
    	if(au!=null){//该用户id已存在，需重新生成
    		genUserId();
    	}
    	au=new AlarmUser();
    	au.setAUserid(Integer.parseInt(userId));
    	au.setACreatetime(TimeUtil.time());
    	alarmUserService.addAlarmUser(au);
    	return userId;
    }
    /**
     * 更新用户的音乐库
     * @desc 
     * @author zcl
     * @date 2019年10月4日
     * @param userId
     * @param music
     */
    @RequestMapping(value="updateMusicGit",method=RequestMethod.GET)
    @ResponseBody
    public void updateMusicGit(int userId,String music){
    	AlarmUser au=new AlarmUser();
    	au.setAUserid(userId);
    	au.setAMusics(music);
    	alarmUserService.updateAlarmUser(au);
    }
    @RequestMapping(value="updateTipsGit",method=RequestMethod.GET)
    @ResponseBody
    public void updateTipsGit(int userId,String aTips){
    	AlarmUser au=new AlarmUser();
    	au.setAUserid(userId);
    	au.setATips(aTips);
    	alarmUserService.updateAlarmUser(au);
    }
    @RequestMapping(value="updateInfo",method=RequestMethod.GET)
    @ResponseBody
    public void updateInfo(int userId,String userName,String sex,String sign){
    	AlarmUser au=new AlarmUser();
    	au.setAUserid(userId);
    	au.setAUsername(userName);
    	au.setAUsersign(sign);
    	alarmUserService.updateAlarmUser(au);
    }
    @RequestMapping(value="getByUserId",method=RequestMethod.GET)
    @ResponseBody
    public AlarmUser getByUserId(String userId){
    	AlarmUser au=alarmUserService.getById(userId);
    	return au;
    }
}
