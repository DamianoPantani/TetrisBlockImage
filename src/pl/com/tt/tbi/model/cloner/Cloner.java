package pl.com.tt.tbi.model.cloner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Cloner {

	@SuppressWarnings("unchecked")
	public static <T extends Object> T clone(T object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			bos.close();
		byte[] byteData = bos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		return (T) new ObjectInputStream(bais).readObject();
		} catch (Exception e) {
			System.err.println(String.format("Could not clone %s. %s. Returning original object", object.getClass().getSimpleName(), e.getLocalizedMessage()));
			e.printStackTrace();
			return object;
		}
	}
	
}
