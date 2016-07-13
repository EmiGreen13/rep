package service;

import entity.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderDao {

    @PersistenceContext(unitName = "Medicine")
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findById(int id){
        List orders = em.createQuery(
                "SELECT o FROM orders o WHERE o.id = :id")
                .setParameter("id", id)
                .getResultList();
        return orders.size() > 0 ?  (Order)orders.get(0) : null;
    }

    public void delete(int id) throws NullPointerException{
        em.remove(findById(id));
    }

    public List findAll(int userId){
        return em.createQuery("select o from orders o where o.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public List findAll(){
        return em.createQuery("select o from orders o").getResultList();
    }
}
