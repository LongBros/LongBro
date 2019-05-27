package com.longbro.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longbro.bean.Result;
import com.longbro.bean.Song;
import com.longbro.bean.SongList;
import com.longbro.service.SongListService;
import com.longbro.service.SongService;
import com.longbro.util.DownloadUtil;
/**
 * 歌单控制器
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年5月4日 上午3:24:57
 * @description
 * @version
 */
@Controller
public class SongListController {
	@Autowired SongListService service;
	/**
	 * 新建歌单
	 * @desc 
	 * @author zcl
	 * @date 2019年5月27日
	 * @param map
	 */
	@RequestMapping("addSongList")
	public void addSongList(Map<Object, Object> map){
		
	}
	/**
	 * 查询所有歌单
	 * @desc 
	 * @author zcl
	 * @date 2019年5月27日
	 * @return
	 */
	@RequestMapping(value="querySongList",method=RequestMethod.GET)
	@ResponseBody
	public List<SongList> querySongList(HttpServletRequest request){
		String id=request.getParameter("id");
		if(StringUtils.isEmpty(id)){
			id="";
		}
		List<SongList> list=service.querySongList(id);
		return list;
	}
	/**
	 * 将某首歌加入歌单
	 */
	@RequestMapping(value="addToList",method=RequestMethod.GET)
	public String addToList(HttpServletRequest request){
		String s_Id=request.getParameter("sid");//要加入的歌曲的id
		String l_Id=request.getParameter("lid");//要加入的歌单
		System.out.println(s_Id);System.out.println(l_Id);
		
		//判断歌单中是否含有该首歌曲，若已含，则不再添加
		String songs=service.querySongList(l_Id).get(0).getSongs();
	    System.out.println(songs);

		String song[]=songs.split(",");
		for(String s:song){
			if(s.equals(s_Id)){
				return "歌单中已存在该歌曲，请勿重复添加";
			}
		}
		songs=songs+","+s_Id;
		System.out.println(songs);
		service.updateSongList(songs, l_Id);
		return "添加成功";
	}
}
