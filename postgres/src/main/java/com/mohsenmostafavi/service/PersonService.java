package com.mohsenmostafavi.service;

import com.mohsenmostafavi.dao.PersonDao;
import com.mohsenmostafavi.entity.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PersonService {
    @Inject
    PersonDao personDao;

    public List<Person> findPersons() {
        try {
            return personDao.findPersons();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Person findPersonByNationalCode(String nationalCode){
        try {
            return personDao.findPersonByNationalCode(nationalCode);
        }catch (Exception e){
            return new Person();
        }
    }

    public void create(Person person) throws Exception {
        personDao.create(person);
    }

    public void edit(Person person) throws Exception {
        personDao.edit(person);
    }

    public void delete(Long id) throws Exception {
        Person person = personDao.findById(id);
        personDao.delete(person);
    }

    public List<Person> findPersonByDetails(Person person) throws Exception{
        return personDao.findPersonByDetails(person);
    }
}
