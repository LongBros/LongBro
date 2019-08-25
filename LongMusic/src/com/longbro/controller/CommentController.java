package com.longbro.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longbro.bean.Comment;
import com.longbro.service.CommentService;
import com.longbro.util.TimeUtil;
@Controller
public class CommentController {
	@Autowired CommentService service;
	@RequestMapping ("addComment")
	public void addComment(HttpServletRequest request){
		Map<String, String> map=new HashMap<String, String>();
		map.put("c_Type", request.getParameter("c_Type"));
		map.put("c_Reviewed", request.getParameter("c_Reviewed"));
		map.put("c_Content", request.getParameter("c_Content"));
		map.put("c_Time", TimeUtil.time());
		map.put("c_Reviewer", "LongBro");
		map.put("c_Ip", "521.521.521.521");
		map.put("c_Address", "河南省邓州市");
		service.addComment(map);
	}
	@RequestMapping("queryComment")
	@ResponseBody
	public List<Comment> queryComment(HttpServletRequest request){
		int c_Type=-1;
		String c_Reviewed="";
		if(StringUtils.isNotEmpty(request.getParameter("c_Type"))){
			c_Type=Integer.parseInt(request.getParameter("c_Type"));
		}
		if(StringUtils.isNotEmpty(request.getParameter("c_Reviewed"))){
			c_Reviewed=request.getParameter("c_Reviewed");
		}
		List<Comment> comments=service.queryComments(c_Type,c_Reviewed);
		
		return comments;
	}
}
