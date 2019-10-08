package org.brandonli.main;

import java.awt.Toolkit;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Graph(final String title, final String lineName, final File file) {

	    super(title);
	    
	    String [] titles = GetData.getNames(file);
	    double [][] values = GetData.getValues(file);
	    
	    final XYSeries series = new XYSeries(lineName);
	    
	    for (int i = 0; i < values.length; i++) {
	    	
	    	series.add(values[i][0], values[i][1]);
	    	
	    }
	    
	    final XYSeriesCollection data = new XYSeriesCollection(series);
	    final JFreeChart chart = ChartFactory.createXYLineChart(
	        title,
	        titles[0], 
	        titles[1], 
	        data,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );

	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(1024, 576));
	    setContentPane(chartPanel);
	    
	    setTitle(title);
	    setIconImage(Toolkit.getDefaultToolkit().getImage(ChooseFile.class.getResource("/resources/icon.png")));

	}

	public static void start(String title, String lineName, File file) {

	    final Graph demo = new Graph(title, lineName, file);
	    demo.pack();
	    demo.setVisible(true);

	}

}
