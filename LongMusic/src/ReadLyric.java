import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 去掉歌词带的时间，读取带歌词时间的歌词文件-》保存处理后的纯歌词文件
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年4月6日 上午9:33:23
 * @description
 * @version
 */
public class ReadLyric {
	public static ArrayList<String> getTime(String lrc,ArrayList<String> times){
		String time=lrc.substring(lrc.indexOf("<")+1, lrc.indexOf(">"));
		times.add(time);
//		System.out.println(time);
		lrc=lrc.substring(lrc.indexOf(">")+1);
//		System.out.println(lrc);
		if(lrc.contains(">")){
			getTime(lrc, times);
		}
//		System.out.println(times);
		return times;
	}
	public static void main(String[] args) {
		File file=new File("E:/AAAA/alyric/509313735.txt");
		try {
			String lrc="";//含有时间的歌词
			FileReader reader=new FileReader(file);
			BufferedReader br=new BufferedReader(reader);
			String tem="";
			while((tem=br.readLine())!=null){
				lrc=lrc+tem;
			}
//			System.out.println(lrc);
			lrc=lrc.replaceAll("\\[", "<");
			lrc=lrc.replaceAll("]", ">");
		    String ss[]=lrc.split("<[.[^<]]*>");
 		    System.out.println(ss.length-1);//时间点下歌词数量
 		    ArrayList<String> times=new ArrayList<String>();
 		   times=getTime(lrc,times);
// 		   System.out.println(times);
 		    
 		    for(int i=0;i<times.size();i++){
 		    	System.out.println(times.get(i)+ss[i+1]);
 		    }
//		    String pureLyric=file.getName().replace(".txt", "")+"\r";//单纯的歌词
//		    for(String s:ss){//基数是汉语，偶数是翻译
//		    	pureLyric=pureLyric+s+"<br>\r";
//		    }
//			System.out.println(pureLyric);//单纯的歌词
//			printLyric(pureLyric, file.getName());
//			System.out.println(lrc);//含有时间的歌词
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			
		}
	}
	/**
	 * 输出纯歌词文件
	 * @desc 
	 * @author zcl
	 * @date 2019年4月6日
	 * @param lyric	纯歌词
	 * @param name	输出的文件名
	 */
	public static void printLyric(String lyric,String name){
		File file=new File("E:/AAAA/lyric/"+name);
		
		try {
			file.createNewFile();
			FileOutputStream fos=new FileOutputStream(file);
			
			fos.write(lyric.getBytes());
			System.out.println("纯歌词文件已输出至"+file.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
