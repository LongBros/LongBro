package com.longbro.note.service;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.longbro.note.bean.StoreDiary;
import com.longbro.note.dao.StoreDiaryDao;
/**
 * 
 * <pre> 
 * 描述：d_store DAO接口
 * 作者:longbro
 * 日期:2019-10-22 20:28:10
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class StoreDiaryService{
	@Autowired StoreDiaryDao dao;
	public void create(StoreDiary diary) {
		// TODO Auto-generated method stub
		dao.create(diary);
	}
	public void remove(StoreDiary diary) {
		// TODO Auto-generated method stub
		dao.remove(diary);
	}
	public StoreDiary get(StoreDiary diary) {
		// TODO Auto-generated method stub
		return dao.get(diary);
	}
}

