package lab1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;




public class window {
	public static BufferedImage obraz,edytowany,porownywany_obr;
	int value0,value1;
	private String path,path1;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window window = new window();
					window.frame.setVisible(true);
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	
	public Color randomColor() {
		Random random_kolor = new Random();
		int red = random_kolor.nextInt(255);
		int green = random_kolor.nextInt(255);
		int blue = random_kolor.nextInt(255);
		return new Color(red,green,blue);
	}
	
	
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wybrany obraz");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//lblNewLabel.setIcon(new ImageIcon("X:\\lab1\\src\\img1.jpg"));
		lblNewLabel.setBounds(10, 11, 300, 330);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Obraz zmieniony");
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_1.setBounds(340, 11, 300, 330);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSlider binarny_slider = new JSlider();
		binarny_slider.setBounds(650, 31, 174, 26);
		binarny_slider.setMinimum(0);
		binarny_slider.setMaximum(255);
		binarny_slider.setEnabled(false);
		frame.getContentPane().add(binarny_slider);
		
		JLabel lblNewLabel_3 = new JLabel("Thresholding:");
		lblNewLabel_3.setBounds(650, 11, 78, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel sliderlabel = new JLabel("50");
		sliderlabel.setBounds(785, 11, 39, 14);
		frame.getContentPane().add(sliderlabel);
		
		binarny_slider.addChangeListener(new ChangeListener() {
			int value;
            Color color;
            
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	value = ((JSlider) ce.getSource()).getValue();
	            sliderlabel.setText(String.valueOf(value));
	            
	            try {
					edytowany = ImageIO.read(new File(path));
	            } catch (IOException exth){
					exth.printStackTrace();
				}
					for (int i=0; i<edytowany.getWidth(); i++) {
						for (int j=0; j<edytowany.getHeight(); j++) {
							color = new Color(edytowany.getRGB(i, j));
		                    if ((color.getRed()+color.getGreen()+color.getBlue())/3 < value) {
		                    	edytowany.setRGB(i, j, Color.black.getRGB());
		                    } else {
		                    	edytowany.setRGB(i, j, Color.white.getRGB());
		                    }
						}
					}
					
				
	            lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
	            
	        }
	        
	        
	        
	    });
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ImageIO.write(edytowany, "png", new File("image.png"));
				} catch (IOException esave) {
					esave.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(709, 408, 115, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
		
		JLabel lblMultithresholding = new JLabel("MultiThresholding:");
		lblMultithresholding.setBounds(650, 68, 118, 14);
		frame.getContentPane().add(lblMultithresholding);
		
		JLabel lblmulti1 = new JLabel("50");
		lblmulti1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblmulti1.setBounds(778, 116, 46, 14);
		frame.getContentPane().add(lblmulti1);
		
		JLabel lblmulti = new JLabel("50");
		lblmulti.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblmulti.setBounds(778, 68, 46, 28);
		frame.getContentPane().add(lblmulti);
		
		JSlider slider_multi = new JSlider();
		slider_multi.setBounds(650, 93, 174, 26);
		slider_multi.setEnabled(false);
		slider_multi.setMaximum(255);
		slider_multi.setMinimum(0);
		
		frame.getContentPane().add(slider_multi);
		
		slider_multi.addChangeListener(new ChangeListener() {
 
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	value0=((JSlider) ce.getSource()).getValue();
	        	 lblmulti.setText(String.valueOf(value0));
	        }
	        
	        
		});
		
		JSlider slider_multi1 = new JSlider();
		slider_multi1.setBounds(650, 130, 174, 26);
		slider_multi1.setEnabled(false);
		slider_multi1.setMaximum(255);
		slider_multi1.setMinimum(0);
		
		frame.getContentPane().add(slider_multi1);
		
		slider_multi1.addChangeListener(new ChangeListener() {
			 
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	value1=((JSlider) ce.getSource()).getValue();
	        	lblmulti1.setText(String.valueOf(value1));
	        }
	        
	        
		});
		
		JButton btnNewButton_3 = new JButton("Accept");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				
				
				Color color;
				int Y,temp;
				try {
					edytowany = ImageIO.read(new File(path));
					for (int i=0; i<edytowany.getWidth(); i++) {
						for (int j=0; j<edytowany.getHeight(); j++) {
							color = new Color(edytowany.getRGB(i, j));
							Y = (color.getRed()+color.getGreen()+color.getBlue())/3;
		                    if (value0 > value1) {
		                    	temp=value0;
		                    	value0=value1;
		                    	value1=temp;
		                    } 
		                    if ((Y >= value0) && (Y <= value1)) {
	                    		edytowany.setRGB(i, j, Color.white.getRGB());
	                    	} else {
	                    		edytowany.setRGB(i, j, Color.black.getRGB());
	                    	}
		                    
						}
					}
					
				} catch (IOException exth){
					exth.printStackTrace();
				}
	            
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
			}
		});
		btnNewButton_3.setBounds(735, 165, 89, 23);
		btnNewButton_3.setEnabled(false);

		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("RED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		       
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb;
				
				w=edytowany.getWidth();
				h=edytowany.getHeight();
				
				for(int i=0;i<w;i++) {
					for(int j=0;j<h;j++) {
						piksel = edytowany.getRGB(i, j);
						kolor = new Color(piksel,true);
						r = kolor.getRed();
						g = kolor.getGreen();
						b = kolor.getBlue();
						g=0;
						b=0;
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i,j,rgb);
					}
				}
				
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
			
			}
		});
		btnNewButton.setBounds(230, 363, 100, 23);
		btnNewButton.setEnabled(false);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGreen = new JButton("GREEN");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb;
				
				w=edytowany.getWidth();
				h=edytowany.getHeight();
				
				for(int i=0;i<w;i++) {
					for(int j=0;j<h;j++) {
						piksel = edytowany.getRGB(i, j);
						kolor = new Color(piksel,true);
						r = kolor.getRed();
						g = kolor.getGreen();
						b = kolor.getBlue();
						r=0;
						b=0;
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i,j,rgb);
					}
				}
				
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
			
			}
		});
		btnGreen.setBounds(120, 363, 100, 23);
		btnGreen.setEnabled(false);
		frame.getContentPane().add(btnGreen);
		
		JButton btnBlue = new JButton("BLUE");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb;
				
				w=edytowany.getWidth();
				h=edytowany.getHeight();
				
				for(int i=0;i<w;i++) {
					for(int j=0;j<h;j++) {
						piksel = edytowany.getRGB(i, j);
						kolor = new Color(piksel,true);
						r = kolor.getRed();
						g = kolor.getGreen();
						b = kolor.getBlue();
						r=0;
						g=0;
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i,j,rgb);
					}
				}
			
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
		
			}
		});
		btnBlue.setBounds(10, 363, 100, 23);
		btnBlue.setEnabled(false);
		frame.getContentPane().add(btnBlue);
		
		JButton generacja = new JButton("Generate new image");
		generacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h;
				w=edytowany.getWidth();
				h=edytowany.getHeight();
				int kwadrat = randomColor().getRGB();
				
				for (int i=0; i < (w/2); i++) {
					for (int j=0; j < (h/2); j++) {
						edytowany.setRGB(i, j, kwadrat);
					}
				}
				kwadrat = randomColor().getRGB();
				for (int i= 0; i < (w/2); i++) {
					for (int j= (h/2); j < h; j++) {
						edytowany.setRGB(i, j, kwadrat);
					}
				}
				
				kwadrat = randomColor().getRGB();
				for (int i= (w/2); i < w; i++) {
					for (int j= 0; j < (h/2); j++) {
						edytowany.setRGB(i, j, kwadrat);
					}
				}
				
				kwadrat = randomColor().getRGB();
				for (int i= (w/2); i < w; i++) {
					for (int j= (h/2); j < h; j++) {
						edytowany.setRGB(i, j, kwadrat);
					}
				}
				
				
				int triangelek = randomColor().getRGB();
				for (int i= 0; i < (w/2); i++) {
					for (int j=0; j < i ; j++) {
					    edytowany.setRGB(i, j, triangelek);
					}
				}
				
				int rektangelek = randomColor().getRGB();
				for (int i= (w/2); i < w; i++) {
					for (int j=0; j < ((h/2)/2) ; j++) {
					    edytowany.setRGB(i, j, rektangelek);
					}
				}
				
				/*
				for(int i=0;i<w;i++) {
					for(int j=0;j<h-1;j++) {
						piksel = edytowany.getRGB(i, j);
						kolor = new Color(piksel,true);
						b=200;
						r=0;
						g=0;
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i,j,rgb);
						
					}
					
				}
				*/
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
		
				
				
				
				
				
			}
		});
		generacja.setBounds(340, 363, 163, 23);
		generacja.setEnabled(false);
		frame.getContentPane().add(generacja);
		
		JButton btnNewButton_2 = new JButton("Grayscale");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,Y;
				
				w=edytowany.getWidth();
				h=edytowany.getHeight();
				
				for(int i=0;i<w;i++) {
					for(int j=0;j<h;j++) {
						piksel = edytowany.getRGB(i, j);
						kolor = new Color(piksel,true);
						r = kolor.getRed();
						g = kolor.getGreen();
						b = kolor.getBlue();
						r=(int) (0.299*r);
						g=(int) (0.587*g);
						b=(int) (0.114*b);
						Y = r + g + b;
						kolor=new Color(Y,Y,Y);
						rgb=kolor.getRGB();
						edytowany.setRGB(i,j,rgb);
					}
				}
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
			}
		});
		btnNewButton_2.setBounds(10, 397, 100, 23);
		btnNewButton_2.setEnabled(false);
		frame.getContentPane().add(btnNewButton_2);
		
		
		JButton btnNewButton_4 = new JButton("Move image");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				JFrame okno = new JFrame();
				okno.setSize(350, 140);
				okno.setResizable(false);
				okno.setVisible(true);
				okno.setTitle("Przesuwaczka");
				Przesuwanko przesuwacz = new Przesuwanko();
				okno.setContentPane(przesuwacz);
				okno.setLocation(250,250);
				przesuwacz.ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int a = Integer.parseInt(przesuwacz.textfield.getText());
						int b = Integer.parseInt(przesuwacz.textfield2.getText());
						int h=obraz.getHeight();
						int w=obraz.getWidth();
					
						
							edytowany = new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_INT_ARGB);
							
							if ((a>=0) && (b>=0)) {
								for (int i=0; i<w-a; i++) {
									for (int j=b; j<h; j++) {
										Color pixels = new Color(obraz.getRGB(i,j));
										edytowany.setRGB(i+a, j-b, pixels.getRGB());
									}
								}
							} else if ((a<=0) && (b<=0)) {
								a = -(a);
								b = -(b);
								for (int i=a; i<w; i++) {
									for (int j=0; j<h-b; j++) {
										Color pixels = new Color(obraz.getRGB(i,j));
										edytowany.setRGB(i-a, j+b, pixels.getRGB());
									}
								}
							} else if ((a<0)&&(b>0)) {
								a = -(a);
								for (int i=a; i<w; i++) {
									for (int j=b; j<h; j++) {
										Color pixels = new Color(obraz.getRGB(i,j));
										edytowany.setRGB(i-a, j-b, pixels.getRGB());
									}
								}
							} else {
								b = -(b);
								for (int i=0; i<w-a; i++) {
									for (int j=0; j<h-a; j++) {
										Color pixels = new Color(obraz.getRGB(i,j));
										edytowany.setRGB(i+a, j+a, pixels.getRGB());
									}
								}
							}
							
							

							
						okno.dispose();
						lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
						
					}
				});
	
			}
		});
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setBounds(120, 397, 115, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnImageReflection = new JButton("Image reflection");
		btnImageReflection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					edytowany = ImageIO.read(new File(path));
					int rgb;
					int w=obraz.getWidth();
					int h=obraz.getHeight();
					for (int i=0; i<h; i++) {
						for (int j=0; j<w; j++) {
							rgb = obraz.getRGB(j, i);
							/*
							if(i%2==0) {Color jazda = randomColor();
							rgb=jazda.getRGB();}
							*/
							edytowany.setRGB((w-1)-j, i, rgb);
						}
					}
				} catch (IOException exth){
					exth.printStackTrace();
				}
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
			}
		});
		btnImageReflection.setBounds(240, 397, 164, 23);
		btnImageReflection.setEnabled(false);
		frame.getContentPane().add(btnImageReflection);
		
		JMenuBar mb = new JMenuBar();
		mb.setBounds(414, 397, 52, 21);
		frame.getContentPane().add(mb);
		
		JMenu filtry = new JMenu("Filters");
		filtry.setBorder(UIManager.getBorder("Button.border"));
		
		//filtry.setBounds(414, 397, 89, 23);
		filtry.setEnabled(false);
		mb.add(filtry);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Dolnoprzepustowy");
		filtry.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Program Completed");
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * [1 1 1]
				 * [1 1 1]
				 * [1 1 1]
				 * */
			
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								/*
								System.out.println("i= "+i);
								System.out.println("j= "+j);
								System.out.println("k= "+k);
								System.out.println("z= "+z);
								*/
							piksel = obraz.getRGB(i+z, j+k);
							//piksel = obraz.getRGB(2, 14);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							sr=(r*1)+sr;
							sg=(g*1)+sg;
							sb=(b*1)+sb;
							}
						}
						
						r=sr/(1+1+1 + 1+1+1 +1+1+1); // "jedynki" to skladowe maski
						g=sg/(9*1);
						b=sb/(9*1);
						/*
						System.out.println("r= "+r);
						System.out.println("g= "+g);
						System.out.println("b= "+b);
					*/
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
			
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
			}
		});
		
		JMenuItem mntmNewMenuItem1 = new JMenuItem("Gornoprzepustowy");
		filtry.add(mntmNewMenuItem1);
		
		mntmNewMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Program Completed");
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
				 *
				 * [-1 -1 -1]
				 * [-1  9 -1]
				 * [-1 -1 -1]
				 * */
			
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							//piksel = obraz.getRGB(2, 14);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							if(z==0&&k==0) {
								sr=(r*10)+sr;
								sg=(g*10)+sg;
								sb=(b*10)+sb;
							}else {
							sr=(r*(-1))+sr;
							sg=(g*(-1))+sg;
							sb=(b*(-1))+sb;
							}
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/((8*(-1))+10); // mamy 8 "minus jedynek" i jedna 9 jako skladowe maski filtra
						g=sg/((8*(-1))+10);
						b=sb/((8*(-1))+10);
						if(r<0) {r=-r;}
						if(g<0) {g=-g;}
						if(b<0) {b=-b;}
						if(r>255) {r=255;}
						if(g>255) {g=255;}
						if(b>255) {b=255;}
							
						
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
			
				lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
				
			}
		});
		
		JMenuItem mntmNewMenuItem2 = new JMenuItem("Gauss");
		filtry.add(mntmNewMenuItem2);
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
		
				 * [1 2 1]
				 * [2 4 2]
				 * [1 2 1]
				 * */
				int[][] maska = new int[3][3];
				maska[0][0] = 1;
				maska[0][1] = 2;
				maska[0][2] = 1;
				maska[1][0] = 2;
				maska[1][1] = 4;
				maska[1][2] = 2;
				maska[2][0] = 1;
				maska[2][1] = 2;
				maska[2][2] = 1;
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
								sr=(r*maska[z+1][k+1])+sr;
								sg=(g*maska[z+1][k+1])+sg;
								sb=(b*maska[z+1][k+1])+sb;
							
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/(1+2+1+2+4+2+1+2+1); // 
						g=sg/(1+2+1+2+4+2+1+2+1);
						b=sb/(1+2+1+2+4+2+1+2+1);
						
							
						
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
				
				
				
				
				
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
						
	}
});

		JMenuItem mntmNewMenuItem3 = new JMenuItem("Krawedzie poziome");
		filtry.add(mntmNewMenuItem3);
		mntmNewMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
		
				 * [ 0 0 0]
				 * [-1 1 0]
				 * [ 0 0 0]
				 * */
				int[][] maska = new int[3][3];
				maska[0][0] = 0;
				maska[0][1] = 0;
				maska[0][2] = 0;
				maska[1][0] = -1;
				maska[1][1] = 1;
				maska[1][2] = 0;
				maska[2][0] = 0;
				maska[2][1] = 0;
				maska[2][2] = 0;
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
								sr=(r*maska[z+1][k+1])+sr;
								sg=(g*maska[z+1][k+1])+sg;
								sb=(b*maska[z+1][k+1])+sb;
							
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/1; // jeden bo suma wag wynosi zero (wedlug wikipedii)
						g=sg/1;
						b=sb/1;
						
						//gdy skladowa wchodzi ujemna podobno nalezy zastosowac wartosc bezwzgledna
						if(r<0) {r=-r;}
						if(g<0) {g=-g;}
						if(b<0) {b=-b;}
						/*
						System.out.println("r= "+r);
						System.out.println("g= "+g);
						System.out.println("b= "+b);	
						*/
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
				
			
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
						
			
			
			
			}	
		});
		
		JMenuItem mntmNewMenuItem4 = new JMenuItem("Krawedzie pionowe");
		filtry.add(mntmNewMenuItem4);
		mntmNewMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
		
				 * [ 0 -1 0]
				 * [ 0  1 0]
				 * [ 0  0 0]
				 * */
				int[][] maska = new int[3][3];
				maska[0][0] = 0;
				maska[0][1] = -1;
				maska[0][2] = 0;
				maska[1][0] = 0;
				maska[1][1] = 1;
				maska[1][2] = 0;
				maska[2][0] = 0;
				maska[2][1] = 0;
				maska[2][2] = 0;
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
								sr=(r*maska[z+1][k+1])+sr;
								sg=(g*maska[z+1][k+1])+sg;
								sb=(b*maska[z+1][k+1])+sb;
							
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/1; // jeden bo suma wag wynosi zero (wedlug wikipedii)
						g=sg/1;
						b=sb/1;
						
						//gdy skladowa wchodzi ujemna podobno nalezy zastosowac wartosc bezwzgledna
						if(r<0) {r=-r;}
						if(g<0) {g=-g;}
						if(b<0) {b=-b;}
						/*
						System.out.println("r= "+r);
						System.out.println("g= "+g);
						System.out.println("b= "+b);	
						*/
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
				
			
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
			
		
			}	
		});

		JMenuItem mntmNewMenuItem5 = new JMenuItem("Krawedzie ukosne");
		filtry.add(mntmNewMenuItem5);
		mntmNewMenuItem5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
				 * [ -1 0 0]
				 * [ 0  1 0]
				 * [ 0  0 0]
				 * */
				int[][] maska = new int[3][3];
				maska[0][0] = -1;
				maska[0][1] = 0;
				maska[0][2] = 0;
				maska[1][0] = 0;
				maska[1][1] = 1;
				maska[1][2] = 0;
				maska[2][0] = 0;
				maska[2][1] = 0;
				maska[2][2] = 0;
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
								sr=(r*maska[z+1][k+1])+sr;
								sg=(g*maska[z+1][k+1])+sg;
								sb=(b*maska[z+1][k+1])+sb;
							
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/1; // jeden bo suma wag wynosi zero (wedlug wikipedii)
						g=sg/1;
						b=sb/1;
						
						//gdy skladowa wchodzi ujemna podobno nalezy zastosowac wartosc bezwzgledna
						if(r<0) {r=-r;}
						if(g<0) {g=-g;}
						if(b<0) {b=-b;}
						/*
						System.out.println("r= "+r);
						System.out.println("g= "+g);
						System.out.println("b= "+b);	
						*/
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
				
			
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
			
				
			}	
		});
		
		JMenuItem mntmNewMenuItem6 = new JMenuItem("Laplace'a");
		filtry.add(mntmNewMenuItem6);
		mntmNewMenuItem6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color kolor;
				edytowany = null;
				try {
					edytowany = ImageIO.read(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w,h,piksel,r,g,b,rgb,sr=0,sb=0,sg=0;
				
				w=obraz.getWidth();
				h=obraz.getHeight();
				//i=2 j=2 dlatego ze nie filtruje pikseli krancowych
				/*
				 * stosowany filtr:
				 * 
				 * [
					-1	-1	-1
					-1	8	-1
					-1	-1	-1
					]
				 * */
				int[][] maska = new int[3][3];
				maska[0][0] = -1;
				maska[0][1] = -1;
				maska[0][2] = -1;
				maska[1][0] = -1;
				maska[1][1] = 8;
				maska[1][2] = -1;
				maska[2][0] = -1;
				maska[2][1] = -1;
				maska[2][2] = -1;
				for(int i=2;i<(w-2);i++) {
					for(int j=2;j<(h-2);j++) {
						sr=0;
						sg=0;
						sb=0;
						for(int k=-1;k<2;k++) {
							for(int z=-1;z<2;z++) {
								
							piksel = obraz.getRGB(i+z, j+k);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
								sr=(r*maska[z+1][k+1])+sr;
								sg=(g*maska[z+1][k+1])+sg;
								sb=(b*maska[z+1][k+1])+sb;
							
							
							}
						}
						/*
						System.out.println("sr= "+sr);
						System.out.println("sg= "+sg);
						System.out.println("sb= "+sb);
						*/
						r=sr/1; // jeden bo suma wag wynosi zero (wedlug wikipedii)
						g=sg/1;
						b=sb/1;
						
						if(r<0) {r=-r;}
						if(g<0) {g=-g;}
						if(b<0) {b=-b;}
						if(r>255) {r=255;}
						if(g>255) {g=255;}
						if(b>255) {b=255;}
						
						
						kolor=new Color(r,g,b);
						rgb=kolor.getRGB();
						edytowany.setRGB(i, j, rgb);
					}
				}
				
			
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, edytowany.SCALE_SMOOTH)));
			
				
				
				
			}	
		});
		
		JButton btnNewButton_5 = new JButton("Compare Images");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				int dajlog = fc.showOpenDialog(null);
				if (dajlog == JFileChooser.APPROVE_OPTION) {
					File do_porownania = fc.getSelectedFile();
					path1 = do_porownania.getAbsolutePath();
				}
				try {
					edytowany = ImageIO.read(new File(path));
					int w = edytowany.getWidth();
					int h = edytowany.getHeight();
					int r,g,b,b1,g1,r1;
					porownywany_obr = ImageIO.read(new File(path1));
					for (int i=0; i<w; i++) {
						for (int j =0; j<h; j++) {
							Color pikesel_normalny = new Color(edytowany.getRGB(i, j));
							r = pikesel_normalny.getRed();
							g = pikesel_normalny.getGreen();
							b = pikesel_normalny.getBlue();
							Color piksel_edytowany = new Color(porownywany_obr.getRGB(i, j));
							r1 = piksel_edytowany.getRed();
							g1 = piksel_edytowany.getGreen();
							b1 = piksel_edytowany.getBlue();
			
							if ( (g1 == g) && (r1 == r) &&  (b1 == b) ) {
								porownywany_obr.setRGB(i, j, Color.black.getRGB());
							} else {
								porownywany_obr.setRGB(i, j, porownywany_obr.getRGB(i, j));
							}
						}
					}
					lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(porownywany_obr).getImage().getScaledInstance(300, 330, obraz.SCALE_SMOOTH)));
					
				} catch (IOException exp1) {
					exp1.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setBounds(541, 408, 158, 23);
		btnNewButton_5.setEnabled(false);
		frame.getContentPane().add(btnNewButton_5);
		
		
		JButton btnNewButton_7 = new JButton("Histogram equalization");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					edytowany = ImageIO.read(new File(path));
					Histogram.equalizacja(); // tworzy histogram z nowymi poprawionymi wartosciami kanalow R/G/B
					Histogram.Histogram_create("Histogram - wyrownany");
					lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(edytowany).getImage().getScaledInstance(300, 330, obraz.SCALE_SMOOTH)));
					
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_7.setBounds(650, 262, 174, 23);
		frame.getContentPane().add(btnNewButton_7);
		btnNewButton_7.setEnabled(false);
		
		
		
		JButton btnNewButton_6 = new JButton("Histogram");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					edytowany = ImageIO.read(new File(path));
					btnNewButton_7.setEnabled(true);
					Histogram.Histogram_create("Histogram");
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnNewButton_6.setBounds(650, 228, 174, 23);
		frame.getContentPane().add(btnNewButton_6);
		btnNewButton_6.setEnabled(false);
		
		
		
		
		JButton btnSelectImage = new JButton("Select Image");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				final JFileChooser fc = new JFileChooser();
				int returnval = fc.showOpenDialog(null);
				
				if(returnval==JFileChooser.APPROVE_OPTION) 
				{
					
					try {
						obraz = ImageIO.read(new File(fc.getSelectedFile().toString()));
					    path = fc.getSelectedFile().getAbsolutePath();
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lblNewLabel.setIcon(new ImageIcon(new ImageIcon(obraz).getImage().getScaledInstance(300, 330, obraz.SCALE_SMOOTH)));
					btnNewButton_1.setEnabled(true);
					generacja.setEnabled(true);
					binarny_slider.setEnabled(true);
					slider_multi.setEnabled(true);
					slider_multi1.setEnabled(true);
					btnNewButton_3.setEnabled(true);
					btnNewButton.setEnabled(true);
					btnGreen.setEnabled(true);
					btnBlue.setEnabled(true);
					btnNewButton_2.setEnabled(true);
					btnImageReflection.setEnabled(true);
					btnNewButton_4.setEnabled(true);
					filtry.setEnabled(true);
					btnNewButton_5.setEnabled(true);
					btnNewButton_6.setEnabled(true);
				}
				
				
				
			}
		});
		btnSelectImage.setBounds(709, 374, 115, 23);
		frame.getContentPane().add(btnSelectImage);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
