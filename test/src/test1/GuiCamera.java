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
 * ��JavaBean����ֱ��������JavaӦ�ó����е��ã�ʵ����Ļ��"����"
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
    private String fileName; //�ļ���ǰ׺
    private String defaultName = "GuiCamera";
    static int serialNum=0;
    private String imageFormat; //ͼ���ļ��ĸ�ʽ
    private String defaultImageFormat="JPEG";
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
 
	static String showname=null;
	static int shownum=1;
	Thread ko_Thread;
	int flag;
	
	
		JButton jb1 = new JButton("��ʼ");
		JButton jb2 = new JButton("��ͣ");
		JButton jb3= new JButton("ֹͣ");
		JButton jb4=new JButton("����¼��");
		JButton jb5=new JButton("ѡ��");
		JPanel   jp1= new JPanel();
		JLabel  label= new JLabel();
 
		
		public void paint(Graphics g){
		
		if(showname!=null){
 
		Image image=getToolkit().getImage(showname);//����һ����Ա����
 
   		
		g.drawImage(image,0,0,1440,860,this);
 
		}
 
		}
 
/********************************************************************************************************/
	//�߳̿�ʼ
	public void start(){
 
          	if(ko_Thread==null){
		
		ko_Thread=new Thread(this);
 
		ko_Thread.start();
		
		
 
		}
 
			}
	//����
 
	 public void stop(){
 
		if(ko_Thread!=null){
 
			ko_Thread=null;
 
		}
			}
	//ִ��
 
	 public void run(){
 
		Thread thisThread=Thread.currentThread();
 
		while(ko_Thread==thisThread){
 
		if(flag==0)
 
		snapShot();
 
		if(flag==1){
 
		showname="hello"+shownum+".JPEG";
		
		if(shownum<serialNum)
 
		shownum++;
 
		repaint();//�ػ�
		
		
		}
 
		try{
 
			Thread.sleep(400);
 
			}catch(InterruptedException e){}
 
 
 
		
		}
		
			
		}
 
 
    /****************************************************************
     * Ĭ�ϵ��ļ�ǰ׺ΪGuiCamera���ļ���ʽΪJPEG��ʽ
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
     * ������֧��JPG��PNG�ļ��Ĵ洢
     ****************************************************************/
    public GuiCamera(String s,String format) {
 
	super("��ӭʹ��java�����Ļ¼�����,�д��Ľ�");
	
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
 
		flag=0;//¼�Ʊ��
 
		start();
 
				}
 
 
		if(source==jb3){
		
 
		stop();
 
		}
 
 
		if(source==jb4){
 
		flag=1;//���ű��
 
		start();
 
 
		}
			}
	 
    /****************************************************************
     * ����Ļ��������
     * snapShot the Gui once
     ****************************************************************/
    public void snapShot() {
 
      try {
      //������Ļ��һ��BufferedImage����screenshot
        BufferedImage screenshot = (new Robot()).createScreenCapture(new
            Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
        serialNum++;
        //�����ļ�ǰ׺�������ļ���ʽ�������Զ������ļ���
        String name=fileName+String.valueOf(serialNum)+"."+imageFormat;
        File f = new File(name);
        //System.out.print("Save File "+name);
      //��screenshot����д��ͼ���ļ�
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

