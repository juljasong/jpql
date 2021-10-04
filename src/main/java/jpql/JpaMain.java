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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("TeamA");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //String query = "SELECT m FROM Member m INNER JOIN m.team t";
            //String query = "SELECT m FROM Member m LEFT [OUTER] JOIN m.team t";
            //String query = "SELECT m FROM Member m, Team t WHERE m.username = t.name";
            //String query = "SELECT m FROM Member m LEFT JOIN m.team t ON t.name = 'TeamA'";
            String query = "SELECT m FROM Member m LEFT JOIN Team t ON m.username = t.name";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            System.out.println("==== " + resultList.size());

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
