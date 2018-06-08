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
import Products.Product;

public class savePerson {
	private ObjectInputStream EmpRead;
	private FileInputStream EmpFileInput;

	private ObjectOutputStream EmpWrite;
	private FileOutputStream EmpFileOutput;

	private ObjectOutputStream PrWrite;
	private FileOutputStream PrFileOutput;
	private ObjectInputStream PrRead;
	private FileInputStream PrFileInput;

	private DataOutputStream sizeOutputStream;
	private DataInputStream sizeInputStream;

	public int sizeOfEmployees = 0;
	private int sizeOfProducts = 0;
	private int sizeOfClients = 0;

	public void initializeSize() {
		try {
			FileInputStream k = new FileInputStream("Size.txt");
			sizeInputStream = new DataInputStream(k);

			sizeOfEmployees = sizeInputStream.readInt();
			sizeInputStream.readChar(); // reads the \n
			sizeOfProducts = sizeInputStream.readInt();
			sizeInputStream.readChar(); // reads the \n
			sizeOfClients = sizeInputStream.readInt();
			sizeInputStream.readChar(); // reads the \n
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

	public void saveEmployees() {
		for (Employee E : Driver.Website.HE)
			try {
				if (E instanceof HourlyEmployee)
					EmpWrite.writeObject((HourlyEmployee) E);
				else
					EmpWrite.writeObject((PartTimeEmployee) E);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		sizeOfEmployees = Driver.Website.HE.size();
		try {
			sizeOutputStream.writeInt(Driver.Website.HE.size());
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(Driver.Website.Clients.size());
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readPerson() {
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
			sizeOfProducts = sizeInputStream.readInt();
			sizeInputStream.readChar(); // reads the \n
		} catch (IOException e1) {
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

	public void openProductsFile() {
		try {
			PrFileOutput = new FileOutputStream("Products");
			PrWrite = new ObjectOutputStream(PrFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveProducts() {
		for (Product p : Driver.Website.Pr)
			try {
				PrWrite.writeObject(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public void readProducts() {
		Object e = null;
		Path p = Paths.get("Products"), p2 = Paths.get("Size.txt");
		if (Files.notExists(p) || Files.notExists(p2)) {
			return;
		}
		try {
			PrFileInput = new FileInputStream("Products");
			PrRead = new ObjectInputStream(PrFileInput);
		} catch (IOException e1) {
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

	public void closeProductsFile() {
		try {
			if (PrRead != null) {
				PrRead.close();
				PrFileInput.close();
			}
			if (PrWrite != null) {
				PrWrite.flush();
				PrWrite.close();
				PrFileOutput.close();
			}
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

	public void read() {
		readPerson();
		readProducts();
		closeAllFiles();
	}

	public void save() {
		openAllFiles();
		saveEmployees();
		saveProducts();
		closeAllFiles();
	}

	public void openAllFiles() {
		initializeSize();
		openSizeFile();
		openEmployeeFileOutputMode();
	}

	public void closeAllFiles() {
		closeSizeFile();
		closeEmployeeFile();
	}

}
