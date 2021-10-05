package jpql;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex1");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //code

            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);



            //FLUSH 자동 호출 <- commit, query, flush
            int result = em.createQuery("UPDATE Member m set m.age = 20").executeUpdate();
            System.out.println(result);

            em.clear(); // 안하면 영속성 컨텍스트에 남아있는 값 때문에 제대로 동작 X

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            Member findMember3 = em.find(Member.class, member3.getId());

            System.out.println(findMember1.getAge());
            System.out.println(findMember2.getAge());
            System.out.println(findMember3.getAge());

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
