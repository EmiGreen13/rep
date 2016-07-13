package service;

import entity.Category;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryDao {

    @PersistenceContext(unitName = "Medicine")
    private EntityManager em;

    public void save(Category category){
        em.persist(category);
    }

    public Category findById(int id){
        List categories = em.createQuery(
                "SELECT c FROM category c WHERE c.id = :id")
                .setParameter("id", id)
                .getResultList();
        return categories.size() > 0 ?  (Category)categories.get(0) : null;
    }

    public void update(Category category){
        Category category1 = em.merge(category);
        em.persist(category1);
    }

    public void delete(int id) throws NullPointerException{
        em.remove(findById(id));
    }

    public List findAll(){
        return em.createQuery("select c from category c").getResultList();
    }
}
