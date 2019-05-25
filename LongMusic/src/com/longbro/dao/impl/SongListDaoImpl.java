package com.longbro.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.longbro.bean.Song;
import com.longbro.bean.SongList;
import com.longbro.common.BaseDao;
import com.longbro.dao.SongDao;
import com.longbro.dao.SongListDao;
import com.longbro.util.LogUtil;
@Repository
public class SongListDaoImpl extends BaseDao implements SongListDao{
	@Override
	public void addSongList(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		this.insert("com.longbro.bean.songlist.addSongList", map);
	}
	@Override
	public List<SongList> querySongList() {
		// TODO Auto-generated method stub
		return (List<SongList>)this.selectList("com.longbro.bean.songlist.querySongList");
	}
}
