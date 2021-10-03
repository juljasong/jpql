package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex1");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //code
            Member member = new Member();
            member.setUsername("Song");
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery("SELECT m FROM Member m where m.username =: username", Member.class)
                    .setParameter("username", "Song")
                    .getSingleResult();

            System.out.println(singleResult.getUsername());

//            Member result = query.getSingleResult();
//            System.out.println("result = " + result.getUsername());

            tx.commit();
            //code
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
