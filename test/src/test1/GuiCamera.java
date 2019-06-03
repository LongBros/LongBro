package test1;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;
 
import java.awt.*; 
import java.awt.event.ActionEvent;
import javax.swing.*;
 
import javax.imageio.ImageIO;
 
/*******************************************************************
 * 该JavaBean可以直接在其他Java应用程序中调用，实现屏幕的"拍照"
 * This JavaBean is used to snapshot the GUI in a
 * Java application! You can embeded
 * it in to your java application source code, and us
 * it to snapshot the right GUI of the application
 * @see javax.ImageIO
 * @author liluqun ([email]liluqun@263.net[/email])
 * @version 1.0
 *
 *****************************************************/
 
public class GuiCamera extends JFrame implements ActionListener, Runnable
{
    private String fileName; //文件的前缀
    private String defaultName = "GuiCamera";
    static int serialNum=0;
    private String imageFormat; //图像文件的格式
    private String defaultImageFormat="JPEG";
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
 
	static String showname=null;
	static int shownum=1;
	Thread ko_Thread;
	int flag;
	
	
		JButton jb1 = new JButton("开始");
		JButton jb2 = new JButton("暂停");
		JButton jb3= new JButton("停止");
		JButton jb4=new JButton("播放录象");
		JButton jb5=new JButton("选择");
		JPanel   jp1= new JPanel();
		JLabel  label= new JLabel();
 
		
		public void paint(Graphics g){
		
		if(showname!=null){
 
		Image image=getToolkit().getImage(showname);//声明一个成员变量
 
   		
		g.drawImage(image,0,0,1440,860,this);
 
		}
 
		}
 
/********************************************************************************************************/
	//线程开始
	public void start(){
 
          	if(ko_Thread==null){
		
		ko_Thread=new Thread(this);
 
		ko_Thread.start();
		
		
 
		}
 
			}
	//结束
 
	 public void stop(){
 
		if(ko_Thread!=null){
 
			ko_Thread=null;
 
		}
			}
	//执行
 
	 public void run(){
 
		Thread thisThread=Thread.currentThread();
 
		while(ko_Thread==thisThread){
 
		if(flag==0)
 
		snapShot();
 
		if(flag==1){
 
		showname="hello"+shownum+".JPEG";
		
		if(shownum<serialNum)
 
		shownum++;
 
		repaint();//重画
		
		
		}
 
		try{
 
			Thread.sleep(400);
 
			}catch(InterruptedException e){}
 
 
 
		
		}
		
			
		}
 
 
    /****************************************************************
     * 默认的文件前缀为GuiCamera，文件格式为JPEG格式
     * The default construct will use the default
     * Image file surname "GuiCamera",
     * and default image format "JPEG"
     ****************************************************************/
    public GuiCamera() {
 
      fileName = defaultName;
      imageFormat=defaultImageFormat;
 
    }
 
    /****************************************************************
     * @param s the surname of the snapshot file
     * @param format the format of the  image file,
     * it can be "jpg" or "png"
     * 本构造支持JPG和PNG文件的存储
     ****************************************************************/
    public GuiCamera(String s,String format) {
 
	super("欢迎使用java版的屏幕录象软件,有待改进");
	
      	fileName = s;
      	imageFormat=format;
 
	
 
	
		this.setSize(1440,900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(jp1,BorderLayout.SOUTH);
 
		this.add(label,BorderLayout.CENTER);
 
 
		this.setVisible(true);
 
		jp1.setLayout(new GridLayout(1,4));
 
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
 
		jb1.addActionListener(this);
		jb4.addActionListener(this);
		jb3.addActionListener(this);
 
 
		ImageIcon icon = new ImageIcon();
		
		label.setIcon(icon); 
 
		jp1.setVisible(true);
 
		label.setVisible(true);
 
    }
 
 
	public void actionPerformed(ActionEvent e){
 
		Object source=e.getSource();
		if(source==jb1){
 
		//ImageIcon icon = new ImageIcon("hello1.JPEG");
		
		//label.setIcon(icon); 
 
		//snapShot(); 
 
		flag=0;//录制标记
 
		start();
 
				}
 
 
		if(source==jb3){
		
 
		stop();
 
		}
 
 
		if(source==jb4){
 
		flag=1;//播放标记
 
		start();
 
 
		}
			}
	 
    /****************************************************************
     * 对屏幕进行拍照
     * snapShot the Gui once
     ****************************************************************/
    public void snapShot() {
 
      try {
      //拷贝屏幕到一个BufferedImage对象screenshot
        BufferedImage screenshot = (new Robot()).createScreenCapture(new
            Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
        serialNum++;
        //根据文件前缀变量和文件格式变量，自动生成文件名
        String name=fileName+String.valueOf(serialNum)+"."+imageFormat;
        File f = new File(name);
        //System.out.print("Save File "+name);
      //将screenshot对象写入图像文件
        ImageIO.write(screenshot, imageFormat, f);
        //System.out.print("..Finished!\n");
 
	//System.out.print(d.getWidth()+"        "+d.getHeight());
      }
      catch (Exception ex) {
        System.out.println(ex);
      }
    }
 
    public static void main(String[] args)
    {
 
	
        GuiCamera cam= new GuiCamera("D:\\long\\", "jpg");
 
        //cam.snapShot();
    }
}

