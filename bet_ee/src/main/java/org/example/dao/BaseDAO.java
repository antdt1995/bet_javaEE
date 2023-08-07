package org.example.dao;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
/**
 * Contract for a generic DAO.
 *
 * @param <E> - Entity type parameter.
 */
@RequiredArgsConstructor
public abstract class BaseDAO<E>  {
    private final Class<E> entityClass;
    @PersistenceContext(name = "betting")
    protected EntityManager em;

    public E create (E entity)  {
        em.persist(entity);
        return entity;
    }
    public E update (E entity) {
        return em.merge(entity);
    }
    public Optional<E> findById (Long id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }
    public List<E> findAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}
