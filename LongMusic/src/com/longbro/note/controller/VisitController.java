
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

import com.longbro.note.bean.StoreDiary;
import com.longbro.note.bean.Visit;
import com.longbro.note.service.NoteBookService;
import com.longbro.note.service.VisitService;
import com.longbro.util.TimeUtil;
/**
 * 日记访问记录表控制器
 * @author longbro
 * @date 2019-11-18 21:04:53
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("note/visit/")
public class VisitController{
    @Autowired
    VisitService visitService;
    @Autowired 
    NoteBookService noteBookService;
    /**
     * @desc 添加用户访问日记的记录
     * @author zcl
     * @date 2019年11月18日
     * @param visit
     */
    @RequestMapping(value="addVisitRecord",method=RequestMethod.GET)
    @ResponseBody
    public void addVisitRecord(Visit visit){
    	visit.setVVisitTime(TimeUtil.time());
    	visit.setVReadStatus("0");
    	visitService.create(visit);
    	
    	noteBookService.alterTypeNumAdd(visit.getVDiary(), 0);
    }
}
