
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
    @Autowired
    AttentionService attentionService;
    /**
     * @desc 关注作者
     * @author zcl
     * @date 2019年11月20日
     * @param att
     */
    @RequestMapping(value="noticeAuthor",method=RequestMethod.GET)
    @ResponseBody
    public void noticeAuthor(Attention att){
    	att.setNNoticeTime(TimeUtil.time());
    	att.setNReadStatus(0);
    	attentionService.create(att);
    }
}
