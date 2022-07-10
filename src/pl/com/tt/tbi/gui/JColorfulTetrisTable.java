package pl.com.tt.tbi.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class JColorfulTetrisTable extends JTetrisTable {

	private static final long serialVersionUID = 1L;
	
	List<List<Color>> colors = new ArrayList<>();
	
	public JColorfulTetrisTable(int width, int height) {
		super(width, height);

		for (int i=0; i<height; i++){
			Color[] row = new Color[width];
			Arrays.fill(row, Color.BLACK);
			colors.add(Arrays.asList(row));
		}
		
		setShowGrid(false);
		setDefaultRenderer(Object.class, new CustomTableRenderer());
	}

	public void setPixelAt(int x, int y, Color color) {
		setPixelAt(x, y);
		colors.get(y).set(x, color);
	}

	public void addColumns(int count) {
		throw new IllegalAccessError("Method is not accessible for class "+this.getClass().getSimpleName());
	}

	public void addRows(int difference) {
		throw new IllegalAccessError("Method is not accessible for class "+this.getClass().getSimpleName());
	}
	
	private class CustomTableRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column){
            Component c = super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            c.setForeground(colors.get(row).get(column));
            return c;
        }
	}
	
}
