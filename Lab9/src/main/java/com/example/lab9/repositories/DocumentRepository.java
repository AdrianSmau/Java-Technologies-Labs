package com.example.lab9.repositories;

import com.example.lab9.entities.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named("DocumentRepository")
@ApplicationScoped
@Transactional
public class DocumentRepository {
    @PersistenceContext(unitName = "lab-7")
    EntityManager em;

    public void delete(int id) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createNativeQuery("delete from documents where id=(?)").setParameter(1, id).executeUpdate();
        et.commit();
    }

    public void update(String documentName, String documentContent, int oldId) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        if (documentName != null && documentName.length() > 0) {
            em.createNativeQuery("update documents set name=(?) where id=(?)").setParameter(1, documentName).setParameter(2, oldId).executeUpdate();
        }
        if (documentContent != null && documentContent.length() > 0) {
            em.createNativeQuery("update documents set content=(?) where id=(?)").setParameter(1, documentContent).setParameter(2, oldId).executeUpdate();
        }
        et.commit();
    }

    public void save(Document document) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(document);
        et.commit();
    }

    public List<Document> getAll() {
        return em.createNativeQuery("SELECT * FROM documents", Document.class).getResultList();
    }
}
