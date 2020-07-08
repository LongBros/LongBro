
package com.longbro.note.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.longbro.common.BaseResult;
import com.longbro.note.bean.Attention;
import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.service.AttentionService;
import com.longbro.util.TimeUtil;

import common.Logger;
/**
 * 关注表(notice,attention,concern,watch)控制器
 * @author longbro
 * @date 2019-11-20 00:03:42
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/notice/")
public class AttentionController{
    @Autowired
    AttentionService attentionService;
	private Logger logger= Logger.getLogger(AttentionController.class);

    /**
     * @desc 1.关注作者
     * @author zcl
     * @date 2019年11月20日
     * @param att
     */
    @RequestMapping(value="noticeAuthor",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult noticeAuthor(Attention att){
//    	log.info("开始关注作者");
    	BaseResult bs=new BaseResult<>();
    	List<Attention> atten=attentionService.whetherNotice(att);
    	if(StringUtils.isEmpty(att.getNNoticer())||atten.size()>0){
    		bs.setCode(110);
    		bs.setMessage("当前用户未登录或已经关注该作者");
    		return bs;
    	}
    	att.setNNoticeTime(TimeUtil.time());
    	att.setNReadStatus(0);
    	attentionService.create(att);
    	bs.setCode(200);
    	bs.setMessage("关注成功");
//    	log.info("关注作者成功");
    	return bs;
    }
    /**
     * @desc 2.判断当前登录用户是否已关注某作者
     * @author zcl
     * @date 2019年11月20日
     */
    @RequestMapping(value="whetherHasNotice",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult whetherNotice(Attention att){
//    	log.info("开始判断当前登录用户是否已关注作者");
    	BaseResult bs=new BaseResult<>();
    	List<Attention> atten=attentionService.whetherNotice(att);
    	//当前人未登录或登录未关注，显示“关注”按钮
    	if(atten.size()<1||StringUtils.isEmpty(att.getNNoticer())){
    		bs.setCode(110);
    		bs.setMessage("当前人未登录或登录未关注");
    		return bs;
    	}
    	bs.setCode(200);
		bs.setMessage("当前人已关注当前作者");
		return bs;
    }
    /**
     * @desc 3.取消关注
     * @author zcl
     * @date 2019年11月20日
     * @param att
     * @return
     */
    @RequestMapping(value="cancelAtten",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult cancelAtten(Attention att){
//    	log.info("开始取消关注作者");
    	BaseResult bs=new BaseResult<>();
    	if(StringUtils.isEmpty(att.getNNoticer())){
    		bs.setCode(110);
    		bs.setMessage("请先登录");
    		return bs;
    	}
    	
    	attentionService.cancelAtten(att);
    	bs.setCode(200);
    	bs.setMessage("取消关注成功");
    	return bs;
    }
    /**
     * 4.得到登录用户被关注的消息
     * @author LongBro
     * 2019年12月3日
     * 下午12:56:26
     * @param userId
     * @return
     */
    @RequestMapping(value="getMyMessage",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<HashMap<String, String>> getMyMessage(String userId,
    		String author,int page,int perPage){
    	BaseResult<HashMap<String, String>> result=new BaseResult<>();
    	int start=perPage*(page-1);
    	HashMap<String,Object> map=new HashMap<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	map.put("userId",userId);map.put("author",author);
    	map.put("start",start);map.put("perPage",perPage);
    	logger.debug("getMyMessage=========>"+new Gson().toJson(map));
    	result.setResult(attentionService.getMyMessage(map));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * @desc 5.查询出我关注的人
     * @author zcl
     * @date 2019年12月7日
     * @param userId
     * @return
     */
    @RequestMapping(value="getMyAtten",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<HashMap<String, Object>>> getMyAtten(String userId,
    		String author,int page,int perPage){
    	BaseResult<List<HashMap<String, Object>>> result=new BaseResult<>();
    	int start=perPage*(page-1);
    	HashMap<String,Object> map=new HashMap<>();
    	if(StringUtils.isEmpty(userId)){
    		result.setCode(110);
    		result.setMessage("用户id不能为空");
    		return result;
    	}
    	map.put("userId",userId);map.put("author",author);
    	map.put("start",start);map.put("perPage",perPage);
    	logger.debug("getMyAtten=========>"+new Gson().toJson(map));

    	result.setResult(attentionService.getMyAtten(map));
    	result.setCode(200);
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * @desc 6.查询关注的人的数量
     * @author zcl
     * @date 2020年1月30日
     * @param diary
     * @return
     */
    @RequestMapping(value="getAttenNum",method=RequestMethod.GET)
    @ResponseBody
    public int getAttenNum(Attention atten){
    	logger.debug("getAttenNum=========>"+new Gson().toJson(atten));
    	int num=attentionService.getAttenNum(atten);
    	return num;
    }
}
