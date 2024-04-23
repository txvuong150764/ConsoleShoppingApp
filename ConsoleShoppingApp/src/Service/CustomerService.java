package Service;

import DTO.Customer;
import DTO.Item;
import Utils.Utils;

import java.util.ArrayList;

public class CustomerService {
    public Customer getCustomer(ArrayList<Customer> customers, String name, String password) {
        for(Customer customer : customers) {
            if(customer.getName().equals(name) && customer.getPassword().equals(password)) {
                return customer.classifyCustomer(customer.getRank());
            }
        }
        return null;
    }

    public void viewCart(Customer customer) {
        Utils.printItemHeader();
        for(Item item : customer.getShoppingCart()) {
            Utils.printItem(item.getName(), item.getPrice());
        }
        Utils.printItemEnd();
    }
    public void viewRank(Customer customer) {
        System.out.println("Your rank is " + customer.getRank() + " with " + customer.getLoyalPoints() + " loyalty points.");
    }
}
