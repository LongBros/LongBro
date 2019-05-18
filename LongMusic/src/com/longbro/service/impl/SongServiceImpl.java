package com.longbro.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longbro.bean.Song;
import com.longbro.dao.SongDao;
import com.longbro.service.SongService;
@Service
public class SongServiceImpl implements SongService{
	@Autowired SongDao dao;
	@Override
	public void addSong(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		dao.addSong(map);
	}
	@Override
	public List<Song> querySongs(String key) {
		// TODO Auto-generated method stub
		return dao.querySongs(key);
	}
	@Override
	public Song querySongById(int id) {
		// TODO Auto-generated method stub
		return dao.querySongById(id);
	}
	@Override
	public Song querySongBySId(String sourceId) {
		// TODO Auto-generated method stub
		return dao.querySongBySId(sourceId);
	}
	@Override
	public List<Song> queryAllSongs(int page) {
		// TODO Auto-generated method stub
		return dao.queryAllSongs(50*(page-1));
	}
	@Override
	public Song querySongBySName(String key) {
		// TODO Auto-generated method stub
		return dao.querySongBySName(key);
	}
	@Override
	public List<Song> querySongsBySinger(String singer) {
		// TODO Auto-generated method stub
		return dao.querySongsBySinger(singer);
	}
	
	@Override
	public void editSong(Song song) {
		// TODO Auto-generated method stub
		dao.editSong(song);
	}
}
