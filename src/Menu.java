import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    String str = "";
    String name;
    String name1;
    String noResult = "Khong tim thay yeu cau!!!";
    int choice;
    Scanner scanner = new Scanner(System.in);
    StaffManagement staffManagement = new StaffManagement();

    public Menu() throws IOException {
    }

    void menuMain() {
        str = """
                                
                ---Quan ly nhan vien---|
                1. Hien thi
                2. Them
                3. Tim kiem
                4. In danh sach nhan vien
                5. Sua
                6. Doi trang thai
                7. Tong luong nhan vien
                8. Thoat
                                
                """;
        System.out.println(str);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> menuDisplay();
            case 2 -> menuAdd();
            case 3 -> menuSearch();
            case 4 -> menuPrint();
            case 5 -> menuEdit();
            case 6 -> menuChange();
            case 7 -> menuSal();
            case 8 -> System.exit(1);

        }

        scanner.nextLine();
    }

    void menuDisplay() {
        System.out.println("Danh sach nhan vien la: ");
        staffManagement.showStaffList();
        menuMain();
    }

    void menuAdd() {
        staffManagement.add(staffManagement.menuInput());
        menuMain();
    }

    void menuSearch() {
        System.out.println("Nhap vao ten nhan vien muon tim");
        name = scanner.nextLine();
        if (staffManagement.findByName(name) == -1) {
            System.out.println(noResult);
        }
        menuMain();
    }

    void menuPrint() {
        str = """
                                
                ---Danh sach nhan vien
                1. Nhan vien Full-time
                2. Nhan vien Part-time
                3. Quay lai
                                
                                
                """;
        System.out.println(str);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> staffManagement.filterFulltimeStaff();
            case 2 -> staffManagement.filterParttimeStaff();
            default -> menuMain();
        }
    }

    void menuEdit() {
        scanner.nextLine();
        System.out.println("Nhap vao ten nhan vien muon sua");
        name = scanner.nextLine();
        int index = staffManagement.findByName(name);
        if (index != -1) {
            System.out.println("nhap vao ten nhan can sua!");
            name1 = scanner.nextLine();
            boolean status;
            boolean type;
            try {
                System.out.println("Co phai nhan vien Full-time khong?  true - Full-time/ false - Part-time");
                type = scanner.nextBoolean();
                System.out.println("Co phai nhan vien dang lam khong?  true - Dang lam/ false - Da nghi");
                status = scanner.nextBoolean();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Co phai nhan vien Full-time khong?  true - Full-time/ false - Part-time");
                type = scanner.nextBoolean();
                System.out.println("Co phai nhan vien dang lam khong?  true - Dang lam/ false - Da nghi");
                status = scanner.nextBoolean();
            }
            System.out.println("Nhap vao tien luong nhan vien:");
            int salary = scanner.nextInt();
            Staff staff1 = new Staff(name1, type, status, salary);
            staffManagement.editByName(name, staff1);
        } else {
            System.out.println(noResult);
        }
        System.out.println("Da sua thanh cong!");
        menuMain();
    }

    void menuChange() {
        System.out.println("Nhap vao ten nhan vien muon tim:");
        scanner.nextLine();
        String nameS = scanner.nextLine();

        if (staffManagement.findByName(nameS) != -1) {
            System.out.println("Ban co chac chan muon doi trang thai cua nhan vien " + nameS + "? nhan 'y' de tiep tuc.");
            String nameC = scanner.nextLine();
            if (nameC.equals("y")) {
                staffManagement.updateStaffStatus(nameS);
                System.out.println("Da thay doi thanh cong");
            } else {
                System.out.println("Lenh da duoc huy bo.");
            }

        } else {
            System.out.println(noResult);
        }
        menuMain();
    }

    void menuSal() {
        str = """
                                
                ---Tinh tong luong nhan vien
                1. Nhan vien Full-time
                2. Nhan vien Part-time
                3. Toan bo nhan vien
                4. Quay lai
                                
                """;
        System.out.println(str);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> menucalSalaryFulltime();
            case 2 -> menucalSalaryParttime();
            case 3 -> menucalSalary();
            default -> menuMain();
        }
    }

    void menucalSalaryFulltime() {
        System.out.println("Tong luong nhan vien Full-time la: "
                + staffManagement.calSalaryFulltime());
        menuMain();
    }

    void menucalSalaryParttime() {
        System.out.println("Tong luong nhan vien Part-time la: "
                + staffManagement.calSalaryParttime());
        menuMain();
    }

    void menucalSalary() {
        System.out.println("Tong luong nhan vien la: "
                + staffManagement.calSalary());
        menuMain();
    }
}
