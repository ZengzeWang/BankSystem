
import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;

public class BankControl {
	public String Acc1;
	Scanner sc = new Scanner(System.in);

	public BankControl() {
		try {
			Interface();
		}
		catch( Exception e)
		{ 
			System.out.println("Wrong! Invalid input ");
		}
		finally {
			BankControl b = new BankControl();
		}
	}

	/**
	 * method Interface () This method is the initial interface leading to 3 main
	 * function.
	 * @throws ParseException 
	 */

	public void Interface() throws ParseException {
		System.out.println("Please select service, input 1 or 2.");
		System.out.println("<1> Create your new account");
		System.out.println("<2> Log in your account");
		int op1 = sc.nextInt();
		switch (op1) {
		case (1):
			CreateAccount();
			break;
		case (2):
			Login1();
			break;
		default:
			System.out.println("Invalid input");
			Interface();
			break;
		}
	}

	/**
	 * method CreateAccount Use this method to create an account.
	 * @throws ParseException 
	 * 
	 */
	public void CreateAccount() throws ParseException {
		String date;
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("YYYY-MM-dd");// format of date
		date = yyyymmdd.format(new Date());
		String accountnum = Integer.toString((int) ((Math.random() * 9 + 1) * 1000));// generate account number
		File F = new File("check.txt");
		System.out.println("Please enter your name");
		String c = sc.nextLine();
		String name = sc.nextLine();
		if (readFile(F).contains(name)) // refer to credit system
		{
			System.out.println("You cannot open an account because of bad credit");
			Interface();
		} else {
			String date0 = "NULL";// initialize date
			System.out.println("Please enter your address");
			String addr = sc.nextLine();
			System.out.println("Please enter your birthday(in the form of YYYY-MM-dd)");
			String birth = sc.nextLine();
			String[] str1 = date.split("\\-");// split the date
			String[] str2 = birth.split("\\-");
			if (Integer.parseInt(str1[0]) - Integer.parseInt(str2[0]) <= 16) // calculate age
			{
				System.out.println("You are under 16, only allowed to open junior account");
				System.out.println("Please input 2 to confirm");
				System.out.println("<2> Junior Account");
			}
			if (Integer.parseInt(str1[0]) - Integer.parseInt(str2[0]) > 16) {
				System.out.println("Please select account");
				System.out.println("<1> Saver Account");
				System.out.println("<2> Junior Account");
				System.out.println("<3> Current Account");
			}

			int type = sc.nextInt();
			switch (type) {
			case 1:
				System.out.println("You can withdraw your money after 7 days when you deposit your money");
				break;
			case 2:
				System.out.println("You have successfully open a junior account");
				break;
			case 3:
				System.out.println("This is a current account, you have a 500 dollars overdraft limit");
				break;
			default:
				System.out.println("Wrong input!");
				Interface();
				break;
			}
			String pin = Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
			Account acc = new Account(type, name, addr, birth, pin, accountnum, 0, 0, date0, date0, true);
			File f = new File(accountnum + ".txt");
			if (f.exists())
				System.out.println("This account number has already existed!");
			else {
				writeFile(acc);// store information
				System.out.println("Please remember! Your account number is  " + accountnum);
				System.out.println("Your initial pin is  " + pin);
				Interface();
			}
		}
	}

	/**
	 * Method setAcc() This method set content of account into an arraylist in the
	 * memory with the help of content read.
	 * 
	 * @param list
	 * @return acct
	 */
	public Account setAcc(ArrayList<String> list) {
		int type1;
		int fund11;
		int fund21;
		String name1;
		String pin1;
		String birth1;
		String addr1;
		String accountnum1;
		Boolean state1;
		String date11;
		String date21;
		int i = 0;
		int uppercase = list.size();
		while (i < uppercase) {
			type1 = Integer.parseInt(list.get(i++));
			name1 = list.get(i++);
			addr1 = list.get(i++);
			birth1 = list.get(i++);
			pin1 = list.get(i++);
			accountnum1 = list.get(i++);
			fund11 = Integer.parseInt(list.get(i++));
			fund21 = Integer.parseInt(list.get(i++));
			date11 = list.get(i++);
			date21 = list.get(i++);
			if (list.get(i).equals("true"))
				state1 = true;
			else
				state1 = false;
			Account acct = new Account(type1, name1, addr1, birth1, pin1, accountnum1, fund11, fund21, date11, date21,
					state1);
			return acct;
		}
		Account acc = new Account();
		return acc;
	}

