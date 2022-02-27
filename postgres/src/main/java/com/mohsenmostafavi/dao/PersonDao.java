package com.mohsenmostafavi.dao;

import com.mohsenmostafavi.entity.Person;
import com.mohsenmostafavi.entity.Person_;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequestScoped
public class PersonDao extends GenericDAO<Person> {
    @Inject
    protected EntityManager entityManager;

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

    public Person findPersonByNationalCode(String nationalCode) throws Exception {
        Query query = entityManager.createNamedQuery("findPersonByNationalCode");
        query.setParameter("nationalCode", nationalCode);
        return (Person) query.getSingleResult();
    }

    public List<Person> findPersons() {
        Query query = entityManager.createNamedQuery("findPersons");
        return query.getResultList();
    }

    public Person findById(Long id) {
        Query query = entityManager.createNamedQuery("findPersonById");
        query.setParameter("id", id);
        return (Person) query.getSingleResult();
    }

    public List<Person> findPersonByDetails(Person person) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);
        criteriaQuery.select(personRoot);
        Predicate predicate = builder.conjunction();

        if (person != null) {
            if (person.getFirstName() != null && !person.getFirstName().isEmpty()) {
                predicate = builder.and(predicate, builder.equal(personRoot.get(Person_.firstName), person.getFirstName()));
            }
            if (person.getLastName() != null && !person.getLastName().isEmpty()) {
                predicate = builder.and(predicate, builder.equal(personRoot.get(Person_.lastName), person.getLastName()));
            }
        }
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(builder.asc(personRoot.get(Person_.lastName)));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
