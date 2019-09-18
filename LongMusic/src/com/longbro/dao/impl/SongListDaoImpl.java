package com.longbro.dao.impl;

import java.util.HashMap;
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
	public void addSongList(SongList list) {
		// TODO Auto-generated method stub
		this.insert("com.longbro.bean.songlist.addSongList", list);
	}
	@Override
	public List<SongList> querySongList(String id,String creator) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);map.put("creator", creator);
		// TODO Auto-generated method stub
		return (List<SongList>)this.selectList("com.longbro.bean.songlist.querySongList",map);
	}
	@Override
	public void updateSongList(String songs, String id) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("songs", songs);
		map.put("id", id);
		// TODO Auto-generated method stub
		this.update("com.longbro.bean.songlist.updateSongList", map);
	}
}
