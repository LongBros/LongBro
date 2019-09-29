package com.longbro.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.longbro.bean.Song;
import com.longbro.common.BaseDao;
import com.longbro.dao.SongDao;
import com.longbro.util.LogUtil;
@Repository
public class SongDaoImpl extends BaseDao implements SongDao{
	final String pre="com.longbro.bean.song.";
	@Override
	public void addSong(Map<Object, Object> map) {
		this.insert(pre+"addSong", map);
	}
	//根据关键字查询歌曲
	@Override
	public List<Song> querySongs(String key) {
		// TODO Auto-generated method stub
		List<Song> list=(List<Song>)this.selectList(pre+"querySongs",key);
		return list;
	}
	/**
	 * start  第一首歌
	 */
	@Override
	public List<Song> queryAllSongs(int start) {
		// TODO Auto-generated method stub
		LogUtil.print(start+"");
		return (List<Song>)this.selectList(pre+"queryAllSongs",start);
	}
	@Override
	public Song querySongById(int id) {
		// TODO Auto-generated method stub
		return (Song)this.selectOne(pre+"querySongById", id);
	}
	@Override
	public Song querySongBySId(String sourceId) {
		// TODO Auto-generated method stub
		return (Song)this.selectOne(pre+"querySongBySId", sourceId);
	}
	@Override
	public Song querySongBySName(String sname) {
		// TODO Auto-generated method stub
		Song song=(Song)this.selectOne(pre+"querySongsBySName", sname);
		return song;
	}
	@Override
	public List<Song> querySongsBySinger(String singer) {
		// TODO Auto-generated method stub
		List<Song> list=(List<Song>)this.selectList(pre+"querySongsBySinger",singer);
		return list;
	}
	@Override
	public List<Song> querySongsByLyric(String key) {
		List<Song> list=(List<Song>)this.selectList(pre+"querySongsByLyric",key);
		// TODO Auto-generated method stub
		return list;
	}
	@Override
	public void editSong(Song song) {
		// TODO Auto-generated method stub
		this.update(pre+"editSong", song);
	}
	@Override
	public List<Song> queryHotSongs(int num) {
		// TODO Auto-generated method stub
		List<Song> list=(List<Song>)this.selectList(pre+"querySongsBy",num);
		return list;
	}
	@Override
	public Integer findSongNumBy(String inputTime) {
		// TODO Auto-generated method stub
		return (Integer)this.selectOne(pre+"findSongNumBy", inputTime);
	}
	@Override
	public ArrayList<String> queryAllSinger() {
		// TODO Auto-generated method stub
		return (ArrayList<String>)this.selectList(pre+"queryAllSinger");
	}
}
