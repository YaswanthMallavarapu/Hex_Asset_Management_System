package com.service;

import com.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public void insert(Employee employee) {
        em.persist(employee);
    }
}
