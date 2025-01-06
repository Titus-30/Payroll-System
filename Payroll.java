import java.util.*;
import java.util.regex.*;

// Interface: Payroll
interface Payroll {
    double calculatePermanent(double salary);

    double calculateCasual(double salary);

    double calculateContractual(double salary);
}

// Abstract Base Class: Employee
abstract class Employee2 {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int age;
    private String dateOfBirth;
    private String department;
    private int yearsOfExperience;

    public Employee2(int employeeId, String firstName, String lastName, int age, String dateOfBirth, String department, int yearsOfExperience) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
    }

    public static int inputInteger(String prompt, Scanner scanner, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    throw new IllegalArgumentException("Value must be between " + min + " and " + max);
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }
    }

    public static String inputString(String prompt, Scanner scanner, boolean allowOnlyText) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (allowOnlyText && !value.matches("[a-zA-Z\\s]+")) {
                System.out.println("Invalid input. Only text (letters) is allowed.");
                continue;
            }

            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static String inputRegex(String prompt, Scanner scanner, String regex, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (value.matches(regex)) {
                return value;
            }
            System.out.println("Invalid input. " + errorMessage);
        }
    }

    public static String inputDepartment(String prompt, Scanner scanner) {
        List<String> validDepartments = Arrays.asList("Accounting", "Planning", "Strategy", "Human Resources");
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (validDepartments.contains(value)) {
                return value;
            }
            System.out.println("Invalid department. Choose from: " + validDepartments);
        }
    }

    public static double inputSalary(String prompt, Scanner scanner, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value < min || value > max) {
                    throw new IllegalArgumentException("Salary must be between " + min + " and " + max);
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }
    }
}

// Concrete Classes for Employee Types
class PermanentEmployee extends Employee2 implements Payroll {
    public PermanentEmployee(int employeeId, String firstName, String lastName, int age, String dateOfBirth, String department, int yearsOfExperience) {
        super(employeeId, firstName, lastName, age, dateOfBirth, department, yearsOfExperience);
    }

    @Override
    public double calculatePermanent(double salary) {
        double deductions = salary * (0.02 + 0.05 + 0.026);
        return salary - deductions;
    }

    @Override
    public double calculateCasual(double salary) {
        return 0;
    }

    @Override
    public double calculateContractual(double salary) {
        return 0;
    }
}

class CasualEmployee extends Employee2 implements Payroll {
    public CasualEmployee(int employeeId, String firstName, String lastName, int age, String dateOfBirth, String department, int yearsOfExperience) {
        super(employeeId, firstName, lastName, age, dateOfBirth, department, yearsOfExperience);
    }

    @Override
    public double calculateCasual(double salary) {
        double deductions = salary * (0.02 + 0.026);
        return salary - deductions;
    }

    @Override
    public double calculatePermanent(double salary) {
        return 0;
    }

    @Override
    public double calculateContractual(double salary) {
        return 0;
    }
}

class ContractualEmployee extends Employee2 implements Payroll {
    public ContractualEmployee(int employeeId, String firstName, String lastName, int age, String dateOfBirth, String department, int yearsOfExperience) {
        super(employeeId, firstName, lastName, age, dateOfBirth, department, yearsOfExperience);
    }

    @Override
    public double calculateContractual(double salary) {
        double deductions = salary * (0.026 + 0.026);
        return salary - deductions;
    }

    @Override
    public double calculatePermanent(double salary) {
        return 0;
    }

    @Override
    public double calculateCasual(double salary) {
        return 0;
    }
}

// Main Class for Testing
class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employeeType = Employee2.inputInteger("Enter Employee Type (1: Permanent, 2: Casual, 3: Contractual): ", scanner, 1, 3);

        System.out.println("Enter Employee Details:");
        int id = Employee2.inputInteger("Employee ID: ", scanner, 1, Integer.MAX_VALUE);
        String firstName = Employee2.inputString("First Name: ", scanner, true);
        String lastName = Employee2.inputString("Last Name: ", scanner, true);
        int age = Employee2.inputInteger("Age: ", scanner, 20, 65);
        String dob = Employee2.inputRegex("Date of Birth (yyyy-mm-dd): ", scanner, "^\\d{4}-\\d{2}-\\d{2}$", "Date must be in 'yyyy-mm-dd' format.");
        String department = Employee2.inputDepartment("Department: ", scanner);
        int experience = Employee2.inputInteger("Years of Experience: ", scanner, 5, Integer.MAX_VALUE);

        double salary = 0;
        switch (employeeType) {
            case 1:
                salary = Employee2.inputSalary("Enter Salary (1,000,000 to 1,500,000): ", scanner, 1_000_000, 1_500_000);
                break;
            case 2:
                salary = Employee2.inputSalary("Enter Salary (700,000 to 850,000): ", scanner, 700_000, 850_000);
                break;
            case 3:
                salary = Employee2.inputSalary("Enter Salary (200,000 to 400,000): ", scanner, 200_000, 400_000);
                break;
            default:
                System.out.println("Invalid employee type!");
                System.exit(1);
        }

        Employee2 employee = null;
        switch (employeeType) {
            case 1:
                employee = new PermanentEmployee(id, firstName, lastName, age, dob, department, experience);
                System.out.println("Final Salary: " + ((Payroll) employee).calculatePermanent(salary));
                break;
            case 2:
                employee = new CasualEmployee(id, firstName, lastName, age, dob, department, experience);
                System.out.println("Final Salary: " + ((Payroll) employee).calculateCasual(salary));
                break;
            case 3:
                employee = new ContractualEmployee(id, firstName, lastName, age, dob, department, experience);
                System.out.println("Final Salary: " + ((Payroll) employee).calculateContractual(salary));
                break;
        }

        scanner.close();
    }
}
