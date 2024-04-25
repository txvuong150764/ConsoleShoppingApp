import DTO.Cutomer.Customer;
import DTO.Shipping.BasicShipping;
import DTO.Shipping.Shipping;
import Service.CartService;
import Service.CustomerService;
import Service.ShopService;
import Utils.Utils;

import java.util.ArrayList;

import Enum.MainMenuOption;
import Enum.ShoppingCartOption;
import Enum.ShoppingOption;
import Enum.ShippingMethod;
import Enum.CheckoutOption;

public class Main {
    static CustomerService customerService = new CustomerService();
    static CartService cartService = new CartService();
    static ShopService shopService = new ShopService();
    public static void main(String[] args) {
        ArrayList<Customer> customers = Utils.readCustomersFile("C:\\Users\\Tran Xuan Vuong\\OneDrive\\Máy tính\\Java Course\\ConsoleShoppingApp\\ConsoleShoppingApp\\src\\Database\\customers.txt");

        Customer loggedInCustomer = customerService.login(customers);

        while(true) {
            MainMenuOption mainMenuOption = MainMenuOption.fromInput(Utils.getMainMenuCustomerInput(loggedInCustomer.getName()));
            if(mainMenuOption == null) {
                System.out.println("Your option is invalid. Please try again with 1 or 2 or 3");
                continue;
            }
            switch (mainMenuOption) {
                case VIEW_CART -> {
                    System.out.println("Welcome to Shopping Cart");
                    boolean backToMainMenu = false;
                    cartService.viewCart(loggedInCustomer.getShoppingCart());
                    while(!backToMainMenu) {
                        ShoppingCartOption shoppingCartOption = ShoppingCartOption.fromInput(Utils.getCartCustomerInput());
                        if(shoppingCartOption == null) {
                            System.out.println("Your option is invalid. Please try again with 1 or 2");
                            continue;
                        }
                        switch (shoppingCartOption) {
                            case SELECT_SHIPPING_METHOD -> {
                                if(cartService.isEmpty(loggedInCustomer)) {
                                    System.out.println("Your cart is empty. Redirect to main menu");
                                    backToMainMenu = true;
                                    break;
                                }
                                boolean backToCartMenu = false;
                                while (!backToCartMenu) {
                                    ShippingMethod shippingMethod = ShippingMethod.fromInput(Utils.getShippingCustomerInput());
                                    if (shippingMethod == null) {
                                        System.out.println("Your option is invalid. Please try again with 1 to 4");
                                        continue;
                                    }

                                    if (shippingMethod == ShippingMethod.RETURN)
                                        break;

                                    Shipping shipping = Shipping.classifyShippingMethod(shippingMethod);
                                    cartService.viewCartToCheckout(loggedInCustomer, shipping);

                                    boolean backToShippingMenu = false;
                                    while (!backToShippingMenu) {
                                        CheckoutOption checkoutOption = CheckoutOption.fromInput(Utils.getCheckoutCustomerInput());
                                        if (checkoutOption == null) {
                                            System.out.println("Your option is invalid. Please try again with 1 or 2");
                                            continue;
                                        }

                                        switch (checkoutOption) {
                                            case CHECK_OUT -> {
                                                cartService.checkOut(loggedInCustomer, shipping);
                                                backToShippingMenu = true;
                                                backToCartMenu = true;
                                                backToMainMenu = true;
                                            }
                                            case RETURN -> backToShippingMenu = true;
                                        }
                                    }
                                }
                            }
                            case RETURN -> backToMainMenu = true;
                        }
                    }
                }
                case VIEW_RANK -> customerService.viewRank(loggedInCustomer);
                case VIEW_SHOP_ITEMS -> {
                    boolean backToMainMenu = false;
                    shopService.viewItems(loggedInCustomer);
                    while(!backToMainMenu) {
                        ShoppingOption shoppingOption = ShoppingOption.fromInput(Utils.getShopCustomerInput());
                        if(shoppingOption == null) {
                            System.out.println("Your option is invalid. Please try again with 1 or 2");
                            continue;
                        }
                        switch (shoppingOption) {
                            case BUY -> shopService.buyItem(loggedInCustomer);
                            case RETURN -> backToMainMenu = true;
                        }
                    }
                }
                case EXIT -> {
                    System.out.println("Thanks for using our app.");
                    System.exit(0);
                }
            }
        }
    }
}