package lab1;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class Histogram extends JFrame {
	
	static int w = window.edytowany.getWidth();
	static int h = window.edytowany.getHeight();
	Color piksel;
	int[] r = new int [256],g = new int [256],b = new int [256];
	
	public Histogram(String nazwa) {
		super(nazwa);
		ChartFactory.setChartTheme(StandardChartTheme.createDarknessTheme());
		IntervalXYDataset dataset = piksele();
		JFreeChart wykres1 = ChartFactory.createHistogram(nazwa, null, null, dataset, PlotOrientation.VERTICAL, false, false, false);
		wykres1.getPlot().setBackgroundPaint( Color.BLUE );
		ChartPanel wykres = new ChartPanel(wykres1);
		setContentPane(wykres);
	}

	private IntervalXYDataset piksele() {
		final XYSeries RGB = new XYSeries("RGB");
		
	
		
		for (int i=0; i<w; i++)
			for (int j=0; j<h; j++) {
				piksel = new Color(window.edytowany.getRGB(i, j));
				r[piksel.getRed()] = r[piksel.getRed()] + 1; 
				g[piksel.getGreen()] = g[piksel.getGreen()] + 1;
				b[piksel.getBlue()] = b[piksel.getBlue()] + 1;
			}
		
		for (int k=0; k<256; k++) {RGB.add(k, r[k]);RGB.add(k, g[k]);RGB.add(k, b[k]);}
		
		final XYSeriesCollection piksele = new XYSeriesCollection(RGB);
		return piksele;
	}
	
	public static void Histogram_create(String nazwa) {
		Histogram window = new Histogram(nazwa);
		window.setSize(700,400);
		window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		window.setVisible(true);
	}
	
	public static int[] nowewartosci(int[] kanal_kolor) {
		int[] kanal_kolor_update = kanal_kolor;
		float tmp = (float) 255.0 / (w * h);
		int suma = 0,wartosc;
		for (int i=0; i<256; i++) {
			suma = suma + kanal_kolor[i];
			wartosc = (int) (suma * tmp);
			if (wartosc > 255)
			{
				wartosc = 255;
			}
			kanal_kolor_update[i] = wartosc;
		}
		return kanal_kolor_update;
	}
	
	public static void equalizacja() {
		int[] czerwony = new int [256];
		int[] zielony = new int [256];
		int[] niebieski = new int [256];
		Color piksel;
		for (int i=0; i<w; i++) {
			for (int j=0; j<h; j++) {
				 piksel = new Color(window.edytowany.getRGB(i, j));
				 czerwony[piksel.getRed()] = czerwony[piksel.getRed()] + 1; 
				 zielony[piksel.getGreen()] = zielony[piksel.getGreen()] + 1;
				 niebieski[piksel.getBlue()] = niebieski[piksel.getBlue()] + 1;
			}
	    }
		
		
		
		czerwony = nowewartosci(czerwony);zielony = nowewartosci(zielony);niebieski = nowewartosci(niebieski);
		for (int i=0; i<w; i++) {
			for (int j=0; j<h; j++) {
				piksel = new Color(window.edytowany.getRGB(i, j));
				int r1 = czerwony[piksel.getRed()];
				int g2 = zielony[piksel.getGreen()];
				int b2 = niebieski[piksel.getBlue()];
				Color nowy = new Color(r1, g2, b2);
				window.edytowany.setRGB(i, j, nowy.getRGB());
			                         }
								}
		}
	
}