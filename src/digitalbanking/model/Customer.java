/**
 * purpose: quản lý thông tin khách hàng
 * date created: 24/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
    /*ATTRIBUTES*/
    private ArrayList<Account> accountsList;

    /*GETTERS & SETTERS*/
    public ArrayList<Account> getAccountsList() {
        return this.accountsList;
    }

    /*CONSTRUCTORS*/
    public Customer() {
        super();
        this.accountsList = new ArrayList<Account>();
    }

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accountsList = new ArrayList<Account>();
    }

    /*SERVICE METHODS*/
    //phương thức nhập dữ liệu
    public void input(Scanner scanner) {
        System.out.print("Nhập tên khách hàng: ");
        this.name = scanner.nextLine();
        do {
            System.out.print("Nhập số CCCD: ");
            this.customerId = scanner.nextLine();
        } while (!CustomerIdValidator.validateCustomerId(this.customerId));
    }

    //phương thức cho biết khách hàng có phải là premium hay không
    public void isPremium() {
        boolean isPremium = isCustomerPremium();
        System.out.printf("Khách hàng %s %s premium\n", this.customerId, isPremium ? "là" : "không phải là");
    }

    //phương thức thêm tài khoản
    public void addAccount(Account newAccount) {
        if (!isAccountExisted(newAccount)) {
            this.accountsList.add(newAccount);
            System.out.printf("Đã thêm tài khoản %s vào danh sách tài khoản của khách hàng %s\n", newAccount.getAccountNumber(), this.customerId);
        } else {
            System.out.printf("Tài khoản %s đã tồn tại trong danh sách tài khoản của khách hàng %s\n", newAccount.getAccountNumber(), this.customerId);
            System.out.println("Thêm tài khoản không thành công");
        }
    }

    //phương thức tính tổng số dư tài khoản
    public double getBalance() {
        double totalBalance = 0;
        for (Account account : this.accountsList) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    //phương thức hiển thị thông tin khách hàng
    public void displayInformation() {
        System.out.println(toString());

        for (int i = 0; i < this.accountsList.size(); i++) {
            System.out.printf("%-5d %s\n", i + 1, this.accountsList.get(i).toString());
        }
    }

    //phương thức xuất dữ liệu
    @Override
    public String toString() {
        return String.format("%-12s | %-7s | %-7s | %,15.2f (VNĐ)", this.customerId, this.name, isCustomerPremium() ? "Premium" : "Normal", getBalance());
    }

    //phương thức kiểm tra tài khoản đã tồn tại trong danh sách tài khoản của khách hàng hay chưa
    public boolean isAccountExisted(Account newAccount) {
        for (Account account : this.accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber()))
                return true;
        }
        return false;
    }

    //phương thức kiểm tra khách hàng có phải là premium hay không
    private boolean isCustomerPremium() {
        for (Account account : this.accountsList) {
            if (account.isAccountPremium()) {
                return true;
            }
        }
        return false;
    }
}
