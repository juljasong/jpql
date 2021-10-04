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
            member.setUsername("관리자");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            //String query = "SELECT COALESCE(m.username, '이름 없는 회원') FROM Member m";
            String query = "SELECT NULLIF(m.username, '관리자') FROM Member m"; // 사용자 이름이 '관리자'이면 null, 나머지는 본인의 이름

            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for(String s : resultList) {
                System.out.println(s);
            }

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
