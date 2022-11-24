/**
 * purpose: quản lý thông tin ngân hàng
 * date created: 24/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    /*ATTRIBUTES*/
    private String bankId;
    private ArrayList<Customer> customersList;

    /*GETTERS & SETTERS*/
    public String getBankId() {
        return this.bankId;
    }

    public ArrayList<Customer> getCustomersList() {
        return this.customersList;
    }

    /*CONSTRUCTORS*/
    public Bank() {
        this.bankId = String.valueOf(UUID.randomUUID());
        this.customersList = new ArrayList<Customer>();
    }

    /*SERVICE METHODS*/
    //phương thức thêm khách hàng
    public void addCustomer(Customer newCustomer) {
        if (!isCustomerExisted(newCustomer)) {
            this.customersList.add(newCustomer);
            System.out.printf("Đã thêm khách hàng %s vào danh sách\n", newCustomer.getCustomerId());
        } else {
            System.out.printf("Khách hàng %s đã tồn tại trong danh sách\n", newCustomer.getCustomerId());
            System.out.println("Thêm khách hàng không thành công");
        }
    }

    public void addCustomer(Scanner scanner) {
        Customer customer = new Customer();
        customer.input(scanner);
        if (!isCustomerExisted(customer)) {
            this.customersList.add(customer);
            System.out.printf("Đã thêm khách hàng %s vào danh sách\n", customer.getCustomerId());
        } else {
            System.out.printf("Khách hàng %s đã tồn tại trong danh sách\n", customer.getCustomerId());
            System.out.println("Thêm khách hàng không thành công");
        }
    }

    //phương thức thêm tài khoản cho khách hàng
    public void addAccount(String customerId, Account account) {
        if (!this.customersList.isEmpty()) {
            Customer customer = searchCustomerById(customerId);
            if (customer != null) {
                if (!isAccountExisted(account)) {
                    customer.addAccount(account);
                } else {
                    System.out.printf("Tài khoản %s đã tồn tại\n", account.getAccountNumber());
                    System.out.println("Thêm tài khoản không thành công");
                }
            } else {
                System.out.printf("Không tìm thấy khách hàng %s trong danh sách\n", customerId);
                System.out.println("Thêm tài khoản không thành công");
            }
        } else
            System.out.println("Danh sách không có khách hàng nào");
    }

    public void addAccount(Scanner scanner) {
        if (!this.customersList.isEmpty()) {
            displayCustomersList();
            Customer customer;
            do {
                System.out.print("Nhập số CCCD khách hàng: ");
                customer = searchCustomerById(scanner.nextLine());
            } while (customer == null);

            Account account = new Account();
            account.input(scanner);
            if (!isAccountExisted(account)) {
                customer.addAccount(account);
            } else {
                System.out.printf("Tài khoản %s đã tồn tại\n", account.getAccountNumber());
                System.out.println("Thêm tài khoản không thành công");
            }
        } else
            System.out.println("Danh sách không có khách hàng nào");
    }

    //phương thức hiển thị danh sách khách hàng
    public void displayCustomersList() {
        for (Customer customer : this.customersList) {
            System.out.println(customer.toString());
        }
    }

    //phương thức hiển thị chi tiết danh sách khách hàng
    public void displayDetailCustomersList() {
        if (!this.customersList.isEmpty()) {
            for (Customer customer : this.customersList) {
                customer.displayInformation();
            }
        } else
            System.out.println("Danh sách không có khách hàng nào");
    }

    //phương thức tìm kiếm khách hàng theo id
    public void searchCustomerById(Scanner scanner) {
        if (!this.customersList.isEmpty()) {
            String customerId;
            do {
                System.out.print("Nhập số CCCD khách hàng: ");
                customerId = scanner.nextLine();
            } while (!CustomerIdValidator.validateCustomerId(customerId));

            Customer customer = searchCustomerById(customerId);

            if (customer != null)
                customer.displayInformation();
            else
                System.out.println("Không tìm thấy khách hàng trong danh sách");
        } else
            System.out.println("Danh sách không có khách hàng nào");
    }

    public Customer searchCustomerById(String customerId) {
        for (Customer customer : this.customersList) {
            if (customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }

    //phương thức tìm kiếm gần đúng khách hàng theo tên
    public void searchCustomerByName(Scanner scanner) {
        if (!this.customersList.isEmpty()) {
            System.out.print("Nhập tên khách hàng: ");
            String customerName = scanner.nextLine();
            boolean flag = false;

            for (Customer customer : this.customersList) {
                if (customer.getName().contains(customerName)) {
                    customer.displayInformation();
                    flag = true;
                }
            }

            if (!flag)
                System.out.println("Không tìm thấy khách hàng trong danh sách");
        } else
            System.out.println("Danh sách không có khách hàng nào");
    }

    //phương thức kiểm tra khách hàng đã tồn tại trong ngân hàng hay chưa
    private boolean isCustomerExisted(Customer newCustomer) {
        for (Customer customer : this.customersList) {
            if (customer.getCustomerId().equals(newCustomer.getCustomerId()))
                return true;
        }
        return false;
    }

    //phương thức kiểm tra tài khoản đã tồn tại trong ngân hàng hay chưa
    private boolean isAccountExisted(Account account) {
        for (Customer customer : this.customersList) {
            if (customer.isAccountExisted(account))
                return true;
        }
        return false;
    }
}
