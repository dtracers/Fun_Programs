package Main;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

import Image.ColorFracture;

public class MainClass extends JPanel implements  ActionListener
{
	ColorFracture s;
	JSlider sl;
	JSlider sl2;
	JButton button;
	static String f;
	public static void main(String args[])
	{
		JFrame frame=new JFrame("Control");
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(300,200);
		frame.setAlwaysOnTop(true);
		BufferedImage x=chooseNewImage(frame);
		MainClass temp=new MainClass(x);
		frame.add(temp);
		//frame.pack();
		frame.setVisible(true);
		//temp.recognize(5);
	}
	private void recognize(int intensity)
	{		
		System.out.println("recognizing!! with :"+intensity);
		s.killAllFrames();
		s.reset();
		s.createAlgorithms(intensity,sl2.getValue());
		s.FractureColors();
		Thread d=new Thread(){
			public void run()
			{
				s.createAllFrames();
				button.setActionCommand("Go!");
				button.setText("Recognize");
				button.setToolTipText("this will cause it to try and recognize things from the picture");
			}
		};
		d.setDaemon(true);
		d.start();
	}
	public MainClass(BufferedImage x)
	{
		super();
		s=new ColorFracture(x,f);
		sl=new JSlider();
		sl.setMaximum(10);
		sl.setMinimum(1);
		sl.setValue(5);
		sl.setPaintTicks(true);
		sl.setMajorTickSpacing(1);
		sl.setSnapToTicks(true);
		add(sl);
		
		sl2=new JSlider();
		sl2.setMaximum(7);//THIS IS THE TYPES
		sl2.setMinimum(1);
		sl2.setValue(1);
		sl2.setPaintTicks(true);
		sl2.setMajorTickSpacing(1);
		sl2.setSnapToTicks(true);
		add(sl2);
		
		
		button=new JButton("Recognize");
		button.setActionCommand("Go!");
		button.addActionListener(this);
		add(button);
		
		JButton but=new JButton("New Picture");
		but.setActionCommand("New");
		but.addActionListener(this);
		add(but);
	}
	public static BufferedImage chooseNewImage(JFrame j)
	{
		 JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF &PNG Images", "jpg", "gif","jpeg","png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(j);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
		    try
		    {
			    File s=chooser.getSelectedFile();
			    f=s.getName();
			    ImageIcon i=new ImageIcon(s.getAbsolutePath());
			    Image image=i.getImage();
			    while(i.getImageLoadStatus()!=MediaTracker.COMPLETE)
			    {
			    	System.out.println("Not Complete");
			    }
			    BufferedImage imagefinal=new BufferedImage(image.getWidth(i.getImageObserver()),image.getHeight(i.getImageObserver()),
			    		BufferedImage.TYPE_INT_RGB);
			    imagefinal.getGraphics().drawImage(image, 0, 0, i.getImageObserver());
			    return imagefinal;
		    }catch(NullPointerException e)
		    {
		    	e.printStackTrace();
		    	JOptionPane.showConfirmDialog(j, "The file you selected does not exist");
		    	return new BufferedImage(j.getWidth(),j.getHeight(),BufferedImage.TYPE_INT_RGB);
		    }
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Go!"))
		{
			System.gc();
			JButton button=(JButton) arg0.getSource();
			button.setText("Stop Now");
			button.setToolTipText("May cause issues");
			button.setActionCommand("Stop!");
			recognize(sl.getValue());
			
		}
		if(arg0.getActionCommand().equals("Stop!"))
		{
			JButton button=(JButton) arg0.getSource();
			button.setActionCommand("Go!");
			button.setText("Recognize");
			button.setToolTipText("this will cause it to try and recognize things from the picture");
			s.STOP();
			
			//recognize(sl.getValue());
		}
		if(arg0.getActionCommand().equals("New"))
		{
			s=new ColorFracture(chooseNewImage(new JFrame()),f);
		}
	}
}
