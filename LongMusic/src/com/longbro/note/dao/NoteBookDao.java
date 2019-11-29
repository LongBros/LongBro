package com.longbro.note.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longbro.common.BaseDao;
import com.longbro.note.bean.Diary;
import com.longbro.note.bean.NoteBook;

import org.springframework.stereotype.Repository;
/**
 * 描述：笔记本 DAO接口
 * 作者:longbro
 * 日期:2019-10-09 21:11:32
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class NoteBookDao extends BaseDao{
	private String pre="com.longbro.note.bean.NoteBook.";
	public void addNote(NoteBook nb) {
		// TODO Auto-generated method stub
		this.insert(pre+"create", nb);
	}
	public List<NoteBook> getDiaryByPage(int page) {
		//每页十篇
		return (List<NoteBook>)this.selectList(pre+"getDiaryByPage", (page-1)*10);
	}
	public NoteBook getDiaryById(int id) {
		// TODO Auto-generated method stub
		return (NoteBook)this.selectOne(pre+"get", id);
	}
	public int getDiaryNumBy(NoteBook nb) {
		// TODO Auto-generated method stub
		return (Integer)this.selectOne(pre+"getDiaryNumBy", nb);
	}
	public List<Diary> getDiaryBy(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return (List<Diary>)this.selectList(pre+"getDiaryBy", map);
	}
	public List<HashMap<String,Object>> getBeforeAndNextId(int id,String author){
		Map<String,Object> map=new HashMap<>();
		map.put("author", author);
		map.put("id", id);
		return (List<HashMap<String,Object>>)this.selectList(pre+"getBeforeAndNextId", map);
	}
	//2019-11-24编辑日记
	public void editDiary(NoteBook nb){
		this.update(pre+"update", nb);
	}
}

