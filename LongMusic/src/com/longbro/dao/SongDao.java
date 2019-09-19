package com.longbro.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Song;

public interface SongDao {
	public void addSong(Map<Object, Object> map);
	
	public List<Song> querySongs(String key);
	
	public List<Song> queryAllSongs(int page);
	public Song querySongById(int id);
	public Song querySongBySId(String sourceId);
	public Song querySongBySName(String key);
	public List<Song> querySongsBySinger(String singer);
	public List<Song> querySongsByLyric(String key);
	public void editSong(Song song);
	
	public List<Song> queryHotSongs(int num);//2019-08-25
}
