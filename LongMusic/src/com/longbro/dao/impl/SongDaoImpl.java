package com.longbro.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.longbro.bean.Song;
import com.longbro.common.BaseDao;
import com.longbro.dao.SongDao;
import com.longbro.util.LogUtil;
@Repository
public class SongDaoImpl extends BaseDao implements SongDao{
	
	@Override
	public void addSong(Map<Object, Object> map) {
		this.insert("com.longbro.bean.song.addSong", map);
	}
	//根据关键字查询歌曲
	@Override
	public List<Song> querySongs(String key) {
		// TODO Auto-generated method stub
		List<Song> list=(List<Song>)this.selectList("com.longbro.bean.song.querySongs",key);
		return list;
	}
	/**
	 * start  第一首歌
	 */
	@Override
	public List<Song> queryAllSongs(int start) {
		// TODO Auto-generated method stub
		LogUtil.print(start+"");
		return (List<Song>)this.selectList("com.longbro.bean.song.queryAllSongs",start);
	}
	@Override
	public Song querySongById(int id) {
		// TODO Auto-generated method stub
		return (Song)this.selectOne("com.longbro.bean.song.querySongById", id);
	}
	@Override
	public Song querySongBySId(String sourceId) {
		// TODO Auto-generated method stub
		return (Song)this.selectOne("com.longbro.bean.song.querySongBySId", sourceId);
	}
	@Override
	public Song querySongBySName(String sname) {
		// TODO Auto-generated method stub
		Song song=(Song)this.selectOne("com.longbro.bean.song.querySongsBySName", sname);
		return song;
	}
	@Override
	public List<Song> querySongsBySinger(String singer) {
		// TODO Auto-generated method stub
		List<Song> list=(List<Song>)this.selectList("com.longbro.bean.song.querySongsBySinger",singer);
		return list;
	}
	
	@Override
	public void editSong(Song song) {
		// TODO Auto-generated method stub
		this.update("com.longbro.bean.song.editSong", song);
	}
}
