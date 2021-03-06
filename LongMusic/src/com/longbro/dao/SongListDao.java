package com.longbro.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Song;
import com.longbro.bean.SongList;

public interface SongListDao {
	public void addSongList(SongList list);
	
	public List<SongList> querySongList(String id,String creator);

	public void updateSongList(String songs,String id);
}
