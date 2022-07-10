package pl.com.tt.tbi.model.pixelmap;

import java.io.Serializable;

public class Pixel implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean isFilled;
	private boolean isRestricted;
	
	public boolean isFilled() {
		return isFilled;
	}
	
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	public boolean isRestricted() {
		return isRestricted;
	}
	
	public void setRestricted(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}
	
	@Override
	public String toString() {
		return (isFilled ? "Filled" : "Empty") + (isRestricted ? " RESTRICTED" : ""); 
	}
	
}
