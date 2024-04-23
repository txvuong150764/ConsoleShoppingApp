package Service;

import DTO.Customer;

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
}
