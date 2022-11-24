/**
 * project: Digital Banking
 * date created: 24/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking;

import digitalbanking.model.Bank;

import java.util.Scanner;

public class Entry {
    private static Scanner scanner;
    private static Bank bank;

    //hàm main
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        bank = new Bank();

        displayGeneralInfo();
        boolean flag = true;

        do {
            switch (inputMenu()) {
                case "1"://thêm khách hàng
                    bank.addCustomer(scanner);
                    break;

                case "2"://thêm tài khoản cho khách hàng
                    bank.addAccount(scanner);
                    break;

                case "3"://hiển thị danh sách khách hàng
                    bank.displayDetailCustomersList();
                    break;

                case "4"://tìm khách hàng theo CCCD
                    bank.searchCustomerById(scanner);
                    break;

                case "5"://tìm khách hàng theo tên
                    bank.searchCustomerByName(scanner);
                    break;

                case "0"://thoát
                    displayExitWarning();
                    flag = false;
                    break;

                default:
                    System.out.println("Vui lòng chọn chức năng từ 0 - 5");
                    break;
            }
        } while (flag);
    }

    //hàm chọn chức năng giao diện
    private static String inputMenu() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "1. Thêm khách hàng");
        System.out.printf("| %-52s |\n", "2. Thêm tài khoản cho khách hàng");
        System.out.printf("| %-52s |\n", "3. Hiển thị danh sách khách hàng");
        System.out.printf("| %-52s |\n", "4. Tìm khách hàng theo CCCD");
        System.out.printf("| %-52s |\n", "5. Tìm khách hàng theo tên");
        System.out.printf("| %-52s |\n", "0. Thoát");
        System.out.println("+------------------------------------------------------+");
        System.out.print("Chọn chức năng: ");
        String answer = scanner.nextLine();
        System.out.println("+------------------------------------------------------+");
        return answer;
    }

    //hàm hiển thị thông tin chung
    private static void displayGeneralInfo() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "ỨNG DỤNG NGÂN HÀNG SỐ | V1.0.0");
        System.out.println("+------------------------------------------------------+");
    }

    //hàm hiển thị thông báo thoát
    private static void displayExitWarning() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "Thoát thành công");
        System.out.println("+------------------------------------------------------+");
    }
}
