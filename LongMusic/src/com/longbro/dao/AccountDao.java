package com.longbro.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Account;
import com.longbro.bean.Song;
import com.longbro.bean.User;
import com.longbro.vo.CateAmountVo;

public interface AccountDao {
	public void addAccount(Map<Object, Object> map);
	public List<Account> queryAllBill(int start) ;
	public List<Account> queryAllBill1(String pageIndex,String pageSize,String sortField,String sortOrder,HashMap<String, String> map) ;
	public List<Account> queryBillBy(Account acc) ;
	public Account queryBillById(int id);
	public void updateBillById(Account acc);
	public String deleteAccById(int id);
	
	public int queryNum(String key);
	public ArrayList getAllMonth(String yom);
	public String getAmount(String yom,String ioo,String d);
	public List<CateAmountVo> getCateByYom(String yom, String ioo, String d);
	public List<Account> queryBillsBy(HashMap map);
}
