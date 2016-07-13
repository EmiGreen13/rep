package service;

import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDao
{
    @PersistenceContext(unitName = "Medicine")
    private EntityManager em;

    public void save(User user)
    {
        em.persist(user);
    }

    public User findByUsername(String username)
    {
        List users = em.createQuery(
                "SELECT c FROM users c WHERE c.username = :username")
                .setParameter("username", username)
                .getResultList();
        return users.size() > 0 ?  (User)users.get(0) : null;
    }

    public List findAll() {
        return em.createQuery("select u from users u").getResultList();
    }

}
