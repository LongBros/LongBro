
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
import com.longbro.note.service.StoreDiaryService;
/**
 * d_store控制器
 * @author longbro
 * @date 2019-10-22 20:28:10
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
public class StoreDiaryController{
    @Autowired
    StoreDiaryService storeDiaryService;
    /**
     * @desc 添加收藏记录
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="storeDiary",method=RequestMethod.GET)
    @ResponseBody
    public void storeDiary(StoreDiary diary){
    	storeDiaryService.create(diary);
    }
    /**
     * @desc 取消收藏
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="removeStoreDiary",method=RequestMethod.GET)
    @ResponseBody
    public void removeStoreDiary(StoreDiary diary){
    	storeDiaryService.remove(diary);
    }
    /**
     * @desc 根据日记id和当前登录用户判断登录用户是否已收藏
     * @author zcl
     * @date 2019年10月22日
     * @param diary
     */
    @RequestMapping(value="getStore",method=RequestMethod.GET)
    @ResponseBody
    public StoreDiary getStore(StoreDiary diary){
    	return storeDiaryService.get(diary);
    }
}
