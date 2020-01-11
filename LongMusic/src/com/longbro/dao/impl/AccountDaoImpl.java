package com.longbro.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longbro.bean.Account;
import com.longbro.bean.Song;
import com.longbro.bean.User;
import com.longbro.common.BaseDao;
import com.longbro.dao.AccountDao;
import com.longbro.dao.LoginDao;
import com.longbro.util.LogUtil;
import com.longbro.vo.CateAmountVo;

@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao{
	@Override
	public void addAccount(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		this.insert("com.longbro.bean.account.addAccount", map);
	}
	@Override
	public List<Account> queryAllBill(int start) {
		// TODO Auto-generated method stub
//		LogUtil.print("所加载的第一个的序号为："+start);
		return (List<Account>)this.selectList("com.longbro.bean.account.queryAllBill",start);
	}
	@Override
	public List<Account> queryAllBill1(String pageIndex, String pageSize,
			String sortField, String sortOrder,HashMap<String, String> map) {
		map.put("pageIndex", pageIndex);map.put("pageSize", pageSize);
		map.put("sortField", sortField);map.put("sortOrder", sortOrder);
		return (List<Account>)this.selectList("com.longbro.bean.account.queryAllBill1",map);
	}
	@Override
	public List<Account> queryBillBy(Account acc) {
		// TODO Auto-generated method stub
		return (List<Account>)this.selectList("com.longbro.bean.account.queryBillBy",acc);
	}
	@Override
	public Account queryBillById(int id) {
		// TODO Auto-generated method stub
		return (Account)this.selectOne("com.longbro.bean.account.queryBillById",id);
	}
	@Override
	public String deleteAccById(int id){
		this.delete("com.longbro.bean.account.deleteAccById", id);
		return "删除成功";
	}
	@Override
	public void updateBillById(Account acc) {
		// TODO Auto-generated method stub
		this.update("com.longbro.bean.account.updateBillById", acc);
	}
	@Override
	public int queryNum(String key) {
		System.out.println(key);
		return (Integer)this.selectOne("com.longbro.bean.account.queryNum", key);
	}
	@Override
	public ArrayList getAllMonth(@Param("yom") String yom) {
		return (ArrayList)this.selectList("com.longbro.bean.account.getAllMonth",yom);
	}
	/**
	 * 时间类型，支出/收入，时间
	 */
	@Override
	public String getAmount(String yom, String ioo, String d) {
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("yom", yom);map.put("ioo", ioo);
		map.put("d", d);
		return (String)this.selectOne("com.longbro.bean.account.getAmount", map);
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
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("yom", yom);map.put("ioo", ioo);
		map.put("d", d);
		return (List<CateAmountVo>)this.selectList("com.longbro.bean.account.getCate", map);
	}
}
