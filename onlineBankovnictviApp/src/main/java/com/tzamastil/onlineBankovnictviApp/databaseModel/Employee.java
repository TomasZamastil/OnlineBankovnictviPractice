package com.tzamastil.onlineBankovnictviApp.databaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String name;
    private String password;

    public Employee() {
    }

    public Employee(String name, String password) {
        this.name = name;
        this.password = TotallySecureEncoder.encodePassword(password);
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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
