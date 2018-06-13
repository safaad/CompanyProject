package CompanyStuff;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Products.Transaction;
import Drivers.Driver;
import Individuals.*;
import Products.Product;

public class files {
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

	private ObjectInputStream CRead;
	private FileInputStream CFileInput;
	private ObjectOutputStream CWrite;
	private FileOutputStream CFileOutput;
	
	private ObjectInputStream TRead;
	private FileInputStream TFileInput;
	private ObjectOutputStream TWrite;
	private FileOutputStream TFileOutput;

	private int sizeOfEmployees = 0;
	private int sizeOfProducts = 0;
	private int sizeOfClients = 0;
	private int sizeOfTransactions = 0;

	public boolean initializeSize() {
		Path p = Paths.get("Size.txt");
		if (Files.notExists(p))
			return false;
		try {
			FileInputStream k = new FileInputStream("Size.txt");
			sizeInputStream = new DataInputStream(k);

			sizeOfEmployees = sizeInputStream.readInt(); // employees
			sizeInputStream.readChar(); // reads the \n
			sizeOfClients = sizeInputStream.readInt(); // clients
			sizeInputStream.readChar(); // reads the \n
			sizeOfProducts = sizeInputStream.readInt(); // products
			sizeInputStream.readChar(); // reads the \n
			sizeOfTransactions = sizeInputStream.readInt(); // Transactions
			sizeInputStream.readChar();
			Transaction.setSerial(sizeInputStream.readInt()); // Setting Transaction IDs to the on in the file
			sizeInputStream.readChar(); // reads the \n
			if (sizeOfEmployees == 0 && sizeOfClients == 0 && sizeOfProducts == 0) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void openEmployeeFileOutputMode() {
		try {
			EmpFileOutput = new FileOutputStream("Employees");
			EmpWrite = new ObjectOutputStream(EmpFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void savePerson() {
		for (Employee E : Driver.Website.HE)
			try {
				FileOutputStream k = new FileOutputStream("Size.txt");
				sizeOutputStream = new DataOutputStream(k);
				if (E instanceof HourlyEmployee)
					EmpWrite.writeObject((HourlyEmployee) E);
				else if (E instanceof PartTimeEmployee)
					EmpWrite.writeObject((PartTimeEmployee) E);
				else
					EmpWrite.writeObject(E);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		for (Employee E : Driver.Website.Admins)
			try {
				EmpWrite.writeObject(E);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		for (Client c : Driver.Website.Clients)
			try {
				CWrite.writeObject(c);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		sizeOfEmployees = Driver.Website.HE.size() + Driver.Website.Admins.size();
		sizeOfClients = Driver.Website.Clients.size();
		try {
			sizeOutputStream.writeInt(sizeOfEmployees);
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(sizeOfClients);
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readPerson() {
		Object e = null;
		Path p = Paths.get("Employees"), p2 = Paths.get("Size.txt"), p3 = Paths.get("Products"),
				p4 = Paths.get("Clients");
		if (Files.notExists(p) || Files.notExists(p2) || Files.notExists(p3) || Files.notExists(p4)) {
			return;
		}
		try {
			EmpFileInput = new FileInputStream("Employees");
			EmpRead = new ObjectInputStream(EmpFileInput);
			CFileInput = new FileInputStream("Clients");
			CRead = new ObjectInputStream(CFileInput);
		} catch (IOException e1) {
			return;
		}
		for (int i = sizeOfEmployees; i > 0; i--) {
			try {
				e = EmpRead.readObject();
				if (((Employee) e).getAdmin()) {
					Driver.Website.Admins.add((Employee) e);
				} else if (e instanceof HourlyEmployee) {

				}
				else if (e instanceof HourlyEmployee) {
					Driver.Website.HE.add((HourlyEmployee) e);
				} else if (e instanceof PartTimeEmployee)
					Driver.Website.HE.add((PartTimeEmployee) e);
				
			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}
		for (int i = sizeOfClients; i > 0; i--) {
			try {
				e = CRead.readObject();
				Driver.Website.Clients.add((Client) e);
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
		try {
			sizeOfProducts = Driver.Website.Pr.size();
			sizeOutputStream.writeInt(sizeOfProducts);
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveTrxs() {
		for (Transaction t : Driver.Website.Transactions)
			try {
				TWrite.writeObject(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		sizeOfTransactions = Driver.Website.Transactions.size();
		try {
			sizeOutputStream.writeInt(sizeOfTransactions);
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(Transaction.getSerial());
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readTrxs() {
		Object t = null;
		Path p = Paths.get("Transactions");
		if (Files.notExists(p))
			return;
		try {
			TFileInput = new FileInputStream("Transactions");
			TRead = new ObjectInputStream(TFileInput);
		} catch (IOException e1) {
			return;
		}
		for (int i = sizeOfTransactions; i > 0; i--)
			try {
				t = TRead.readObject();
				Driver.Website.Transactions.add((Transaction) t);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
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
		for (int i = sizeOfProducts; i > 0; i--) {
			try {
				e = (Product) PrRead.readObject();
				Driver.Website.Pr.add((Product) e);
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
			// The file Size.txt will always start with 0\n0\n0\n
			sizeOutputStream.writeInt(0); // 0 employees
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 clients
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 products
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 Transactions
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSizeFile() {
		try {
			if (sizeInputStream != null) {
				sizeInputStream.close();
			}
			if (sizeOutputStream != null) {
				sizeOutputStream.flush();
				sizeOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openClientsFile() {
		try {
			CFileOutput = new FileOutputStream("Clients");
			CWrite = new ObjectOutputStream(CFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeClientsFile() {
		try {
			if (CRead != null) {
				CRead.close();
				CFileInput.close();
			}
			if (CWrite != null) {
				CFileOutput.close();
				CWrite.flush();
				CWrite.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openTransactionsFile() {
		try {
			TFileOutput = new FileOutputStream("Transactions");
			TWrite = new ObjectOutputStream(TFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeTransactionsFile() {
		try {
			if (TRead != null) {
				TRead.close();
				TFileInput.close();
			}
			if (TWrite != null) {
				TFileOutput.close();
				TWrite.flush();
				TWrite.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		if (!initializeSize()) { // get the size of lists from the size.txt
			closeAllFiles();
			return;
		}
		readPerson(); // read persons to the list
		readProducts(); // read products to the list
		readTrxs();
		closeAllFiles(); // close the files and continue on with the program
	}

	public void save() {
		openAllFiles();
		savePerson();
		saveProducts();
		saveTrxs();
		closeAllFiles();
	}

	public void openAllFiles() {
		openSizeFile();
		openEmployeeFileOutputMode();
		openProductsFile();
		openClientsFile();
		openTransactionsFile();
	}

	public void closeAllFiles() {
		closeSizeFile();
		closeEmployeeFile();
		closeProductsFile();
		closeClientsFile();
		closeTransactionsFile();
	}

}
