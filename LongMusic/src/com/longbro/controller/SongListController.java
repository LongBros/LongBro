package com.longbro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
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
import com.longbro.util.TimeUtil;
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
	@RequestMapping(value="newList",method=RequestMethod.GET)
	@ResponseBody
	public void newList(HttpServletRequest request){
		SongList list=new SongList();
		list.setName(request.getParameter("name"));
		list.setDescr(request.getParameter("desc"));
		list.setSongs(request.getParameter("songIds"));
		list.setTimes(0);
		list.setTime(TimeUtil.time());
		if(StringUtils.isNotEmpty(request.getParameter("creator")))
			list.setCreator(request.getParameter("creator"));
//		System.out.println(request.getParameter("name"));
		service.addSongList(list);
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
		String creator=request.getParameter("creator");
		if(StringUtils.isEmpty(id)){
			id="";
		}
		if(StringUtils.isEmpty(creator)){
			creator="";
		}
		List<SongList> list=service.querySongList(id,creator);
		return list;
	}
	/**
	 * 将某首歌加入歌单
	 * @throws IOException 
	 */
	@RequestMapping(value="addToList",method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String,String> addToList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HashMap<String,String> map=new HashMap<String,String>();
		String s_Id=request.getParameter("sid");//要加入的歌曲的id
		String l_Id=request.getParameter("lid");//要加入的歌单		
		String songs=service.querySongList(l_Id,"").get(0).getSongs();
		//判断歌单中是否含有该首歌曲，若已含，则不再添加
		String song[]=songs.split(",");
		PrintWriter out=response.getWriter();
		for(String s:song){
			if(s.equals(s_Id)){
				map.put("status","歌单中已存在该歌曲，请勿重复添加");
				return map;
			}
		}
		if(songs.equals("")){//如果歌单中还无歌曲
			songs=songs+","+s_Id+",";
		}else{
			songs=songs+s_Id+",";
		}
		service.updateSongList(songs, l_Id);
		map.put("status","添加成功");
		System.out.println("添加成功");
		return map;
	}
	/**
	 * @desc 从歌单中移除歌曲
	 * @author zcl
	 * @date 2019年6月1日
	 */
	@RequestMapping(value="removeFromList",method=RequestMethod.GET)
	public void removeFromList(HttpServletRequest request,HttpServletResponse response){
		String s_Id=request.getParameter("sid");//要移除的歌曲的id
		String l_Id=request.getParameter("lid");//歌曲所在的歌单
		
		//判断歌单中是否含有该首歌曲，若含有，则移除
		String songs=service.querySongList(l_Id,null).get(0).getSongs();
		if(songs.contains(","+s_Id+",")){
			songs=songs.replace(","+s_Id+",", ",");
		}
		service.updateSongList(songs, l_Id);
	}
}
