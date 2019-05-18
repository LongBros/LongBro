package com.longbro.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longbro.bean.Result;
import com.longbro.bean.User;
import com.longbro.service.LoginService;

@Controller
public class LoginController {
	@Autowired LoginService service;
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Result<User> login(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String phone=request.getParameter("account");
		String password=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		String code=request.getSession().getAttribute("validatecode").toString();
		
		Map<Object, Object> map=new HashMap<Object, Object>();
		Result<User> result=new Result<User>();
		if(code.equals(vcode)){
			//存入map
			map.put("account", phone);
			map.put("password", password);
			User user=service.queryUser(map);
			if (user==null) {
				result.setData(user);
				result.setMsg("未匹配到信息");
				result.setResult(false);
			}else {
				System.out.println("登录成功");
				result.setData(user);
				result.setMsg("登录成功");
				result.setResult(true);
				response.sendRedirect("/LongMusic");
			}
		}else{
			result.setData(null);
			result.setMsg("验证码错误");
			result.setResult(false);
		}
		return result;
	}
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public Result<User> register(HttpServletRequest request,HttpServletResponse response)
	{
		String phone=request.getParameter("account");
		String password=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		String code=request.getSession().getAttribute("validatecode").toString();
		
		//账号密码不为空，且验证码输入正确
		Result<User> result=new Result<User>();
		if(!phone.isEmpty()&&!password.isEmpty()&&code.equals(vcode)){
			Map<Object, Object> map=new HashMap<Object, Object>();
			map.put("account", phone);
			map.put("password", password);
			service.addUser(map);
			result.setData(null);
			result.setMsg("注册成功");
			result.setResult(true);
			return result;
		}
		result.setData(null);
		result.setMsg("账号或密码为空，或验证码输入不正确");
		result.setResult(false);
		return result;
		
	}
	@RequestMapping(value="resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public Result<User> resetPassword(HttpServletRequest request,HttpServletResponse response)
	{
		String phone=request.getParameter("account");
		String password=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		String code=request.getSession().getAttribute("validatecode").toString();
		
		Result<User> result=new Result<User>();
		if(!phone.isEmpty()&&!password.isEmpty()&&code.equals(vcode)){
			Map<Object, Object> map=new HashMap<Object, Object>();
			map.put("account", phone);
			map.put("password", password);
			service.resetPassword(map);
			result.setData(null);
			result.setMsg("修改密码成功");
			result.setResult(true);
		}
		result.setData(null);
		result.setMsg("密码修改失败");
		result.setResult(false);
		return result;
	}

}
