package cn.tendata.crm.data.domain;

import cn.tendata.crm.model.Gender;

import javax.persistence.*;

/**
 * Created by minjay on 2017/2/24.
 */
public class Staff extends AbstractEntityAuditable<Integer> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Department department;

    private int salary;

    private int age;

    private Gender gender;

    @Id
    @GeneratedValue
    public Integer getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public void increaseSalary(int number){
        this.salary+=number;
    }
}
