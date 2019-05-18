package com.longbro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longbro.bean.Account;
import com.longbro.bean.User;
import com.longbro.dao.AccountDao;
import com.longbro.dao.LoginDao;
import com.longbro.service.AccountService;
import com.longbro.service.LoginService;
import com.longbro.vo.CateAmountVo;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired AccountDao dao;
	
	@Override
	public void addAccount(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		dao.addAccount(map);
	}
	public List<Account> queryAllBill(int page){
		return dao.queryAllBill(50*(page-1));
	}
	@Override
	public List<Account> queryAllBill1(String pageIndex, String pageSize,
			String sortField, String sortOrder,String payutil,String in_out,String category,String key) {
		// TODO Auto-generated method stub
		return dao.queryAllBill1(pageIndex, pageSize, sortField, sortOrder,payutil,in_out,category,key);
	}
	@Override
	public List<Account> queryBillBy(Account acc) {
		// TODO Auto-generated method stub
		return dao.queryBillBy(acc);
	}
	@Override
	public Account queryBillById(int id) {
		// TODO Auto-generated method stub
		return dao.queryBillById(id);
	}
	@Override
	public void updateBillById(Account acc) {
		// TODO Auto-generated method stub
		dao.updateBillById(acc);
	}
	@Override
	public String deleteAccById(int id) {
		// TODO Auto-generated method stub
		dao.deleteAccById(id);
		return null;
	}
	@Override
	public int queryNum(String key) {
		// TODO Auto-generated method stub
		return dao.queryNum(key);
	}
	@Override
	public ArrayList getAllMonth(String yom) {
		// TODO Auto-generated method stub
		return dao.getAllMonth(yom);
	}
	@Override
	public String getAmount(String yom, String ioo, String d) {
		// TODO Auto-generated method stub
		return dao.getAmount(yom, ioo, d);
	}
	/**
	 * 根据年(月)分、支出（收入）得到其下对应的分类、金额
	 * @desc 
	 * @author zcl
	 * @date 2019年5月18日
	 * @param yom
	 * @param ioo
	 * @param d
	 */
	@Override
	public List<CateAmountVo> getCateByYom(String yom, String ioo, String d){
		return dao.getCateByYom(yom, ioo, d);
	}
}
