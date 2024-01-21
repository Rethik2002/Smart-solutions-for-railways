import java.util.*;

class Customer{
	private int id;
	private String name;
	private int age;
	private String gender;
	private String berthPreference;
	Customer(int id,String name,int age,String gender,String berthPreference){
		this.id=id;
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.berthPreference=berthPreference;
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getGender() {
		return this.gender;
	}
	public String getBerthPrefernce() {
		return this.berthPreference;
	}
}
public class RailwayReservationSystem {
	static Scanner sc=new Scanner(System.in);
	static int index=0;
	static Customer customer[]=new Customer[index+1];
	static char tickets[][]=new char[9][9];
	static int ticketsCount=0; 
	//static ArrayList rac=new ArrayList();
	static int racCount=0;
	static ArrayList waitingList=new ArrayList();
	static int waitingListCount=0;
	public static void main(String[] args) {
		initialTickets();
		while(true) {
			System.out.println("n - New User\no - Old User\nEnter Your Choice (n/o) : ");
			char user=getUser();
			switch(user) {
				case 'n':
					newUser();
					break;
				case 'o':
					oldUser();
					break;
				default:
					System.out.println("Enter valid choice");
					break;
			}
		}
	}
	public static void initialTickets() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tickets[i][j]='A';
			}
		}
	}
	public static char getUser() {
		while(true) {
			String user=sc.nextLine();
			if(user.length()>1) {
				System.out.println("Enter valid choice");
			}
			else {
				return user.charAt(0);
			}
		}
	}
	public static void newUser() {
		int flag=0;
		outer:
		while(true) {
			System.out.println("1) view Available Tickets\n2) view Total Number Of Tickets\n3) Booking Tickets\n4) Cancelling Tickets\n5) Exit\nEnter Yout Choice : ");
			int choice=sc.nextInt();
			switch(choice) {
				case 1:
					printAvailableTickets();
					break;
				case 2:
					printTotalNumberOfTickets();
					break;
				case 3:
					BookTickets();
					flag=1;
					break;
				case 4:
					if(flag==1) {
						cancelTicket();
						break;
					}
					else {
						System.out.println("You cannot reserve any ticket");
						break;
					}
				case 5:
					break outer;
				default:
					System.out.println("Enter valid choice");
					break;
			}
		}
	}
	public static void BookTickets() {
		System.out.println("Total Number of Booking Tickets : ");
		int num=sc.nextInt();
		for(int i=0;i<num;i++) {
			System.out.println("Enter Name : ");
			String name=sc.nextLine();
			sc.nextLine();
			System.out.println("Enter Age : ");
			int age=sc.nextInt();
			sc.nextLine();
			String gender;
			while(true) {
				System.out.println("Enter Gender : ");
				gender=sc.nextLine();
				if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
					break;
				}
				else {
					System.out.println("Enter valid gender");
				}
			}
			sc.nextLine();
			System.out.println("Enter berth Preference : ");
			String berthPreference;
			while(true) {
				berthPreference=sc.nextLine();
				if(checkPreference(berthPreference)) {
					System.out.println("Your preference is booked successfully\nYour reference id "+(index+1));
					break;
				}
				else {
					System.out.println("Your prefernce is unavailable. Please select available seats");
				}
			}
			customer[index]=new Customer(index+1,name,age,gender,berthPreference);
			index++;
			customer=Arrays.copyOf(customer,index+1);
		}
	}
	public static void cancelTicket() {
		String name=sc.nextLine();
		System.out.println("Enter Your Reference Id : ");
		int id=sc.nextInt();
		int flag=0;
		for(int i=0;i<index;i++) {
			if(customer[i].getId()==id) {
				String preference[]=customer[i].getBerthPrefernce().split("-");
				int row=preference[0].charAt(0)-'0';
				int col=preference[1].charAt(0)-'0';
				tickets[row-1][col-1]='A';
				if(col==8 || col==9) {
					racCount--;
				}
				else {
					ticketsCount--;
				}
				System.out.println("Ticket Cancelled Successfully");
				flag++;
			}
		}
		if(flag==0) {
			System.out.println("Invalid name or reference id");
		}
		
	}
	public static boolean checkPreference(String berthPreference) {
		String preference[]=berthPreference.split("-");
		int row=preference[0].charAt(0)-'0';
		int col=preference[1].charAt(0)-'0';
		if(tickets[row-1][col-1]=='A') {
			tickets[row-1][col-1]='U';
			if(col==8 || col==9) {
				racCount++;
			}
			else {
				ticketsCount++;
			}
			return true;
		}
		else {
			return false;
		}
		
	}
	public static void printAvailableTickets() {
		System.out.println("----------------------------------------------");
		System.out.print("   ");
		for(int i=0;i<8;i++) {
			System.out.print(" "+(i+1)+" ");
		}
		System.out.println();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(j==0) {
					System.out.print("s"+(i+1)+"  ");
				}
				else {
					System.out.print(" "+tickets[i][j-1]+" ");
				}
			}
			System.out.println();
		}
		System.out.println("A - Avaliable Tickets\nU - Unvaliable Tickets");
		System.out.println("----------------------------------------------");
	}
	public static void printTotalNumberOfTickets() {
		if(ticketsCount==63) {
			System.out.println("Number of Tickets Available in RAC : "+(18-racCount));
		}
		else if(racCount==18) {
			System.out.println("Number of Waiting List Tickets : "+(10-waitingListCount));
		}
		else {
			System.out.println("Number of Tickets Available berth Tickets : "+(63-ticketsCount));
		}
		System.out.println("----------------------------------------------");
	}
	public static void oldUser() {
		
	}

}
