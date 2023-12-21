package com.tzamastil.onlineBankovnictviApp.databaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long employeeId;
    private String name;
    private String password;

    public static Employee currentLoggedAccount = null;

    public Employee() {
    }

    public Employee(String name, String password) {
        this.name = name;
        this.password = TotallySecureEncoder.encodePassword(password);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return (int) (employeeId ^ (employeeId >>> 32));
    }
}
