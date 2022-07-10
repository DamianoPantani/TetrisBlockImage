package pl.com.tt.tbi.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTetrisTable extends JTable {

	private static final long serialVersionUID = 1L;
	private static final int COLUMN_WIDTH = 20;
	private DefaultTableModel bitmapTableModel;
	
	public JTetrisTable(int width, int height) {
		super();
		bitmapTableModel = new DefaultTableModel(null, new String[width]);
		for (int i = 0; i < height; i++) {
			bitmapTableModel.addRow(new Object[] {});
		}
		
		setModel(bitmapTableModel);
		setTableHeader(null);
		setCellSelectionEnabled(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
		setDefaultEditor(Object.class, null);
		setCellsWidth();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				for (int x: target.getSelectedColumns()){
					for (int y: target.getSelectedRows()){
						setPixelAt(x, y);
					}
				}
				target.clearSelection();
			}
		});
	}

	private void setCellsWidth() {
		for (int i = 0; i < getColumnCount(); i++) {
	        getColumnModel().getColumn(i).setMaxWidth(COLUMN_WIDTH);
	    }
	}

	public void addColumns(int count) {

	}
	

	public void setColumnsCount(int width) {
		bitmapTableModel.setColumnCount(width);
	    setCellsWidth();
	}

	public void setRowsCount(int height) {
		int difference = height - bitmapTableModel.getRowCount();
		if (difference > 0) {
			for (int i = 0; i < difference; i++) {
				bitmapTableModel.addRow(new Object[] {});
			}
		} else {
			for (int i = 0; i > difference; i--) {
				bitmapTableModel.removeRow(bitmapTableModel.getRowCount() - 1);
			}
		}
	}

	public void setPixelAt(int x, int y) {
		bitmapTableModel.setValueAt(
				bitmapTableModel.getValueAt(y, x) == null ? "\u2588\u2588" : null,
				y, x);
	}

	public void clearAllPixels() {
		int rowCount = getRowCount();
		setRowsCount(0);
		setRowsCount(rowCount);
	}
	
}
