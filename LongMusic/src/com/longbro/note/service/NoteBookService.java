package com.longbro.note.service;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.longbro.note.bean.Diary;
import com.longbro.note.bean.NoteBook;
import com.longbro.note.dao.NoteBookDao;
/**
 * 描述：笔记本 DAO接口
 * 作者:longbro
 * 日期:2019-10-09 21:11:32
 * 版权：多啦学娱网络科技有限公司
 */
@Service
public class NoteBookService{
	@Autowired NoteBookDao dao;
	public void addNote(NoteBook nb) {
		// TODO Auto-generated method stub
		dao.addNote(nb);
	}
	public List<NoteBook> getDiaryByPage(int page) {
		// TODO Auto-generated method stub
		return dao.getDiaryByPage(page);
	}
	public NoteBook getDiaryById(int id) {
		// TODO Auto-generated method stub
		return dao.getDiaryById(id);
	}
	public int getDiaryNumBy(NoteBook nb) {
		// TODO Auto-generated method stub
		return dao.getDiaryNumBy(nb);
	}
	public List<Diary> getDiaryBy(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.getDiaryBy(map);
	}
	public List<HashMap<String,Object>> getBeforeAndNextId(int id,String author){
		return dao.getBeforeAndNextId(id, author);
	}
}
