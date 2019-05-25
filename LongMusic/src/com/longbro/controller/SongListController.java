package com.longbro.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@RequestMapping("addSongList")
	public void addSongList(Map<Object, Object> map){
		
	}
	@RequestMapping(value="querySongList",method=RequestMethod.GET)
	@ResponseBody
	public List<SongList> querySongList(){
		List<SongList> list=service.querySongList();
		return list;
	}
}
