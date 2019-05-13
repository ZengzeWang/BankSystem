
public class Account {
	private int type,fund1,fund2;
	private Boolean state;
	private String name,addr,birth,pin,accountnum,date1,date2;


	public Account() {
	}
	/**
	 * @constructor Account. This constructor is the constructor that stores customer's all information.
	 * @param type, name, addr,brith, pin, accountnum, fund1,fund2,date1,date2,state
	 * 
	 */
	public Account(int type, String name, String addr, String birth, String pin, String accountnum, int fund1,
			int fund2, String date1, String date2, Boolean state) {
		this.type = type;
		this.name = name;
		this.addr = addr;
		this.birth = birth;
		this.pin = pin;
		this.accountnum = accountnum;
		this.fund1 = fund1;
		this.fund2 = fund2;
		this.state = state;
		this.date1 = date1;
		this.date2 = date2;
	}

	/**
	 * Method setstate()
	 * 
	 * @param state
	 */
	public void setstate(Boolean state) {
		this.state = state;
	}

	/**
	 * Method setfund1()
	 * 
	 * @param fund1
	 */
	public void setfund1(int fund1) {
		this.fund1 = this.fund1 + fund1;
	}

	/**
	 * Method serfund2()
	 * 
	 * @param fund2
	 */
	public void setfund2(int fund2) {
		this.fund2 = this.fund2 + fund2;
	}

	/**
	 * Method setdate1()
	 * 
	 * @param date1
	 */
	public void setdate1(String date1) {
		this.date1 = date1;
	}

	/**
	 * Method setdate2()
	 * 
	 * @param date2
	 */
	public void setdate2(String date2) {
		this.date2 = date2;
	}

	/**
	 * Method setpin()
	 * 
	 * @param pin
	 */
	public void setpin(String pin) {
		this.pin = pin;
	}

	/**
	 * Method getType()
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Method getState()
	 * 
	 * @return state
	 */
	public Boolean getState() {
		return state;
	}

	/**
	 * Method getName()
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method getAddr()
	 * 
	 * @return addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * Method getBirth()
	 * 
	 * @return birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * Method getPin
	 * 
	 * @return pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Method getAccountnum()
	 * 
	 * @return accountnum
	 */
	public String getAccountnum() {
		return accountnum;
	}

	/**
	 * Method getFund1()
	 * 
	 * @return fund1
	 */
	public int getFund1() {
		return fund1;
	}

	/**
	 * Method getFund2()
	 * 
	 * @return fund2
	 */
	public int getFund2() {
		return fund2;
	}

	/**
	 * Method getDate1()
	 * 
	 * @return date1
	 */
	public String getDate1() {
		return date1;
	}

	/**
	 * Method getDate2()
	 * 
	 * @return date2
	 */
	public String getDate2() {
		return date2;
	}
	
}