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
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Confide;
import com.longbro.note.service.ConfideService;
import com.longbro.util.TimeUtil;
/**
 * 倾诉墙表控制器
 * @author longbro
 * @date 2020-03-14 12:37:00
 * @copyright 哆啦学娱网络科技有限公司
 */
@RequestMapping("/note/confide/")
@Controller
public class ConfideController{
    @Autowired
    ConfideService confideService;
    /**
     * @desc 1.向某人发出倾诉 
     * @author zcl
     * @date 2020年3月14日
     * @param confide
     * @return
     */
    @RequestMapping("confideOther")
    @ResponseBody
    public BaseResult confideOther(Confide confide){
    	BaseResult result=new BaseResult<>();
//    	confide.setWConfideTime(TimeUtil.getToday());
    	confideService.create(confide);
    	result.setCode(200);
    	result.setMessage("倾诉成功");
    	result.setResult(null);
    	return result;
    }
    /**
     * @desc 2.根据用户id获取某用户的所有倾诉
     * @author zcl
     * @date 2020年3月14日
     * @return
     */
    @RequestMapping(value="getConfides",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult getConfides(HttpServletRequest request){
    	BaseResult result=new BaseResult<>();
    	String author=request.getParameter("author");
    	String page=request.getParameter("page");
    	String perPage=request.getParameter("perPage");
    	HashMap<String,String> map=new HashMap<>();
    	map.put("author",author);
    	List<HashMap> list=confideService.getConfides(map);
    	System.out.println(new Gson().toJson(list));
    	result.setResult(list);
    	result.setCode(200);
    	result.setNum(list.size());
    	result.setMessage("已为你加载出"+author+"的倾诉墙");
    	return result;
    }
}
