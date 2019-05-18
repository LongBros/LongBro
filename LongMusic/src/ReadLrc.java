import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2018年6月16日 上午10:27:28
 * @description
 * @version
 */
public class ReadLrc {
	public static void main(String[] args) {
		File file=new File("D:/backup/My love.xlrc");
		try {
			String lrc="";
			FileReader reader=new FileReader(file);
			BufferedReader br=new BufferedReader(reader);
			String tem="";
			while((tem=br.readLine())!=null){
				lrc=lrc+tem;
			}
			lrc=lrc.replaceAll("\\[", "<");
			lrc=lrc.replaceAll("]", ">");
		    String ss[]=lrc.split("<[.[^<]]*>");
		    System.out.println(ss.length);
		    int i=1;
		    for(String s:ss){//基数是汉语，偶数是翻译
		    	System.out.println(i+"   "+s);
		    	i++;
		    }
			System.out.println(lrc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			
		}
	}

}
