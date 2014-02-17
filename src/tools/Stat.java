package tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Stat {

	public static void writeStat() {
		if (Config.stat)
			try {
				if (Config.verbose >= 1) {
					System.out.println("Generating statisques file : stat.txt");
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"stat.txt"));
				writer.write("Generation , nb of trees \n");
				for (int i = 0; i < Generator.mainList.size(); i++) {
					writer.write(i + " , " + Generator.mainList.get(i).size()
							+ "\n");
				}
				writer.write("Size of tree , nb of trees \n");
				ArrayList<Integer> sizes = new ArrayList<Integer>();
				for (int k : Generator.table.keySet()) {
					sizes.add(k);
				}
				Collections.sort(sizes);
				for (int k : sizes)
					writer.write(k + " , " + Generator.table.get(k).size()
							+ "\n");

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
