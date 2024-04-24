package Service;

import DTO.Cutomer.Customer;
import DTO.Voucher.Voucher;
import Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    public Customer getCustomer(ArrayList<Customer> customers, String name, String password) {
        for(Customer customer : customers) {
            if(customer.getName().equals(name) && customer.getPassword().equals(password)) {
                return customer.classifyCustomer(customer.getRank());
            }
        }
        return null;
    }
    public void viewRank(Customer customer) {
        System.out.println("\nYour rank is " + customer.getRank() + " with " + customer.getLoyalPoints() + " loyalty points.");
        System.out.println("Benefit for " + customer.getRank() + " customer are listed below: ");
        Utils.printVoucherHeader();
        for(Voucher voucher : customer.getVoucherList()) {
            Utils.printVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        }
        Utils.printVoucherEnd();
    }
    public Customer login(ArrayList<Customer> customers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Login Screen ----------------------------");
        System.out.print("                      Name: ");
        String name = sc.nextLine();
        System.out.print("                      Password: ");
        String password = sc.nextLine();

        Customer loginUser = this.getCustomer(customers, name, password);
        if(loginUser != null) {
            System.out.println("\n                        Login Successfully!!!!");
        }
        else {
            System.out.println("\nName or Password is incorrect. Please try again!!!!!!!!");
            loginUser = login(customers);
        }
        return loginUser;
    }
}
