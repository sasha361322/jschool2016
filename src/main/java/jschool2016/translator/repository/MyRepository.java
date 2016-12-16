package jschool2016.translator.repository;

import jschool2016.translator.entity.Pair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class MyRepository {
    @PersistenceContext
    EntityManager em;

    public List<Pair> findPairsByPriorityAndLimitExceptOfIds(int cnt, int priority, String ids) {

        TypedQuery query = em.createQuery("select p" +
                "  from Pair p" +
                "  where p.priority = :priority " +
                "  and p.id not in ("+ids+")"+
                "  ORDER BY RAND()", Pair.class);
        query.setParameter("priority", priority).setMaxResults(cnt);

        return query.getResultList();
    }
    public List<Pair> findPairsByPriorityAndLimit(int cnt, int priority) {

        TypedQuery query = em.createQuery("select p" +
                "  from Pair p" +
                "  where p.priority = :priority " +
                "  ORDER BY RAND()", Pair.class);
        query.setParameter("priority", priority).setMaxResults(cnt);

        return query.getResultList();
    }
//    @Query("select p.priority ||' '|| count(*) from Pair p where id not in (:ids) group by p.priority")
    public List<String> getPrioritiesListExceptOfIds(String ids){
        TypedQuery query = em.createQuery("select p.priority ||' '|| count(*)" +
                "  from Pair p" +
                "  where id not in ("+ids+")"+
                "  group by p.priority", String.class);

        return query.getResultList();
    }
}
