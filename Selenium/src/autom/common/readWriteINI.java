package autom.common;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class readWriteINI<T> {
	protected String sFile="c:\\temp\\testdata.ini";
	protected Wini ini;
	protected String Category;
	protected String Key;
	protected T sValue;
	
	public readWriteINI() {
		try {
			ini = new Wini(new File(sFile));
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public readWriteINI(String sFileNamePath) {
		try {
			Path path = Paths.get(sFileNamePath);
			if(Files.exists(path)) {
				ini = new Wini(new File(sFileNamePath));
				sFile=sFileNamePath;
			}
		} catch (InvalidFileFormatException|FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setValue(T svalue) {
		sValue = svalue;
	}	
	public readWriteINI(String Category, String Key) {
		try {
			Path path = Paths.get(sFile);
			if(Files.exists(path)) {
				ini = new Wini(new File(sFile));
				this.Category=Category;
				this.Key=Key;
			}
		} catch (InvalidFileFormatException|FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void WriteINI() {
		try {
			ini.add(Category,Key,sValue);
			ini.store();
		} catch (InvalidFileFormatException|FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


