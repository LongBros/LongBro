package com.longbro.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * 机器账号每日自动发表日记，包括：古诗账号(66666666)，歌词账号(88888888)
 * 1.确认当天是否已生成，已生成返回true	2.根据账号为其生成对应的日记 3.得到可用的即未被录入过的id
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年10月26日 上午8:58:33
 * @description
 * @version
 */
public class MachineAccount {
	static Statement st=null;
	static ResultSet rs=null;
	public static void main(String[] args) {
		int machines[]={66666666,88888888};
			for(int account:machines){
//				if(!ifHasGen(account)){
					genDiary(account);
//				}else{
//					System.out.println("当日已生成过！");
//				}
			}
		
		
	}
	/**
	 * 1.确认当天是否已生成，已生成返回true
	 * @desc 
	 * @author zcl
	 * @date 2019年11月10日
	 * @return
	 */
	public static boolean ifHasGen(int account){
		Statement st1=JdbcUtil.getCon();
		try {
			ResultSet rs1=null;
			rs1=st1.executeQuery("select * from d_diary where n_Writter='"+account+"' and n_Time like '%"+TimeUtil.getToday()+"%'");
			if(rs1.next())
				return true;
			st1.close();
			rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @desc 2.根据账号为其生成对应的日记
	 * @author zcl
	 * @date 2019年10月26日
	 * @param account
	 */
	public static void genDiary(int account){
		int len=0;
		String table="";
		String selSql="",updSql="",insSql="";
		String status="";//是否已录入状态值在原始表中的字段名
		if(account==66666666){//古诗账号
			len=1000;
			table="poem";
			selSql="select * from "+table+" where p_Id=";
			updSql="update "+table+" set p_Status=1,p_usetime='"+TimeUtil.time()+"' where p_Id=";
			status="p_Status";
		}else if(account==88888888){//歌词账号
			len=600;
			table="song";
			selSql="select * from "+table+" where id=";
			updSql="update "+table+" set status=1,use_time='"+TimeUtil.time()+"' where id=";
			status="status";
		}
		st=JdbcUtil.getCon();
		try {
			int id=597;//此id已为未录入过的
			System.out.println(id);
			rs=st.executeQuery(selSql+id);//查询出日记
			while(rs.next()){
				//日记的信息
				int type=3;
				String title="";
				String content="";
				String songId="";
				String time=TimeUtil.getToday()+" "+TimeUtil.genRandomTime();
				int wea=0;
				int mood=0;
				String loc="河南省邓州市";
				if(table=="poem"){
					title=rs.getString("p_Name")+"-"+rs.getString("p_Poet");
					content=rs.getString("p_PoemCons");
				}else if(table=="song"){
					title=rs.getString("songName")+"-"+rs.getString("singer");
					content=rs.getString("lyric");
					songId=rs.getString("sourceId");
				}
				//根据原始表内容生成日记并录入日记表
				insSql="INSERT INTO `music`.`d_diary` (`n_Type`, `n_BookId`, `n_Writter`, `n_Title`, `n_Content`, `n_Time`, `n_Weather`, `n_Mood`, `n_Location`, `n_AllowComment`, `n_Authority`,`n_song_id`) VALUES ('"
				+type+"', NULL, '"+account+"', '"+title+"', '"+content+"', '"
						+time+"', '"+wea+"', '"+mood+"', '"+loc+"', '0', '0','"+songId+"');";
				System.out.println(insSql);
				st.executeUpdate(updSql+id);//更新原表该条记录为已被使用
				System.out.println(updSql+id+";");

			}
//			st.executeUpdate(updSql+id);//更新原表该条记录为已被使用
			System.out.println(updSql+id);

//			st.execute(insSql);
			st.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}
	/**
	 * @desc 3. 得到可用的即未被录入过的id
	 * @author zcl
	 * @date 2019年10月26日
	 * @param st
	 * @param status
	 * @param selSql
	 * @param len
	 * @return
	 */
	public static int getUsableRawData(Statement st,String status,String selSql,int len){
		int id=new Random().nextInt(len);
		try {
			ResultSet rs=st.executeQuery(selSql+id);
			if(rs.next()){
				String sta=rs.getString(status);
				if(sta.equals("1")){//之前已被哆啦日记使用过
//					rs.close();//11-22新加，并将下面加return
					return getUsableRawData(st,status,selSql,len);//递归重新生成
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//查询出日记
		return id;
	}
}
