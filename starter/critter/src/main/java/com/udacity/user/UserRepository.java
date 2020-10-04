package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
//@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    //    @PersistenceContext
//    EntityManager entityManager;
//
//    public void persist(Customer customer) {
//        entityManager.persist(customer);
//    }
//
//    public Customer findCustomer(Long id) {
//        return entityManager.find(Customer.class, id);
//    }
//
//    public Customer merge(Customer customer){
//        return entityManager.merge(customer);
//    }
//
//    public void deleteCustomer(Long id) {
//        Customer customer = entityManager.find(Customer.class, id);
//        entityManager.remove(customer);
//    }
//    public void persist(Employee employee) {
//        entityManager.persist(employee);
//    }
//
//    public Employee find(Long id) {
//        return entityManager.find(Employee.class, id);
//    }
//
//    public Employee merge(Employee employee){
//        return entityManager.merge(employee);
//    }
//
//    public void delete(Long id) {
//        Employee employee = entityManager.find(Employee.class, id);
//        entityManager.remove(employee);
//    }
}
