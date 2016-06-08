/**
 * Created by Siclait on 7/6/16.
 */
package BlogService;

import org.hibernate.QueryException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ORManager<T> {

    //Attributes
    private static EntityManagerFactory emf;
    private Class<T> classEntity;

    public ORManager(Class<T> classEntity){

        if(emf ==  null)
            emf = Persistence.createEntityManagerFactory("PersistenceUnit");

        this.classEntity = classEntity;
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    // Class Methods
    public void Create(T entity){

        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        try
        {
            em.persist(entity);
            em.getTransaction().commit();

        } catch (EntityNotFoundException exp){
            System.out.println("Entity ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw  exp;
        } catch (TransactionRequiredException exp) {
            System.out.println("Transaction ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw  exp;
        } catch (PersistenceException exp){
            System.out.println("Persistence ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw  exp;
        } catch (Exception exp){
            System.out.println("General ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw  exp;
        } finally{
            em.close();
        }
    }

    public void Edit(T entity) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        try {
            em.merge(entity);
            em.getTransaction().commit();

        } catch (EntityNotFoundException exp) {
            System.out.println("Entity ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (TransactionRequiredException exp) {
            System.out.println("Transaction ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (PersistenceException exp) {
            System.out.println("Persistence ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } finally {
            em.close();
        }
    }

    public void Delete(T entity){

        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();

        } catch (EntityNotFoundException exp) {
            System.out.println("Entity ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (TransactionRequiredException exp) {
            System.out.println("Transaction ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (PersistenceException exp) {
            System.out.println("Persistence ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } finally {
            em.close();
        }

    }

    public T Find(Object id) {

        EntityManager em = getEntityManager();

        try {
            return em.find(classEntity, id);

        } catch (EntityNotFoundException exp) {
            System.out.println("Entity ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (PersistenceException exp) {
            System.out.println("Persistence ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } finally {
            em.close();
        }
    }

    public List<T> FindAll() {

        EntityManager em = getEntityManager();

        try {
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(classEntity);
            criteriaQuery.select(criteriaQuery.from(classEntity));

            return em.createQuery(criteriaQuery).getResultList();

        } catch (EntityNotFoundException exp) {
            System.out.println("Entity ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (QueryTimeoutException exp) {
            System.out.println("Query Timeout ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        }catch (QueryException exp) {
            System.out.println("Query ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch ( PersistenceException exp) {
            System.out.println("Persistence ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
            em.getTransaction().rollback();
            throw exp;
        } finally {
            em.close();
        }
    }
}
