package com.longbro.note.bean;
/**
 * 描述：d_user_info实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-26 13:07:00
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class UserInfo{

	protected Integer UId;

	protected String UUserName; //用户名
	protected Integer UUserId; //系统生成的8位id
	protected Integer UUserSex; //性别
	protected Integer uShowWordnum; //12-14日记列表是否显示日记字数
	protected String blackNameList; //黑名单
	protected String UJoinTime; //加入哆啦日记网时间
	protected Integer UStatus; //在线状态(1:在线，0:离线)
	protected String UBornTime; //出生时间
	protected String UHomeSong; //家歌
	protected String signature;
	protected String headImage;
	protected String password;//2019-10-27
	protected String location;//2019-11-15
	private Integer autoPlay;//自动播放带音频日记的音频(0:提示，1:自动播放，2:不播放) 
	private String lastLogin;//最近登录
	private String back;//网站背景2019-12-01
	protected Integer perpageNum; //12-21默认每页加载多少篇(0：显示下拉列表，1：显示）
	private Integer inviter;//12-27	邀请者哆啦id
	private Integer showWhichTab;//12-27首页默认加载tab(0:推荐，1:关注，2:时间轴)
	private Integer showPlayer;//u_show_player是否在播放歌曲的时候显示播放器(0：不显示，1：显示)
	private Integer	textEditor;//写日记使用的编辑器(0：百度文本编辑器，1：普通文本区)
	protected String blackList; //2020-01-28不给看名单
	protected int listStyle;//2020-01-29日记列表显示样式(默认0:标题+部分内容，1:仅标题)
	protected int loopPlay;//2020-01-30音频是否循环播放 0:不循环，1:循环
	public Integer getUId() {
		return this.UId;
	}
	public void setUId(Integer aValue) {
		this.UId = aValue;
	}
	public void setUUserName(String UUserName) {
		this.UUserName = UUserName;
	}
	
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUUserName() {
		return this.UUserName;
	}
	public void setUUserId(Integer UUserId) {
		this.UUserId = UUserId;
	}
	
	/**
	 * 返回 系统生成的8位id
	 * @return
	 */
	public Integer getUUserId() {
		return this.UUserId;
	}
	public void setUUserSex(Integer UUserSex) {
		this.UUserSex = UUserSex;
	}
	
	/**
	 * 返回 性别
	 * @return
	 */
	public Integer getUUserSex() {
		return this.UUserSex;
	}
	
	public Integer getuShowWordnum() {
		return uShowWordnum;
	}
	public void setuShowWordnum(Integer uShowWordnum) {
		this.uShowWordnum = uShowWordnum;
	}
	public void setUJoinTime(String UJoinTime) {
		this.UJoinTime = UJoinTime;
	}
	
	public String getBlackNameList() {
		return blackNameList;
	}
	/**
	 * 默认黑名单
	 * 66666666,12345678,15577347,96664270,54343391,88007770,65313340
	 * 古诗网		FatLuo	 Yigongzi JinYong  MoYan	LuXun	 一只小兔几
	 * @author LongBro
	 * 2019年12月20日
	 * 下午6:23:35
	 * @return
	 */
	public void setBlackNameList(String blackNameList) {
		this.blackNameList = blackNameList;
	}
	/**
	 * 返回 加入哆啦日记网时间
	 * @return
	 */
	public String getUJoinTime() {
		return this.UJoinTime;
	}
	public void setUStatus(Integer UStatus) {
		this.UStatus = UStatus;
	}
	
	/**
	 * 返回 在线状态(1:在线，0:离线)
	 * @return
	 */
	public Integer getUStatus() {
		return this.UStatus;
	}
	public void setUBornTime(String UBornTime) {
		this.UBornTime = UBornTime;
	}
	
	/**
	 * 返回 出生时间
	 * @return
	 */
	public String getUBornTime() {
		return this.UBornTime;
	}
	public void setUHomeSong(String UHomeSong) {
		this.UHomeSong = UHomeSong;
	}
	
	/**
	 * 返回 家歌
	 * @return
	 */
	public String getUHomeSong() {
		return this.UHomeSong;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getAutoPlay() {
		return autoPlay;
	}
	public void setAutoPlay(Integer autoPlay) {
		this.autoPlay = autoPlay;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public Integer getPerpageNum() {
		return perpageNum;
	}
	public void setPerpageNum(Integer perpageNum) {
		this.perpageNum = perpageNum;
	}
	public Integer getInviter() {
		return inviter;
	}
	public void setInviter(Integer inviter) {
		this.inviter = inviter;
	}
	public Integer getShowWhichTab() {
		return showWhichTab;
	}
	public void setShowWhichTab(Integer showWhichTab) {
		this.showWhichTab = showWhichTab;
	}
	public Integer getShowPlayer() {
		return showPlayer;
	}
	public void setShowPlayer(Integer showPlayer) {
		this.showPlayer = showPlayer;
	}
	public Integer getTextEditor() {
		return textEditor;
	}
	public void setTextEditor(Integer textEditor) {
		this.textEditor = textEditor;
	}
	public String getBlackList() {
		return blackList;
	}
	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}
	public int getListStyle() {
		return listStyle;
	}
	public void setListStyle(int listStyle) {
		this.listStyle = listStyle;
	}
	public int getLoopPlay() {
		return loopPlay;
	}
	public void setLoopPlay(int loopPlay) {
		this.loopPlay = loopPlay;
	}
	
}
