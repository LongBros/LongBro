package com.longbro.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Song;
import com.longbro.bean.SongList;

public interface SongListService {
	public void addSongList(Map<Object, Object> map);
	
	public List<SongList> querySongList(String id);

	public void updateSongList(String songs,String id);
}
