package com.longbro.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longbro.bean.Song;
import com.longbro.bean.SongList;
import com.longbro.dao.SongDao;
import com.longbro.dao.SongListDao;
import com.longbro.service.SongListService;
import com.longbro.service.SongService;
@Service
public class SongListServiceImpl implements SongListService{
	@Autowired SongListDao dao;
	@Override
	public void addSongList(SongList list) {
		// TODO Auto-generated method stub
		dao.addSongList(list);
	}
	@Override
	public List<SongList> querySongList(String id) {
		// TODO Auto-generated method stub
		return dao.querySongList(id);
	}
	
	@Override
	public void updateSongList(String songs, String id) {
		// TODO Auto-generated method stub
		dao.updateSongList(songs, id);
	}
}
