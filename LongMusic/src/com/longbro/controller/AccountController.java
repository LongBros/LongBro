package com.longbro.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longbro.bean.Account;
import com.longbro.bean.Result;
import com.longbro.bean.User;
import com.longbro.service.AccountService;
import com.longbro.service.LoginService;
import com.longbro.util.DateUtil;
import com.longbro.vo.CateAmountVo;

import flexjson.JSONDeserializer;

@Controller
public class AccountController {
	@Autowired AccountService service;
	
	@RequestMapping(value="addBill",method=RequestMethod.GET)
	@ResponseBody
	public void addBill(HttpServletRequest request,HttpServletResponse response)
	{
		String time=request.getParameter("time");
		if(time.contains("中国")){//时间需转换
			time=DateUtil.formatDate(time);
		}
		String payutil=request.getParameter("payutil");
		String in_out=request.getParameter("in_out");
		String category=request.getParameter("category");
		String amount=request.getParameter("amount");
		String remark=request.getParameter("remark");
		String picture=request.getParameter("picture");

		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("time", time);
		map.put("payutil", payutil);
		map.put("in_out", in_out);
		map.put("category", category);
		map.put("amount", amount);
		map.put("remark", remark);
		map.put("picture", picture);
		service.addAccount(map);
				
	}
	/**
	 * 根据分页查询所有账单
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryAllBill",method=RequestMethod.POST)
	@ResponseBody
	public List<Account> queryAllBill(HttpServletRequest request,HttpServletResponse response){
		List<Account> list=service.queryAllBill(Integer.parseInt(request.getParameter("page")));
		return list;
	}
	/**
	 * miniui根据分页、备注、排序查询账单
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryAllBill1",method=RequestMethod.POST)
	@ResponseBody
	public HashMap queryAllBill1(HttpServletRequest request,HttpServletResponse response){
		
		String payutil=request.getParameter("payutil");
		if(StringUtils.isEmpty(payutil))
			payutil=null;
		String category=request.getParameter("category");
		if(StringUtils.isEmpty(category))
			category=null;
		String in_out=request.getParameter("in_out");
		if(StringUtils.isEmpty(in_out))
			in_out=null;
		String key=request.getParameter("key");
		if(StringUtils.isEmpty(key)){
			key="";
		}
		String pageIndex=request.getParameter("pageIndex");
		if(StringUtils.isEmpty(pageIndex)){
			pageIndex="0";
		}
		String pageSize=request.getParameter("pageSize");
		String sortField=request.getParameter("sortField");
		String sortOrder=request.getParameter("sortOrder");
		System.out.println("页码："+pageIndex);System.out.println("总页数："+pageSize);
		System.out.println("排序:"+sortField);System.out.println("升/序:"+sortOrder);
		HashMap map=new HashMap();
		List<Account> list=service.queryAllBill1(pageIndex,pageSize,sortField,sortOrder,payutil,in_out,category,key);
		map.put("data", list);
		int num=service.queryNum(key);
		map.put("total", num);
		return map;
	}
	/**
	 * @desc强大的搜索功能 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryBillBy",method=RequestMethod.GET)
	@ResponseBody
	public List<Account> queryBillBy(HttpServletRequest request,HttpServletResponse response){
		Account acc=new Account();
//		if(!StringUtils.isEmpty(request.getParameter("id"))){
//			acc.setId(Integer.parseInt(request.getParameter("id")));
//			System.out.println("账单id为"+request.getParameter("id"));
//		}
//		if(!StringUtils.isEmpty(request.getParameter("payutil"))){
//			acc.setPayutil(request.getParameter("payutil"));
//			System.out.println("账单工具为"+request.getParameter("payutil"));
//
//		}
//		if(!StringUtils.isEmpty(request.getParameter("category"))){
//			acc.setPayutil(request.getParameter("category"));
//			System.out.println("账单分类为"+request.getParameter("category"));
//		}
//		if(!StringUtils.isEmpty(request.getParameter("in_out"))){
//			acc.setPayutil(request.getParameter("in_out"));
//			System.out.println("账单为"+request.getParameter("in_out"));
//
//		}
//		if(!StringUtils.isEmpty(request.getParameter("key"))){
//			acc.setPayutil(request.getParameter("key"));
//			System.out.println("搜索关键字"+request.getParameter("key"));
//
//		}
//		if(!StringUtils.isEmpty(request.getParameter("time"))){
//			acc.setPayutil(request.getParameter("time"));
//			System.out.println("记账时间为"+request.getParameter("time"));
//		}
		if(!request.getParameter("payutil").equals(""))acc.setPayutil(request.getParameter("payutil"));
		if(!request.getParameter("category").equals(""))acc.setCate(request.getParameter("category"));
		if(!request.getParameter("in_out").equals(""))acc.setIn_out(request.getParameter("in_out"));
		if(!request.getParameter("key").equals(""))acc.setRemark(request.getParameter("key"));
		if(!request.getParameter("time").equals(""))acc.setTime(request.getParameter("time"));
		
		List<Account> list=service.queryBillBy(acc);
		return list;
	}
	/**
	 * @desc 根据id搜索账单，编辑账单时使用
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryBillById",method=RequestMethod.GET)
	@ResponseBody
	public Account queryBillById(HttpServletRequest request,HttpServletResponse response){
		
		Account acc=service.queryBillById(Integer.parseInt(request.getParameter("id")));
		return acc;
	}
	@RequestMapping(value="updateBillById",method=RequestMethod.POST)
	@ResponseBody
	public String updateBillById(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String data=request.getParameter("data");
		String id=data.substring(data.indexOf("id")+5, data.indexOf("payutil")-3);
		String payutil=data.substring(data.indexOf("payutil")+10, data.indexOf("in_out")-3);
		String in_out=data.substring(data.indexOf("in_out")+9, data.indexOf("cate")-3);
		String cate=data.substring(data.indexOf("cate")+7, data.indexOf("amount")-3);
		String amount=data.substring(data.indexOf("amount")+9, data.indexOf("remark")-3);
		String remark=data.substring(data.indexOf("remark")+9, data.indexOf("time")-3);
		String time=data.substring(data.indexOf("time")+7,data.length()-3);
		time=time.replace("T", " ");
		Account acc=new Account();
		acc.setId(id);
		acc.setPayutil(payutil);
		acc.setIn_out(in_out);
		acc.setCate(cate);
		acc.setAmount(Double.parseDouble(amount));
		acc.setRemark(remark);
		acc.setTime(time);
		service.updateBillById(acc);
//		JSONDeserializer decode=new JSONDeserializer();
//		decode.use(String.class, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		Account acc=(Account)decode.deserialize(data);
//		System.out.println(data);
//		System.out.println(time);
		return "修改成功";
	}
	/**
	 * 根据id删除账单
	 * @desc 
	 * @author zcl
	 * @date 2019年5月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteAccById",method=RequestMethod.GET)
	public String deleteAccById(HttpServletRequest request){
		if(request.getParameter("id").contains(",")){//选中多个时利用循环逐个删除
			String ids[]=request.getParameter("id").split(",");
			for(String id:ids){
				service.deleteAccById(Integer.parseInt(id));
			}
		}else{
			service.deleteAccById(Integer.parseInt(request.getParameter("id")));
		}
		
		return "删除成功";
	}
	//月分析和年分析
	@RequestMapping(value="getAmountByYoM",method=RequestMethod.GET)
	@ResponseBody	//此注解不加报404
	public ArrayList<HashMap> getAmountByYoM(HttpServletRequest request){
		ArrayList<HashMap> arr=new ArrayList<HashMap>();
		String yom=request.getParameter("yom");//值为year或month
		ArrayList<HashMap<String, String>> list=service.getAllMonth(yom);
//		System.out.println(list);
		for(HashMap<String, String> s:list){
			HashMap<String, String> map=new HashMap<String, String>();
			
			String in=service.getAmount(yom, "收入", s.get("time"));
			if(StringUtils.isEmpty(in)){
				in="0";
			}
			if(in.contains(".")&&(in.substring(in.indexOf(".")+1).length()>2)){//小数点后大于两位则保留两位
				in=in.substring(0,in.indexOf(".")+3);
			}
			String out =service.getAmount(yom, "支出", s.get("time"));
			if(StringUtils.isEmpty(out)){
				out="0";
			}
			if(out.contains(".")&&(out.substring(out.indexOf(".")+1).length()>2)){//小数点后大于两位
				out=out.substring(0,out.indexOf(".")+3);
			}
			map.put("yom", s.get("time"));//（年份）月份
			map.put("out",out );//总支出
			map.put("in_",in );//总收入
			String earn=(Double.parseDouble(in)-Double.parseDouble(out))+"";
			if(earn.contains(".")&&earn.substring(earn.indexOf(".")+1).length()>2){
				earn=earn.substring(0,earn.indexOf(".")+3);
			}
			map.put("earn", earn);//净收入
			
//			System.out.println(s.get("time")+"收入为"+in+"支出为"+out+"结余为"+earn);
			arr.add(map);
		}
		return arr;
	}
	@RequestMapping(value="getCateByYom",method=RequestMethod.GET)
	@ResponseBody	//此注解不加报404
	public List<CateAmountVo> getCateByYom(HttpServletRequest request){
		String yom=request.getParameter("yom");//值为year或month
		String ioo=request.getParameter("ioo");//值为year或month
		String d=request.getParameter("d");//值为year或month
		return service.getCateByYom(yom, ioo, d);
	}
}
