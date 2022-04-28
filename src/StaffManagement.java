import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StaffManagement {

    File file = new File("listStaff.csv");
    Scanner scanner = new Scanner(System.in);
    final String PATH = "listStaff.csv";

    List<Staff> staffList;
    String name;
    boolean type;
    boolean status;
    int salary;
    int index;

    public StaffManagement() throws IOException {
        staffList = readFile(PATH);
        writeFile(staffList,PATH);
    }

    public void add(Staff staff) {
        staffList.add(staff);
        writeFile(staffList,PATH);
    }

    public List<Staff>  readFile(String path) {
        List<Staff> staffList1 = new ArrayList<>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(",");
                staffList1.add(new Staff(strings[0], Boolean.parseBoolean(strings[1]),
                        Boolean.parseBoolean(strings[2]), Integer.parseInt(strings[3])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return staffList1;
    }

    public void writeFile(List<Staff> list, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            String line = "Ten nhan vien, ca lam, trang thai, luong";
            bw.write(line);
            bw.newLine();
            for (Staff staff : list
            ) {
                bw.write(staff.getName() + "," + staff.getType() + "," +
                        staff.getStatus() + "," + staff.getSalary() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Staff menuInput() {
        System.out.println("nhap vao ten nhan vien!");
        name = scanner.nextLine();
        System.out.println("Co phai nhan vien Full-time khong?  true - Full-time/ false - Part-time");
        type = scanner.nextBoolean();
        System.out.println("Co phai nhan vien dang lam khong?  true - Dang lam/ false - Da nghi");
        status = scanner.nextBoolean();
        System.out.println("Nhap vao tien luong nhan vien:");
        salary = scanner.nextInt();
        scanner.nextLine();
        Staff staff1 = new Staff(name, type, status, salary);
        return staff1;
    }


    public void showStaffList() {
        for (Staff staff :
                staffList) {
            System.out.println(staff);
        }
    }

    public int findByName(String name) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;


    }

    public void editByName(String name, Staff staff) {
        index = findByName(name);
        staffList.set(index, staff);
        writeFile(staffList,PATH);
    }

    public void removeByName(String name) {
        index = findByName(name);
        staffList.remove(index);
    }

    public void updateStaffStatus(String name) {
        index = findByName(name);
        boolean status = staffList.get(index).isStatus();
        status = !status;
        staffList.get(index).setStatus(status);
    }

    public int calSalaryFulltime() {
        int sum = 0;
        for (Staff staff :
                staffList) {
            if (staff.isType()) {
                sum += staff.getSalary();
            }
        }
        return sum;
    }

    public int calSalaryParttime() {
        int sum = 0;
        for (Staff staff :
                staffList) {
            if (!staff.isType()) {
                sum += staff.getSalary();
            }
        }
        return sum;
    }

    public int calSalary() {
        int sum = 0;
        for (Staff staff :
                staffList) {
            sum += staff.getSalary();
        }
        return sum;
    }

    public void filterFulltimeStaff() {
        for (Staff staff :
                staffList) {
            if (staff.isType()) {
                System.out.println(staff);
            }
        }
    }

    public void filterParttimeStaff() {
        for (Staff staff :
                staffList) {
            if (!staff.isType()) {
                System.out.println(staff);
            }
        }
    }

}
