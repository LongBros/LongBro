
package com.longbro.note.controller;

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
import com.longbro.note.bean.Call;
import com.longbro.note.service.CallService;
import com.longbro.util.TimeUtil;
/**
 * 用户之间的@记录表控制器
 * @author longbro
 * @date 2019-12-31 23:03:47
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/call/")
public class CallController{
    @Autowired
    CallService callService;
    /**
     * 1.@关注的人
     * @author zcl
     * @date 2020年1月1日
     * @param friend
     */
    @RequestMapping("callFriend")
    @ResponseBody
    public BaseResult<Object> atFriend(Call friend){
    	BaseResult<Object> bs=new BaseResult<>();
    	String ated=friend.getAAtedUser();
    	String ateds[]=ated.split("、");
    	friend.setAAtTime(TimeUtil.time());
    	for(String at:ateds){
    		friend.setAAtedUser(at);
    		callService.create(friend);
    	}
    	bs.setCode(200);
    	bs.setMessage("@成功！");
    	bs.setResult(null);
    	
    	return bs;
    }
}
