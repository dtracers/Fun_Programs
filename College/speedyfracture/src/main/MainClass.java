package main;

import image.ColorDecoder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainClass extends JPanel
  implements ActionListener
{
  ColorDecoder s;
  JSlider sl;
  JSlider sl2;
  static String f;

  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Slider");
    frame.setVisible(false);
    frame.setDefaultCloseOperation(3);
    frame.setSize(400, 200);
    frame.setAlwaysOnTop(true);
    frame.setResizable(false);
    BufferedImage x = chooseNewImage(frame);
    MainClass temp = new MainClass(x);
    frame.add(temp);
    frame.setVisible(true);
  }

  private void recognize(int intensity)
  {
    System.out.println("recognizing!! with :" + intensity);

    this.s.FractureColors(intensity, this.sl2.getValue());
    this.s.createAllFrames();
  }

  	public MainClass(BufferedImage x)
  	{
  		//setting the layout
  		setLayout(new GridBagLayout());
  		
  		//setting the color decoder
	    this.s = new ColorDecoder(x, f);
	  //  JPanel sliderPanel=new JPanel();
	   // sliderPanel.setLayout(new GridBagLayout());
	    
	    //JLabel
	    GridBagConstraints label = new GridBagConstraints();
	    label.gridx = 0;
	    label.gridy = 0;
	    add(new JLabel("Threshold"),label);
	    
	    //JSlider
	    GridBagConstraints slider = new GridBagConstraints();
	    slider.gridx = 0;
	    slider.gridy = 1;
		this.sl = new JSlider();
		this.sl.setMaximum(10);
		this.sl.setMinimum(1);
		this.sl.setValue(5);
		this.sl.setPaintTicks(true);
		this.sl.setMajorTickSpacing(1);
		this.sl.setSnapToTicks(true);
		add(this.sl,slider);
		
		//JLAbel
		label = new GridBagConstraints();
	    label.gridx = 1;
	    label.gridy = 0;
	    add(new JLabel("Algorithm"),label);
		
		//JSlider
		slider = new GridBagConstraints();
	    slider.gridx = 1;
	    slider.gridy = 1;
		this.sl2 = new JSlider();
		this.sl2.setMaximum(5);
		this.sl2.setMinimum(0);
		this.sl2.setValue(5);
		this.sl2.setPaintTicks(true);
		this.sl2.setMajorTickSpacing(1);
		this.sl2.setSnapToTicks(true);
		add(this.sl2,slider);
		
		//Add SliderPanel
		//add(sliderPanel);
		
		//button Panel
		//JPanel buttonPanel=new JPanel();
		//buttonPanel.setLayout(new GridBagLayout());
		
		//buttons!
		GridBagConstraints button = new GridBagConstraints();
		button.gridx = 0;
		button.gridy = 2;
		JButton but = new JButton("Recognize");
		but.setActionCommand("Go!");
		but.addActionListener(this);
		add(but,button);
		
		button = new GridBagConstraints();
		button.gridx = 1;
		button.gridy = 2;
		but = new JButton("New Picture");
		but.setActionCommand("New");
	    but.addActionListener(this);
	    add(but,button);
	    
	  //  add(buttonPanel);
	}

  public static BufferedImage chooseNewImage(JFrame j) {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
      "JPG & GIF Images", new String[] { "jpg", "gif", "jpeg" });
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(j);
    if (returnVal == 0) {
      System.out.println("You chose to open this file: " + 
        chooser.getSelectedFile().getName());
    }
    try
    {
      File s = chooser.getSelectedFile();
      f = s.getName();
      ImageIcon i = new ImageIcon(s.getAbsolutePath());
      Image image = i.getImage();
      while (i.getImageLoadStatus() != 8)
      {
        System.out.println("Not Complete");
      }
      BufferedImage imagefinal = new BufferedImage(image.getWidth(i.getImageObserver()), image.getHeight(i.getImageObserver()), 
        1);
      imagefinal.getGraphics().drawImage(image, 0, 0, i.getImageObserver());
      return imagefinal;
    }
    catch (Exception e) {
      e.printStackTrace();
    }return new BufferedImage(j.getWidth(), j.getHeight(), 1);
  }

  public void actionPerformed(ActionEvent arg0)
  {
    if (arg0.getActionCommand().equals("Go!"))
    {
      recognize(this.sl.getValue());
    }
    if (arg0.getActionCommand().equals("New"))
    {
      this.s = new ColorDecoder(chooseNewImage(new JFrame()), f);
    }
  }
}