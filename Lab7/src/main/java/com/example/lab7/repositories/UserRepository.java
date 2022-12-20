package com.example.lab7.repositories;

import com.example.lab7.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Named("UserRepository")
@ApplicationScoped
@Transactional
public class UserRepository {
    @PersistenceContext(unitName = "lab-7")
    EntityManager em;

    public void save(User user) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(user);
        et.commit();
    }

    public Optional<User> getByUsername(String username) {
        Object obj;
        try {
            obj = em.createNativeQuery("SELECT * FROM users WHERE username = (?)", User.class).setParameter(1, username).getSingleResult();
        } catch (NoResultException ex) {
            return Optional.empty();
        }
        return Optional.of((User) obj);
    }

    public Optional<User> getByEmail(String email) {
        Object obj;
        try {
            obj = em.createNativeQuery("SELECT * FROM users WHERE email = (?)", User.class).setParameter(1, email).getSingleResult();
        } catch (NoResultException ex) {
            return Optional.empty();
        }
        return Optional.of((User) obj);
    }
}
