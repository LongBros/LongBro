package test1;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class CaptureScreen extends JFrame{
	public CaptureScreen(){
		final JFrame frame=new JFrame("截屏");
    	frame.setSize(100,100);
    	frame.setVisible(true);
    	Button btn=new Button("截取");
    	btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	            try {
	            	frame.setVisible(false);
					captureScreen("E:\\你好","22.png");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
    	frame.add(btn);
    	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    public static void captureScreen(String folder, String fileName) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        File screenFile = new File(folder);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, fileName);

        ImageIO.write(image, "png", f);
        //自动打开
        if (Desktop.isDesktopSupported()
                 && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
                    Desktop.getDesktop().open(f);
    }

    public static void main(String[] args) {
    	
        new CaptureScreen();
    }

}
