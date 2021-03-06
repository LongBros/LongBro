package com.longbro.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.longbro.service.SongService;
import com.longbro.util.DownloadUtil;
import com.longbro.util.TimeUtil;
/**
 * 1.添加歌曲2.分页查询歌曲3.关键词搜索歌曲4.根据歌手搜索歌曲5.根据id搜索歌曲
 * 9.查询今日、昨日、本周、本月、一日内、一周内、一月内……录入歌曲的数量
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年5月4日 上午3:24:57
 * @description
 * @version
 */
@Controller
public class SongController {
	@Autowired SongService service;
	public String getClassName(){
		return getClass().getName();
	}
	/**
	 * 1添加歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping ("addSong")
	@ResponseBody
	public Result addSong(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		Result result=new Result();
		request.setCharacterEncoding("utf-8");
		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("sourceId", request.getParameter("sourceId"));
		map.put("songName", request.getParameter("songName"));
		map.put("singer", request.getParameter("singer"));
		map.put("duration", request.getParameter("duration"));
		map.put("album", request.getParameter("album"));
		map.put("imgPath", request.getParameter("imgPath"));
		map.put("releaseTime", request.getParameter("releaseTime"));
		map.put("website", request.getParameter("website"));
		map.put("descr", request.getParameter("descr"));
		map.put("time", request.getParameter("time"));
		if(request.getParameter("website").equals("网易云音乐")){
			map.put("lyric",DownloadUtil.spideLyric(request.getParameter("sourceId")));
		}
		System.out.print(getClassName()+".addSong:"+request.getParameter("time"));
		Song song=service.querySongBySId(request.getParameter("sourceId"));
		if(song!=null){
			result.setData(null);
			result.setMsg("库中已有该歌曲，请勿重复添加！^-^");
			result.setResult(false);
			return result;
		}
		service.addSong(map);
//		下载歌曲
		DownloadUtil.downloadMp3(request.getParameter("sourceId"), request.getParameter("songName"));
//		网易云音乐同时下载歌词
		if(request.getParameter("website").equals("网易云音乐")){
			DownloadUtil.writeToFile(DownloadUtil.spideLyric(request.getParameter("sourceId")), request.getParameter("sourceId"));
		}
		result.setData(null);
		result.setMsg("添加歌曲成功");
		result.setResult(true);
		return result;
	}
	/**
	 * 2.分页查询歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="queryAllSongs",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> queryAllSongs(HttpServletRequest request,HttpServletResponse response)
	{		
		System.out.println(getClassName()+".queryAllSongs:"+request.getParameter("page"));
		List<Song> list=service.queryAllSongs(Integer.parseInt(request.getParameter("page")));
		return list;
	}
	/**
	 * 3.关键词搜索歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping (value="querySongs",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> querySongs(HttpServletRequest request,HttpServletResponse response)
	{		
		List<Song> list=service.querySongs(request.getParameter("key"));
		return list;
	}
	/**
	 * 4.根据歌手搜索歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="querySongsBySinger",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> querySongsBySinger(HttpServletRequest request,HttpServletResponse response)
	{		
		List<Song> list=service.querySongsBySinger(request.getParameter("singer"));
		return list;
	}
	/**
	 * 5.根据歌词搜索歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年8月5日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="querySongsByLyric",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> querySongsByLyric(HttpServletRequest request,HttpServletResponse response)
	{		
		List<Song> list=service.querySongsBySinger(request.getParameter("key"));
		return list;
	}
	/**
	 * 6.强力搜索功能-根据关键词同时搜索歌曲名、歌手、歌词~分别使用三条sql查询
	 * @desc 
	 * @author zcl
	 * @date 2019年8月5日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping (value="strongQuerySongs",method=RequestMethod.GET)
	@ResponseBody
	public List<List<Song>> strongQuerySongs(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{	
		List<Song> listSongs=service.querySongs(request.getParameter("key"));
		List<Song> listSinger=service.querySongsBySinger(request.getParameter("key"));
		List<Song> listLyric=service.querySongsByLyric(request.getParameter("key"));
		List<List<Song>> ls=new ArrayList<List<Song>>();
		ls.add(listSongs);
		ls.add(listSinger);
		ls.add(listLyric);
		return ls;
	}
	/**
	 * 7.根据id搜索歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="querySongById",method=RequestMethod.GET)
	@ResponseBody
	public Song querySongById(HttpServletRequest request,HttpServletResponse response)
	{		
		System.out.println(getClassName()+".querySongById:"+request.getParameter("id"));
		Song song=service.querySongById(Integer.parseInt(request.getParameter("id")));
		return song;
	}
	/**
	 * @desc 8.编辑歌曲
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping (value="editSong",method=RequestMethod.POST)
	@ResponseBody
	public void editSong(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{	
		Song song=new Song();
		request.setCharacterEncoding("utf-8");
		song.setId(Integer.parseInt(request.getParameter("id")));
		song.setSourceId(request.getParameter("sourceId"));
		song.setSongName(request.getParameter("songName"));
		song.setDuration(request.getParameter("duration"));
		song.setAlbum(request.getParameter("album"));
		song.setSinger(request.getParameter("singer"));
		song.setReleaseTime(request.getParameter("releaseTime"));
		song.setImgPath(request.getParameter("imgPath"));
		song.setWebsite(request.getParameter("website"));
		song.setDescr(request.getParameter("descr"));
		song.setLyric(request.getParameter("lyric"));
		service.editSong(song);
		
		return;
	}
	/**
	 * 9.根据播放列表中的id批量查询歌曲
	 * @desc 
	 * @author zcl
	 * @date 2019年5月4日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="queryPListSong",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Song> queryPListSong(HttpServletRequest request,HttpServletResponse response)
	{	
		ArrayList<Song> list=new ArrayList<Song>();
		Song song;
		String ids=request.getParameter("pList");
		System.out.println(getClassName()+".queryPListSong:歌单歌曲id字符串处理前："+ids);
		if(ids.substring(0, 1).equals(",")){//如果歌单的歌曲id字符串第一个为,则截取之
			ids=ids.substring(1);
		}
		if(ids.substring(ids.length()-1).equals(",")){//如果歌单的歌曲id字符串最后一个为,则截取之
			ids=ids.substring(0,ids.length()-1);
		}
		System.out.println(getClassName()+".queryPListSong:歌单歌曲id字符串处理后："+ids);
		String idss[]=ids.split(",");
		for(String id:idss){
			song=service.querySongById(Integer.parseInt(id));
			list.add(song);
		}		
		return list;
	}
	/**
	 * 10.查询热播榜
	 * @desc 
	 * @author zcl
	 * @date 2019年8月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value="queryHotSongs",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> queryHotSongs(HttpServletRequest request,HttpServletResponse response)
	{		
		List<Song> list=service.queryHotSongs(Integer.parseInt(request.getParameter("num")));
		return list;
	}
	/**
	 * 11.查询今日、昨日、本周、本月、一日内、一周内、一月内……录入歌曲的数量
	 * @desc 
	 * @author zcl
	 * @date 2019年9月20日
	 * @param type
	 * @return
	 */
	@RequestMapping (value="findSongNumBy",method=RequestMethod.GET)
	@ResponseBody
	public Integer findSongNumBy(Integer type){
		String time="";//type=0时time=0，查询所有的
		if(type==1){//今日-可用like %time%，也可用>2019-09-20 00:00:00
			time=TimeUtil.getToday()+" 00:00:00";
		}else if(type==2){//昨日----只传一个time解决不了昨天。要么1.传昨天00:00~23:59:00要么用like
//			time=TimeUtil.getYesterday()+" 00:00:00";
		}else if(type==3){//本周
			//需得到本周第一天即周一的年月日
			Calendar calendar=Calendar.getInstance();
			String cweekNum="";//中文，如：星期一
			calendar.setTime(new Date());
			switch(calendar.get(Calendar.DAY_OF_WEEK)){
				case 1:time=TimeUtil.getAgo(6).substring(0, 10)+" 00:00:00";break;//周日	
				case 2:time=TimeUtil.getAgo(5).substring(0, 10)+" 00:00:00";break;
				case 3:time=TimeUtil.getAgo(4).substring(0, 10)+" 00:00:00";break;
				case 4:time=TimeUtil.getAgo(3).substring(0, 10)+" 00:00:00";break;
				case 5:time=TimeUtil.getAgo(2).substring(0, 10)+" 00:00:00";break;
				case 6:time=TimeUtil.getAgo(1).substring(0, 10)+" 00:00:00";break;
				case 7:time=TimeUtil.getAgo(0).substring(0, 10)+" 00:00:00";break;
			}
		}else if(type==4){//本月
			time=TimeUtil.getToday().substring(0, 7)+"-00 00:00:00";
		}else if(type==5){//一日内
			time=TimeUtil.getAgo(1);
		}else if(type==6){//一周内
			time=TimeUtil.getAgo(7);
		}else if(type==7){//一月内
			time=TimeUtil.getAgo(31);
		}
		
		System.out.println(getClassName()+".findSongNumBy(Integer type)"+":"+type+"》》》》》》》》》》》》"+time);
		return service.findSongNumBy(time);
	}
	/**
	 * @desc 12.查询所有的歌手
	 * @author zcl
	 * @date 2019年11月23日
	 * @return
	 */
	@RequestMapping (value="queryAllSinger",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<String> queryAllSinger(){
		ArrayList<String> ss=service.queryAllSinger();
		return ss; 
	}
	/**
	 * @desc 13.根据资源id得到资源信息
	 * @author zcl
	 * @date 2019年10月4日
	 * @param sourceId
	 * @return
	 */
	@RequestMapping (value="querySongBySId",method=RequestMethod.GET)
	@ResponseBody
	public Song querySongBySId(String sourceId){
		Song song=service.querySongBySId(sourceId);
		return song;
	}
	/**
	 * 14.强力搜索功能2-根据关键词同时搜索歌曲名、歌手、歌词~合并为一条sql查询
	 * @author LongBro
	 * 2019年12月31日
	 * 下午5:48:04
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping (value="strongQuerySongs2",method=RequestMethod.GET)
	@ResponseBody
	public List<Song> strongQuerySongs2(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{	
		List<Song> ls=service.strongQuerySongs(request.getParameter("key"));
		return ls;
	}
}
