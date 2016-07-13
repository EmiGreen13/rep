package service;

import entity.Category;
import entity.Drug;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DrugsDao {

    @PersistenceContext(unitName = "Medicine")
    private EntityManager em;

    public void save(Drug drug){
        em.persist(drug);
    }

    public Drug findByCategoryId(int id)
    {
        List drugs = em.createQuery(
                "SELECT d FROM drug d WHERE d.categoryId = :id")
                .setParameter("id", id)
                .getResultList();
        return drugs.size() > 0 ?  (Drug)drugs.get(0) : null;
    }

    public List findByNumber(int id)
    {
        return em.createQuery(
                "SELECT d FROM drug d WHERE d.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public List findByTitle(String title){
        return em.createQuery(
                "SELECT d FROM drug d WHERE d.title = :title")
                .setParameter("title", title)
                .getResultList();
    }


    public List findByNumber(int id, int categoryId)
    {
        return em.createQuery(
                "SELECT d FROM drug d WHERE d.id = :id and d.categoryId = :categoryId")
                .setParameter("id", id)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    public List findByTitle(String title, int categoryId)
    {
        return em.createQuery(
                "SELECT d FROM drug d WHERE d.title = :title and d.categoryId = :categoryId")
                .setParameter("title", title)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    public void update(Category category){
        Category category1 = em.merge(category);
        em.persist(category1);
    }

    public void delete(int id) throws NullPointerException{
        em.remove(findByNumber(id).get(0));
    }

    public ArrayList findAll(){
        return (ArrayList)em.createQuery("select d from drug d").getResultList();
    }


    public ArrayList findAll(int categoryId){
        return (ArrayList)em.createQuery("select d from drug d where d.categoryId = :categoryId")
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}
