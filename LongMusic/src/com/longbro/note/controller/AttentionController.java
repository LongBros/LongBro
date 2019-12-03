
package com.longbro.note.controller;

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

import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Attention;
import com.longbro.note.service.AttentionService;
import com.longbro.util.TimeUtil;
/**
 * 关注表(notice,attention,concern,watch)控制器
 * @author longbro
 * @date 2019-11-20 00:03:42
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/notice/")
public class AttentionController{
//	Logger log = Logger.getLogger(AttentionController.class);
    @Autowired
    AttentionService attentionService;
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
}
