package pl.com.tt.tbi.gui.window.popup;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public abstract class OverwritingFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	@Override
	public void approveSelection() {
		if (overwriteChoosen()) {
			super.approveSelection();
		}
	}

	private boolean overwriteChoosen() {
		File selectedFile = getSelectedFile();
		if (selectedFile.exists() && getDialogType() == SAVE_DIALOG) {
			int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
				case JOptionPane.YES_OPTION:
					return true;
				case JOptionPane.NO_OPTION:
				case JOptionPane.CLOSED_OPTION:
				case JOptionPane.CANCEL_OPTION:
					return false;
			}
		}
		return true;
	}

}
