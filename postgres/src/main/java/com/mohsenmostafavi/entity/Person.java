package com.mohsenmostafavi.entity;

import javax.persistence.*;

@Entity
@Table(name = "person", uniqueConstraints ={
        @UniqueConstraint(columnNames = {"national_code"}, name = "UK_ACCOUNT_GROUP_CODE")
})
@NamedQueries({
        @NamedQuery(name = "findPersons", query = "select person from Person person order by person.lastName"),
        @NamedQuery(name = "findPersonById", query = "select person from Person person where person.id=:id"),
        @NamedQuery(name = "findPersonByNationalCode", query = "select person from Person person where person.nationalCode=:nationalCode")
})
public class Person extends AbstractBaseEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_code")
    private String nationalCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }



}
