
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

import com.longbro.bean.Alarm;
import com.longbro.service.AlarmService;
import com.longbro.util.TimeUtil;
/**
 * 闹铃表(Alarm)控制器
 * @author longbro
 * @date 2019-10-01 00:42:08
 */
@Controller
public class AlarmController{
    @Autowired
    AlarmService alarmService;
    /**
     * 1.根据userId得到用户设置的闹铃
     * @desc 
     * @author zcl
     * @date 2019年10月1日
     * @param userId
     * @return
     */
    @RequestMapping(value="getAlarmByUserId",method=RequestMethod.GET)
    @ResponseBody
    public List<Alarm> getAlarmByUserId(String userId){
    	
    	return alarmService.getAlarmByUserId(userId);
    }
    /**
     * 2.为userId添加闹铃
     * @desc 
     * @author zcl
     * @date 2019年10月1日
     * @param request
     */
    @RequestMapping(value="addAlarmForUser",method=RequestMethod.GET)
    @ResponseBody
    public String addAlarm(HttpServletRequest request){
    	String userId=request.getParameter("userId");
    	String time=request.getParameter("time");
    	String tip=request.getParameter("tip");
    	String music=request.getParameter("music");
    	Alarm alarm=new Alarm();
    	alarm.setAUserid(userId);
    	alarm.setATime(time);
    	alarm.setATips(tip);
    	alarm.setAMusic(music);
    	alarm.setAStatus(0);
    	alarm.setACreatetime(TimeUtil.time());
    	if(alarmService.isAlarmExits(userId, time)){
    		return "Error!You have set this alarm before";
    	}
    	alarmService.addAlarm(alarm);
    	return "Setting alarm successfully";
    }
    @RequestMapping(value="delAlarmByAId",method=RequestMethod.GET)
    @ResponseBody
    public String delAlarmByAId(int aId){
    	//虽然说是删除，但实际是修改状态
    	alarmService.updateAStatusById(aId);
    	return "delete the alarm successfully!";
    }
}
