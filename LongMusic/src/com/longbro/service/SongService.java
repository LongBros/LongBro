package com.longbro.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Song;

public interface SongService {
	public void addSong(Map<Object,Object> map);
	public Song querySongById(int id);
	public Song querySongBySId(String sourceId);
	public List<Song> querySongs(String key);
	public List<Song> queryAllSongs(int page);
	public Song querySongBySName(String key);
	public List<Song> querySongsBySinger(String singer);
	public List<Song> querySongsByLyric(String key);
	public void editSong(Song song);
	
	public List<Song> queryHotSongs(int num);
	public Integer findSongNumBy(String inputTime);//2019-09-20
	public ArrayList<String> queryAllSinger();//2019-09-28
	public List<Song> strongQuerySongs(String key);//12-31

}