	/**
	 * Method wirteFile This method generate file from the arraylist of account
	 * memorized in the memory.
	 * 
	 * @param acct
	 */

	public void writeFile(Account acct) {
		File f = new File(acct.getAccountnum() + ".txt");

		FileWriter fw;
		try {

			fw = new FileWriter(f.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			String s = String.valueOf(acct.getType());
			bw.write(s);
			bw.newLine();
			bw.write(acct.getName());
			bw.newLine();
			bw.write(acct.getAddr());
			bw.newLine();
			bw.write(acct.getBirth());
			bw.newLine();
			bw.write(acct.getPin());
			bw.newLine();
			bw.write(acct.getAccountnum());
			bw.newLine();
			String s1 = String.valueOf(acct.getFund1());
			bw.write(s1);
			bw.newLine();
			String s2 = String.valueOf(acct.getFund2());
			bw.write(s2);
			bw.newLine();
			bw.write(acct.getDate1());
			bw.newLine();
			bw.write(acct.getDate2());
			bw.newLine();
			String s3 = acct.getState().toString();
			bw.write(s3);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method readFile() This method is used to read the account file to an
	 * arraylist and memorize the content in the memory.
	 * 
	 * @param file
	 * @return list
	 */
	public ArrayList<String> readFile(File file) {
		try {
			String encoding = "GBK";
			ArrayList<String> list = new ArrayList<String>();
			if (file.exists()) {
				InputStreamReader in = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bfr = new BufferedReader(in);
				String tempString = null;
				while ((tempString = bfr.readLine()) != null) {
					list.add(tempString);
				}
				bfr.close();
				return list;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method Login1() This method is used to log in.
	 * @throws ParseException 
	 */
	public void Login1() throws ParseException {
		String c = sc.nextLine();
		System.out.println("Please enter your account number:");
		String Acc = sc.nextLine();
		Acc1 = Acc;
		File ff = new File(Acc + ".txt");
		if (ff.exists()) {
			Account acc = setAcc(readFile(ff));
			System.out.println("Please enter your login pin:");
			String pin2 = sc.nextLine();
			if (pin2.equals(acc.getPin())) {
				System.out.println("Log in Successfully!");
				System.out.println("Please select service");
				Login2(acc);
			} else
				System.out.println("pin is not right!");
			Interface();
		} else
			System.out.println("This account doesn't exist!");
		Interface();
	}

	/**
	 * Method Login2() This method is used to enter the interface of choosing
	 * operations after login
	 * 
	 * @param acc
	 */
	public void Login2(Account acc) throws ParseException {
		String date3;
		SimpleDateFormat ymd = new SimpleDateFormat("YYYY-MM-dd");
		ymd.setLenient(false);
		date3 = ymd.format(new Date());
		acc.setdate2(date3);
		writeFile(acc);
		System.out.println("<1> Check status");
		System.out.println("<2> Deposit money");
		System.out.println("<3> Withdraw money");
		System.out.println("<4> Suspend account");
		System.out.println("<5> Active account");
		System.out.println("<6> Close account");
		System.out.println("<7> Clear fund");
		System.out.println("<8> Show information");
		System.out.println("<9> Logout");
		int o = sc.nextInt();
		switch (o) {
		case 1:
			Lookup(acc);
			break;
		case 2:
			Deposit(acc);
			break;
		case 3:
			Withdraw(acc);
			break;
		case 4:
			Suspend(acc);
			break;
		case 5:
			Active(acc);
			break;
		case 6:
			Close(acc);
			break;
		case 7:
			Clear();
			break;
		case 8:
			Checkinfo();
			break;
		case 9:
			Interface();
			break;
		default:
			System.out.println("Wrong! Please input again.");
			Login2(acc);
			break;
		}
	}

	/**
	 * Method Lookup() This method is used for a user to check balance, unclear
	 * money, state of account in the user file.
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Lookup(Account acc) throws ParseException {
		System.out.println("Now your balance is:  " + acc.getFund2());
		System.out.println("Unclear money: " + acc.getFund1());
		if (acc.getState() == false)
			System.out.println("Your account is suspended ");
		else
			System.out.println("Your account is active");
		Login2(acc);

	}

	/**
	 * Method Deposit() This method is used to deposit.
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Deposit(Account acc) throws ParseException {
		String c = sc.nextLine();
		System.out.println("Please enter the account number you want to confrom:");
		String Acc2 = sc.nextLine();
		File ff = new File(Acc2 + ".txt");
		if (ff.exists()) {
			Account acct = setAcc(readFile(ff));
			if (acct.getState() == true) {
				System.out.println("Choose the method of payment");
				System.out.println("<1> Cash");
				System.out.println("<2> Cheque");
				int h = sc.nextInt();
				if (Acc1.equals(Acc2))// varify authorisation
				{
					if (acc.getType() == 1) {
						System.out.println("You can only withdraw 7 days after depositing money");
					}
					String date5;
					SimpleDateFormat ymd = new SimpleDateFormat("YYYY-MM-dd");
					date5 = ymd.format(new Date());
					acc.setdate1(date5);// store this date into user file, this date is the last deposit date
					System.out.println("Please enter the amount you want to deposit:");
					int am1 = sc.nextInt();
					switch (h) {
					case 1:
						acc.setfund2(am1);
						System.out.println("Success! Your balance is" + acc.getFund2());
						break;
					case 2:
						acc.setfund1(am1);
						System.out.println("Success! Your balance is " + acc.getFund2());
						System.out.println("Unclear money " + acc.getFund1());
						break;
					default:
						System.out.println("Wrong! Please input again");
						int h1 = sc.nextInt();
						h = h1;
						break;
					}
					writeFile(acc);
					Login2(acc);
				} else
					System.out.println("Wrong! Please input again!");
				Login2(acc);
			} else
				System.out.println("Wrong! Your account has been suspended");
			Login2(acc);
		} else
			System.out.println("Wrong! The account dosen't exist.");
		Login2(acc);
	}

	/**
	 * Method Withdraw() This method is used for a user to withdraw money
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Withdraw(Account acc) throws ParseException {
		String[] str3 = acc.getDate2().split("\\-");
		String[] str4 = acc.getDate1().split("\\-");
		if (acc.getState() == true) {// check state
			System.out.println("Your balance : " + acc.getFund2() + " Please enter the amount you want to withdraw:");
			int wm = sc.nextInt();
			if (acc.getType() == 1)// Saver account
			{
				if (checkDate(str3, str4) >= 7 && wm <= acc.getFund2()) {
					acc.setfund2(-wm);
					writeFile(acc);
					System.out.println("Success! Your balance  " + acc.getFund2());
				}
				if (checkDate(str3, str4) >= 7 && wm > acc.getFund2()) {
					System.out.println("Wrong! Your balance is not enough ");
					System.out.println("Your balance " + acc.getFund2());
				}
				if (checkDate(str3, str4) < 7) {
					System.out.println("Wrong! you can't deposit money within 7 days");
				}

			}

			if ((wm <= acc.getFund2() && acc.getType() == 2) || (wm <= (acc.getFund2() + 500) && acc.getType() == 3)) {
				acc.setfund2(-wm);
				writeFile(acc);
				System.out.println("Success! Your cleared balance : " + acc.getFund2());
				Login2(acc);
			} else {
				System.out.println("Wrong! Your balance is not enough ");
				System.out.println("Your balance " + acc.getFund2());
			}

		} else
			System.out.println("Wrong!your account is suspended");
		Login2(acc);
	}

	/**
	 * Method checkDate(date1,date2) This method is used to calculate date
	 * 
	 * @param date1
	 * @param date2
	 * @return date
	 */

	public int checkDate(String[] date1, String[] date2) {
		int date = 365 * (Integer.parseInt(date1[0]) - Integer.parseInt(date2[0]))
				+ 30 * (Integer.parseInt(date1[1]) - Integer.parseInt(date2[1])) + Integer.parseInt(date1[2])
				- Integer.parseInt(date2[2]);
		return date;
	}

	/**
	 * Method Suspend() This method is used to suspend the account
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Suspend(Account acc) throws ParseException {
		System.out.println("Please enter your pin to confirm ");
		String c = sc.nextLine();
		String pin3 = sc.nextLine();
		if (pin3.equals(acc.getPin()))// verify the pin
		{
			acc.setstate(false);// change the boolean value in user file to false(suspend)
			System.out.println("Success! now your account is suspended");
			writeFile(acc);
		} else {
			System.out.println("Wrong pin");
			Login2(acc);
		}
		Login2(acc);
	}

	/**
	 * Method Active() This method is used to active suspended account
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Active(Account acc) throws ParseException {
		System.out.println("Please enter your pin to confirm");
		String c = sc.nextLine();
		String pin4 = sc.nextLine();
		if (pin4.equals(acc.getPin()))// verify the pin
		{
			acc.setstate(true);// active the account
			System.out.println("Your account is active");
			writeFile(acc);
		} else {
			System.out.println("Wrong pin");
			Login2(acc);
		Login2(acc);
		}
	}

	/**
	 * Method Close() This method is used for a user to close his account and delete
	 * his user file.
	 * 
	 * @param acc
	 * @throws ParseException 
	 */
	public void Close(Account acc) throws ParseException {
		System.out.println("Please enter your pin to confirm");
		String c = sc.nextLine();
		String pin6 = sc.nextLine();
		if (pin6.equals(acc.getPin()))// verify the pin
		{
			File fff = new File(Acc1 + ".txt");
			if (fff.delete()) {
				System.out.println("Sccuess! Close account" + Acc1);
				Interface();
			}
		} else {
			System.out.println("Wrong! pin is not right!");
			Login2(acc);
		}
	}

	/**
	 * Method Checkinfo() This method is user to show all information
	 * @throws ParseException 
	 **/
	public void Checkinfo() throws ParseException {
		String c = sc.nextLine();
		System.out.println("Please input the account number that you want to check");
		String ACC = sc.nextLine();
		File ff = new File(ACC + ".txt");
		if (ff.exists()) {
			Account ACC1 = setAcc(readFile(ff));
			System.out.println("All the information of account" + ACC);
			System.out.println("Account type:" + ACC1.getType());
			System.out.println("1 is saver account. 2 is junior account. 3 is current account");
			System.out.println("Name is " + ACC1.getName());
			System.out.println("Address is " + ACC1.getAddr());
			System.out.println("Birthday " + ACC1.getBirth());
			System.out.println("User pin " + ACC1.getPin());
			System.out.println("Account number " + ACC1.getAccountnum());
			System.out.println("Unclear money " + ACC1.getFund1());
			System.out.println("Balance " + ACC1.getFund2());
			System.out.println("Date of last time when depositing money " + ACC1.getDate1());
			System.out.println("Date of last login " + ACC1.getDate2());
			System.out.println("State of account " + ACC1.getState());
			System.out.println("(true for active; false for suspended)");
		} else
			System.out.println("This account number doesn't exist.");
		Login1();
	}

	/**
	 * Method Clear() This method is used to clear deposit
	 * @throws ParseException 
	 */
	public void Clear() throws ParseException {
		String c = sc.nextLine();
		System.out.println("Please enter the account number that you want to clear");
		String ACC = sc.nextLine();
		File ff = new File(ACC + ".txt");
		if (ff.exists()) {
			Account ACC2 = setAcc(readFile(ff));
			ACC2.setfund2(ACC2.getFund1());
			ACC2.setfund1(-ACC2.getFund1());
			System.out.println("Balance: " + ACC2.getFund2());
			writeFile(ACC2);
			Login2(ACC2);
		} else
			System.out.println("This account doesn't exist");
		Login1();
	}

}