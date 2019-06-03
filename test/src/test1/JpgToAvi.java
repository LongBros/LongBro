package test1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
 
import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.utils.MovieUtils;
 
public class JpgToAvi {
 
	 public static void main(String[] args) throws Exception{
		 
	        //jpgsĿ¼����jpgͼƬ,ͼƬ�ļ���Ϊ(1.jpg,2.jpg...)
	        final File[] jpgs = new File("D:\\long\\").listFiles();
	 
	        //���ļ�����������(��ʾ���ٶ��ļ����е�����ԽС,������Ƶ��֡��Խ��ǰ)
	        Arrays.sort(jpgs,new Comparator<File>(){
		   public int compare(File file1, File file2) {
		       String numberName1 = file1.getName().replace(".jpg", "");
		       String numberName2 = file2.getName().replace(".jpg", "");
		       return new Integer(numberName1) - new Integer(numberName2);
		   }
	        });   
	 
	        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider("dst.avi");
	        dmip.setFPS(1);			//����ÿ��֡��
	         dmip.setNumberOfFrames(jpgs.length);	//��֡��
	         dmip.setMWidth(100);
	        dmip.setMHeight(200);     
	 
	        new Jim2Mov(new ImageProvider(){
		   public byte[] getImage(int frame) {
		       try {
	                   //����ѹ����
		           return MovieUtils.convertImageToJPEG((jpgs[frame]), 1.0f);  
	                } catch (IOException e) {
		           e.printStackTrace();
		       }
		       return null;
		   } 	
	        }, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
	    }    
}
