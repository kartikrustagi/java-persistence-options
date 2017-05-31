import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate-jpa");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select s from Student s");
        System.out.println(query.getResultList());
        Student s = new Student();
        //s.setID(1L);
        s.setName("Test name");
        entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();
        System.out.println(entityManager.createQuery("select st from Student st").getResultList());
    }
}
