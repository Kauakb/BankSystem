package model;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    private String department;
    private String managerType;
    private String jobTitle;
    private String company;

    //constructor
    public Employee(String firstName, String lastName, String email, double salary, String department, String managerType,
                    String jobTitle, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.managerType = managerType;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    //full name method
    public String getFullName() {
        return firstName + " " + lastName;
    }

//standard getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() { return email; }
    public double getSalary() {return salary; }
    public String getDepartment() { return department; }
    public String getManagerType() { return managerType; }
    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }


    // method for output giving information the user has given
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getFullName() + '\'' +
                ", department='" + department + '\'' +
                ", manager='" + managerType + '\'' +
                ", job title='" + jobTitle + '\'' +
                ", salary =" + salary +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee other = (Employee) o;
        // consider two employees equal if full name and email match
        return this.getFullName().equalsIgnoreCase(other.getFullName())
                && this.email.equalsIgnoreCase(other.email);
    }

    @Override
    public int hashCode() {
        int result = getFullName().toLowerCase().hashCode();
        result = 31 * result + (email == null ? 0 : email.toLowerCase().hashCode());
        return result;
    }
}