package com.longbro.note.dao;

import java.util.List;
import com.longbro.note.bean.CommentDiary;
import com.longbro.note.dao.CommentDiaryDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：日记评论表 
 * 作者:longbro
 * 日期:2019-10-26 07:50:14
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class CommentDiaryDao extends BaseDao{

	public String getNamespace() {
		return CommentDiary.class.getName();
	}
	public void create(CommentDiary bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
}

