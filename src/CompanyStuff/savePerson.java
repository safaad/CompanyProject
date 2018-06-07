package CompanyStuff;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Drivers.Driver;
import Individuals.*;

public class savePerson {
	private ObjectInputStream EmpRead;
	private FileInputStream EmpFileInput;

	private ObjectOutputStream EmpWrite;
	private FileOutputStream EmpFileOutput;

	private DataInputStream LogsRead;
	private FileInputStream LogsFileIn;
	private DataOutputStream LogsWrite;
	private FileOutputStream LogsFileOut;

	private DataOutputStream sizeOutputStream;
	private DataInputStream sizeInputStream;

	public int sizeOfEmployees = 0;

	public void openLogsFile() {
		try {
			LogsFileOut = new FileOutputStream("Log.txt");
			LogsWrite = new DataOutputStream(LogsFileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readLogs() {
		try {
			LogsFileIn = new FileInputStream("Log.txt");
			LogsRead = new DataInputStream(LogsFileIn);
			
			//read logs later hehe xd
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeLogsFile() {
		try {
			LogsRead.close();
			LogsWrite.flush();
			LogsWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openEmployeeFileOutputMode() {
		try {
			EmpFileOutput = new FileOutputStream("Employee");
			EmpWrite = new ObjectOutputStream(EmpFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void openSizeFile() {
		try {
			FileOutputStream k = new FileOutputStream("Size.txt");
			sizeOutputStream = new DataOutputStream(k);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSizeFile() {
		try {
			if (sizeOutputStream != null) {
				sizeOutputStream.flush();
				sizeOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveEmployees() {
		for (Employee E : Driver.Website.HE) {
			try {
				if (E instanceof HourlyEmployee)
					EmpWrite.writeObject((HourlyEmployee) E);
				else
					EmpWrite.writeObject((PartTimeEmployee) E);
				// LogsWrite.writeUTF("Saved 1 employee successfully");
				// LogsWrite.writeInt(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		sizeOfEmployees = Driver.Website.HE.size();
		try {
			sizeOutputStream.writeInt(Driver.Website.HE.size());
			sizeOutputStream.writeChar('\n');
			// sizeOfEmployeez.writeInt(Driver.Website.Clients.size());
			// sizeOutputStream.writeChar('\n');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readEmployees() {
		Object e = null;
		Path p = Paths.get("Employee"), p2 = Paths.get("Size.txt");
		if (Files.notExists(p) || Files.notExists(p2)) {
			return;
		}
		try {
			EmpFileInput = new FileInputStream("Employee");
			EmpRead = new ObjectInputStream(EmpFileInput);

			FileInputStream k = new FileInputStream("Size.txt");
			sizeInputStream = new DataInputStream(k);
			sizeOfEmployees = sizeInputStream.readInt();
			sizeInputStream.readChar();
		} catch (IOException e1) {
			//e1.printStackTrace();
			return;
		}
		for (int i = sizeOfEmployees; i > 0; i--) {
			try {
				e = EmpRead.readObject();

				if (e instanceof HourlyEmployee) {
					Driver.Website.HE.add((HourlyEmployee) e);
				} else {
					Driver.Website.HE.add((PartTimeEmployee) e);
				}

			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void closeEmployeeFile() {
		try {
			if (EmpRead != null) {
				EmpRead.close();
				EmpFileInput.close();
			}
			if (EmpWrite != null) {
				EmpWrite.flush();
				EmpWrite.close();
				EmpFileOutput.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		readEmployees();
		closeAllFiles();
	}
	
	public void save() {
		openAllFiles();
		saveEmployees();
		closeAllFiles();
	}

	public void openAllFiles() {
		// openLogsFile();
		openSizeFile();
		openEmployeeFileOutputMode();
	}

	public void closeAllFiles() {
		// closeLogsFile();
		closeSizeFile();
		closeEmployeeFile();
	}

}
