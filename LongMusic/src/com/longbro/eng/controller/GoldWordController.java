
package com.longbro.eng.controller;

import java.util.HashMap;
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

import com.longbro.common.BaseResult;
import com.longbro.eng.bean.GoldWord;
import com.longbro.eng.service.GoldWordService;
/**
 * goldword控制器
 * @author longbro
 * @date 2019-11-09 21:27:43
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("eng/")
public class GoldWordController{
    @Autowired
    GoldWordService goldWordService;
    
    @RequestMapping("getWordById")
    @ResponseBody
    public BaseResult<GoldWord> getWordById(String id){
    	BaseResult<GoldWord> result=new BaseResult<>();
    	if(StringUtils.isEmpty(id)){
    		result.setCode(10110);
    		result.setMessage("id为空");
    		return result;
    	}
    	result.setCode(200);
    	result.setMessage("获取单词成功");
    	result.setResult(goldWordService.getWordById(id));
    	return result;
    }
    @RequestMapping("addWord")
    @ResponseBody
    public BaseResult<Object> addWord(HttpServletRequest request){
    	BaseResult<Object> result=new BaseResult<>();
    	GoldWord bean=new GoldWord();
    	if(StringUtils.isEmpty(request.getParameter("chinese"))){
    		result.setCode(110);
    		result.setMessage("不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("english"))){
    		result.setCode(110);
    		result.setMessage("不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("voiceUrl"))){
    		result.setCode(110);
    		result.setMessage("不能为空");
    		return result;
    	}
    	if(StringUtils.isEmpty(request.getParameter("pronounce"))){
    		result.setCode(110);
    		result.setMessage("不能为空");
    		return result;
    	}
    	bean.setChinese(request.getParameter("chinese"));
    	bean.setEnglish(request.getParameter("english"));
    	bean.setPronounce(request.getParameter("pronounce"));
    	bean.setVoiceUrl(request.getParameter("voiceUrl"));
    	goldWordService.create(bean);
    	result.setCode(200);
    	result.setMessage("添加成功");
    	return result;
    }
    @RequestMapping("delWordById")
    @ResponseBody
    public BaseResult<Object> delWordById(HttpServletRequest request) {
    	BaseResult<Object> result=new BaseResult<>();
    	if(StringUtils.isEmpty(request.getParameter("id"))){
    		result.setCode(10110);
    		result.setMessage("id不能为空");
    		return result;
    	}
    	goldWordService.delWordById(request.getParameter("id"));
    	result.setCode(200);
    	result.setMessage("删除成功");
    	return result;
    }
    @RequestMapping("updateWordInfo")
    @ResponseBody
    public BaseResult<Object> updateWordInfo(GoldWord bean) {
    	BaseResult<Object> result=new BaseResult<>();
    	goldWordService.updateWordInfo(bean);
    	result.setCode(200);
    	result.setMessage("修改成功");
    	return result;
    }
    /**
     * 
     * @desc 分页查询单词
     * @Author dora
     * @time 2020年6月26日 下午1:58:09
     * @param req
     * @return
     */
    @RequestMapping("getWordsBy")
    @ResponseBody
    public BaseResult<List<GoldWord>> getWordsBy(HttpServletRequest req){
    	BaseResult<List<GoldWord>> result=new BaseResult<>();
    	String page=req.getParameter("page");
    	String perPage=req.getParameter("perPage");
    	HashMap<String, String> map=new HashMap<>();
    	int per=10;
    	if(StringUtils.isNotEmpty(perPage))
    	{
    		map.put("perPage", perPage);
    		per=Integer.parseInt(perPage);
    	}else{
    		map.put("perPage", "10");
    	}
    	if(StringUtils.isNotEmpty(page))
    		map.put("page", (Integer.parseInt(page)-1)*per+"");
    	else
    		map.put("page", "0");
    	result.setCode(200);
    	result.setMessage("获取单词成功");
    	result.setResult(goldWordService.getWordsBy(map));
    	return result;
    }
}
