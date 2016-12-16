package jschool2016.translator.repository;


import jschool2016.translator.entity.Pair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface PairRepository extends CrudRepository<Pair, Long> {

    @Query("select p.priority ||' '|| count(*) from Pair p group by p.priority")
    List<String> getPrioritiesList();

}
