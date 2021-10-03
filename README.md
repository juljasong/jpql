# JPQL 문법
![img.png](img.png)
- select m from Member as m where m.age > 18
- 엔티티와 속성은 대소문자 구분 O (Member, age)
- JPQL 키워드는 대소문자 구분 X (SELECT, FROM, where)
- 엔티티 이름 사용, 테이블 이름 아님(Member)
- 별칭 필수(m) (as 생략 가능)

### TypeQuery
````java
TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
````
- 반환 타입이 명확할 때

### Query 
````java
Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
````
- 반환 타입이 명확하지 않을 때

### 결과 조회 API
- query.getResultList(): 결과가 하나 이상일 때, 리스트 반환
  - 결과 없으면 빈 리스트 반환
- query.getSingleResult(): 결과가 정확히 하나, 단일 객체 반환
  - 결과 없으면: javax.persistence.NoResultException
  - 둘 이상이면: javax.persistence.NonUniqueResultException

### 파라미터 바인딩
- 이름 기준
````java
SELECT m FROM Member m where m.username=:username
query.setParameter("username", usernameParam);
````
- 위치 기준: 장애 발생 가능성 높음
````java
SELECT m FROM Member m where m.username=?1
query.setParameter(1, usernameParam);
````