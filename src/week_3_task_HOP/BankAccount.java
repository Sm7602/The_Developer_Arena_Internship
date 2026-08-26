package week_3_task_HOP;

class Bank{
	private long acNo;
	private String acHolderName;
	private String adress;
	private String phNo;
	private double amount;
	private  double balance;

	public Bank(long acNo, String acHolderName, String adress, String phNo) {
		this.acNo = acNo;
		this.acHolderName = acHolderName;
		this.adress = adress;
		this.phNo = phNo;
		this.balance=0.00;
	}
	
	public long getAcNo() {
		return acNo;
	}
	public void setAcNo(long acNo) {
		this.acNo = acNo;
	}
	public String getAcHolderName() {
		return acHolderName;
	}
	public void setAcHolderName(String acHolderName) {
		this.acHolderName = acHolderName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Bank [acNo=" + acNo + ", acHolderName=" + acHolderName + ", adress=" + adress + ", phNo=" + phNo
				+ ", amount=" + amount + "]";
	}
	
	public void deposit(double amount) {
		System.out.println("deposit proccesing........"+amount);
		 if (amount <= 0) {
	            System.out.println("Deposit amount must be greater than 0.");
	            return;
	        }
		balance+=amount;	
		System.out.println("deposit ho gaya........current balance = "+balance);
	}
	
	public void withdraw (double amount) {
		System.out.println("withdraw  proccesing........");
		if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
       
        balance -= amount;
        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: ₹" + balance);
	}
	
	
}
public class BankAccount {
	
	public static void main(String[] args) {

        Bank account =new Bank(1234567890L,"Souvik", "ifce","7894561230");


        System.out.println();

        account.deposit(5000);

        System.out.println();

        account.withdraw(3000);

        System.out.println();

        account.withdraw(15000);
    }

}
