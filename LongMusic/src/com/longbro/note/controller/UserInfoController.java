package com.longbro.note.controller;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.longbro.bean.AlarmUser;
import com.longbro.house.bean.BaseResult;
import com.longbro.note.bean.Author;
import com.longbro.note.bean.UserInfo;
import com.longbro.note.service.CommentDiaryService;
import com.longbro.note.service.PraiseDiaryService;
import com.longbro.note.service.StoreDiaryService;
import com.longbro.note.service.UserInfoService;
import com.longbro.service.CommentService;
import com.longbro.util.Strings;
import com.longbro.util.TimeUtil;
/**
 * d_user_info控制器
 * @author longbro
 * @date 2019-10-26 13:07:00
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
@RequestMapping("/note/userinfo/")
public class UserInfoController{
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    CommentDiaryService commentDiaryService;
    @Autowired
    PraiseDiaryService praiseDiaryService;
    @Autowired
    StoreDiaryService storeDiaryService;
    /**
     * @desc 1.根据userId获取用户信息
     * @author zcl
     * @date 2019年10月26日
     * @param UUserId
     * @return
     */
    @RequestMapping("getAuthorInfoByUserId")
    @ResponseBody
    public Author getAuthorInfoByUserId(int UUserId){
    	return userInfoService.get(UUserId);
    }
    /**
     * 2.注册用户
     * 先判断数据库是否已有该userId，再插入不同的userId
     * @desc 
     * @author zcl
     * @date 2019年10月1日
     * @return
     */
    @RequestMapping(value="genNoteUserId",method=RequestMethod.GET)
    @ResponseBody
    public String genUserId(String source){
    	String userId=Strings.allotNum(8)+"";//随机生成userId
    	UserInfo ui=userInfoService.get(Integer.parseInt(userId));
    	if(ui!=null){//该用户id已存在，需重新生成
    		genUserId(source);
    	}
    	ui=new UserInfo();
    	ui.setUUserId(Integer.parseInt(userId));
    	ui.setUJoinTime(TimeUtil.time());
//    	au.setSource(source);
    	userInfoService.create(ui);
    	return userId;
    }
    /**
     * @desc 3.登录哆啦日记网的校验
     * @author zcl
     * @date 2019年10月27日
     * @param acc
     * @param pass
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="loginNote",method=RequestMethod.POST)
    @ResponseBody
    public BaseResult<String> loginNote(String acc,String pass,HttpServletResponse response) throws UnsupportedEncodingException{
    	BaseResult<String> result=new BaseResult<String>();
    	UserInfo ui=userInfoService.loginNote(acc, pass);
    	if(ui!=null){
    		Cookie cookie=new Cookie("userId", acc);
    		cookie.setMaxAge(30*24*60*60);
    		cookie.setPath("/");
    		response.addCookie(cookie);
    		
    		String uName=ui.getUUserName();
    		uName=URLEncoder.encode(uName, "utf-8");
    		Cookie cookie1=new Cookie("userNick", uName);
    		cookie1.setMaxAge(30*24*60*60);
    		cookie1.setPath("/");
    		response.addCookie(cookie1);
    		
    		String uLocation=ui.getLocation();
    		uLocation=URLEncoder.encode(uLocation, "utf-8");
    		Cookie cookie2=new Cookie("userAddr", uLocation);
    		cookie2.setMaxAge(30*24*60*60);
    		cookie2.setPath("/");
    		response.addCookie(cookie2);
    		System.out.println(cookie.getValue());
    		result.setCode(200);
    		result.setMessage("登录成功");
    		result.setResult(1);
    		return result;
    	}
    	result.setCode(110);
    	result.setMessage("请检查你输入的账号");
    	result.setResult(null);
    	return result;
    }
    /**
     * @desc 4.查询某用户或所有用户的未读数量
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     */
    @RequestMapping("queryUnReadNum")
    @ResponseBody
    public BaseResult<String> queryUnReadNum(String userId){
    	BaseResult<String> result=new BaseResult<String>();
    	List<HashMap<String, Object>> map=new ArrayList<HashMap<String, Object>>();
    	result.setCode(200);
    	result.setResult(userInfoService.queryUnReadNum(userId));
    	result.setMessage("查询成功");
    	return result;
    }
    /**
     * @desc 5.设置所有未读消息为已读
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     * @return
     */
    @RequestMapping("setAsReaded")
    @ResponseBody
    public BaseResult<String> setAsReaded(String userId){
    	BaseResult<String> result=new BaseResult<String>();
    	commentDiaryService.setAsReaded(userId);
    	praiseDiaryService.setAsReaded(userId);
    	storeDiaryService.setAsReaded(userId);
    	result.setCode(200);
    	result.setMessage("已设置所有未读消息为已读");
    	result.setResult(null);
    	return result;
    }
    /**
     * @desc 6.加载某用户或所有用户的互动信息：
     * 赞、评论、收藏、关注,被赞、被评论、被收藏、被关注
     * @author zcl
     * @date 2019年11月16日
     * @param userId
     * @return
     */
    @RequestMapping("queryInteractNum")
    @ResponseBody
	public BaseResult<List<HashMap<String, Object>>> queryInteractNum(String userId){
		BaseResult<List<HashMap<String, Object>>> result=new BaseResult<List<HashMap<String, Object>>>();
		result.setCode(200);
    	result.setMessage("已加载完毕");
    	result.setResult(userInfoService.queryInteractNum(userId));
    	
    	return result;
	}
    /**
     * @desc 7.修改用户的昵称、签名、位置等信息
     * @author zcl
     * @date 2019年12月1日
     * @param info
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public BaseResult<List<HashMap<String, Object>>> updateUserInfo(UserInfo info){
		BaseResult<List<HashMap<String, Object>>> result=new BaseResult<List<HashMap<String, Object>>>();
		userInfoService.updateUserInfo(info);
		result.setCode(200);
    	result.setMessage("信息保存成功");
    	result.setResult("");
    	
    	return result;
	}
    /**
     * @desc 上传头像
     * @author zcl
     * @date 2019年12月2日
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="uploadHeadImage",method=RequestMethod.POST)
    @ResponseBody
    public void uploadHeadImage(HttpServletRequest request) throws Exception{
		 String pname="";
		 String userId="";
		 System.out.println(request.getParameter("userId"));
    	//创建 FileItem 对象的工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//ServletFileUpload 负责处理上传的文件数据，并将表单中每个输入项封装成一个 FileItem 对象中
    	ServletFileUpload upload=new ServletFileUpload(factory);//2、创建一个文件上传解析器
		upload.setHeaderEncoding("utf-8");////解决上传文件名的中文乱码
		List<FileItem> items=upload.parseRequest(request);
		System.out.println(new Gson().toJson(items));
		Iterator<FileItem> ite=items.iterator();
		while(ite.hasNext()){
			FileItem fi=(FileItem)ite.next();
			if(fi.isFormField()){//为普通表单字段，则调用getFieldName、getString方法得到字段名和字段值
				if(fi.getFieldName().equals("userId")){
					userId=fi.getString("userId");
				}
			}else{//为上传文件，则调用getInputStream方法得到数据输入流，从而读取上传数据。编码实现文件上传
				if(fi.getName()!=null&&!fi.getName().equals("")){
					  long fname=System.currentTimeMillis();
					  pname=fname+".jpg";
					  File file=new File("D:/apache-tomcat-8.5.35/webapps/",pname);
					  //法一：使用fileitem直接向服务器写数据
					  fi.write(file);
				}else{
				}
			}
		}
		
		UserInfo user=new UserInfo();
		user.setHeadImage(pname);
		user.setUUserId(Integer.parseInt(userId));
		updateUserInfo(user);
    }
}
