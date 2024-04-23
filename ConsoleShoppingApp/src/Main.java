import DTO.Customer;
import Service.CustomerService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static CustomerService customerService = new CustomerService();
    public static Customer login(ArrayList<Customer> customers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Login Screen ----------------------------");
        System.out.print("                      Name: ");
        String name = sc.nextLine();
        System.out.print("                      Password    : ");
        String password = sc.nextLine();

        Customer loginUser = customerService.getCustomer(customers, name, password);
        if(loginUser != null) {
            System.out.println("\n                        Login Successfully!!!!");
        }
        else {
            System.out.println("\nName or Password is incorrect. Please try again!!!!!!!!");
            loginUser = login(customers);
        }
        return loginUser;
    }
    public static void main(String[] args) {
        ArrayList<Customer> customers = Utils.readCustomersFile("C:\\Users\\Tran Xuan Vuong\\OneDrive\\Máy tính\\Java Course\\ConsoleShoppingApp\\ConsoleShoppingApp\\src\\Database\\customers.txt");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }

        Customer loggedInCustomer = login(customers);

        while(true) {

        }
    }
}