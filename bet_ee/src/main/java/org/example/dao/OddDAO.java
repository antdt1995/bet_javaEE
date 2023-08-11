package org.example.dao;

import org.example.entity.Odd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class OddDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public Odd save(Odd odd) {
         em.persist(odd);
        return odd;
    }
    public List<Odd> getAll(){
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Odd> cq = cr.createQuery(Odd.class);
        Root<Odd> root = cq.from(Odd.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
    public Optional<Odd> findById(Long id){
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Odd> cq = cr.createQuery(Odd.class);
        Root<Odd> root = cq.from(Odd.class);
        cq.select(root);
        cq.where(cr.equal(root.get("id"), id));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }
}
