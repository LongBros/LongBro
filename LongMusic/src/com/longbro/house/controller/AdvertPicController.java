
package com.longbro.house.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.house.bean.AdvertPic;
import com.longbro.house.bean.BaseResult;
import com.longbro.house.service.AdvertPicService;
/**
 * house_advert_pic控制器
 * @author longbro
 * @date 2019-11-09 21:27:43
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/house/advert/")
public class AdvertPicController{
    @Autowired
    AdvertPicService advertPicService;
    @RequestMapping("getPicById")
    @ResponseBody
    public BaseResult<AdvertPic> getPicById(String id){
    	BaseResult<AdvertPic> result=new BaseResult<>();
    	if(StringUtils.isEmpty(id)){
    		result.setCode(10110);
    		result.setMessage("图片id为空");
    		return result;
    	}
    	result.setCode(200);
    	result.setMessage("获取图片成功");
    	result.setResult(advertPicService.getPicById(id));
    	return result;
    }
    @RequestMapping("addAdvertPic")
    @ResponseBody
    public BaseResult<Object> addAdvertPic(HttpServletRequest request){
    	BaseResult<Object> result=new BaseResult<>();
    	AdvertPic bean=new AdvertPic();
    	if(StringUtils.isEmpty(request.getParameter("advert_title"))){
    		result.setCode(110);
    		result.setMessage("图片标题不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("advert_link"))){
    		result.setCode(110);
    		result.setMessage("图片跳转链接不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("advert_message"))){
    		result.setCode(110);
    		result.setMessage("图片提示信息不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("advert_image"))){
    		result.setCode(110);
    		result.setMessage("图片信息不能为空");
    		return result;
    	}
    	bean.setATitle(request.getParameter("advert_title"));
    	bean.setALink(request.getParameter("advert_link"));
    	bean.setAImage(request.getParameter("advert_image"));
    	bean.setAMessage(request.getParameter("advert_message"));
    	advertPicService.create(bean);
    	result.setCode(200);
    	result.setMessage("添加轮播图成功");
    	return result;
    }
    @RequestMapping("delPicById")
    @ResponseBody
    public BaseResult<Object> delPicById(HttpServletRequest request) {
    	BaseResult<Object> result=new BaseResult<>();
    	if(StringUtils.isEmpty(request.getParameter("advert_id"))){
    		result.setCode(10110);
    		result.setMessage("图片id不能为空");
    		return result;
    	}
    	advertPicService.delPicById(request.getParameter("advert_id"));
    	result.setCode(200);
    	result.setMessage("删除图片成功");
    	return result;
    }
    @RequestMapping("updatePicInfo")
    @ResponseBody
    public BaseResult<Object> updatePicInfo(HttpServletRequest request) {
    	BaseResult<Object> result=new BaseResult<>();
    	AdvertPic bean=new AdvertPic();
    	if(StringUtils.isEmpty(request.getParameter("id"))){
    		result.setCode(110);
    		result.setMessage("图片id不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("title"))){
    		result.setCode(110);
    		result.setMessage("图片标题不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("link"))){
    		result.setCode(110);
    		result.setMessage("图片跳转链接不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("message"))){
    		result.setCode(110);
    		result.setMessage("图片提示信息不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("image"))){
    		result.setCode(110);
    		result.setMessage("图片信息不能为空");
    		return result;
    	}
    	bean.setATitle(request.getParameter("title"));
    	bean.setALink(request.getParameter("link"));
    	bean.setAImage(request.getParameter("image"));
    	bean.setAMessage(request.getParameter("message"));
    	advertPicService.updatePicInfo(bean);
    	result.setCode(200);
    	result.setMessage("图片修改成功");
    	return result;
    }
}
