package jschool2016.translator.service;


import com.google.common.collect.Lists;
import jschool2016.translator.dto.PairDTO;
import jschool2016.translator.entity.Pair;
import jschool2016.translator.repository.MyRepository;
import jschool2016.translator.repository.PairRepository;
import jschool2016.translator.service.Utils.Parser;
import jschool2016.translator.service.Utils.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class PairService {

    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private MyRepository myRepository;

    @Transactional
    public List<Pair> getAll(){
        return Lists.newArrayList(pairRepository.findAll());
    }

    @Transactional
    public Pair save(Pair pair){
        pair.setPriority(5);
        return pairRepository.save(pair);
    }

    @Transactional
    public List <Pair> save(List<PairDTO> pairs){
        List<Pair> result = new ArrayList<Pair>();
        for (PairDTO pairDTO : pairs){
            Pair pair = new Pair();
            pair.setEnWord(pairDTO.getEnWord());
            pair.setRuWord(pairDTO.getRuWord());
            pair.setPriority(5);
            save(pair);
            result.add(pair);
        }
        return result;
    }

    @Transactional
    public void delete(Long id){
        pairRepository.delete(id);
    }

    @Transactional
    public List<Pair> getRandomPairsExceptOfIds(int count, List<Long> idsList){
        String idsStr = "";
        for (Long l : idsList){
            idsStr += l + ", ";
        }
        idsStr = idsStr.substring(0, idsStr.length() - 2);
        List<String> prioritiesStr = myRepository.getPrioritiesListExceptOfIds(idsStr);
        List<Long> priorities = Parser.parseprioritiesStringListToIntList(prioritiesStr);
        int[] counts = Selector.selectPairs(count, priorities);
        List<Pair> res = new ArrayList<Pair>();
        for (int i = 0; i < 10; i++){
            if (counts[i]!=0)
                res.addAll(myRepository.findPairsByPriorityAndLimitExceptOfIds(counts[i], i+1, idsStr));
        }
        return res;
    }

    @Transactional
    public List<Pair> getRandomPairs(int count){
        List<String> prioritiesStr = pairRepository.getPrioritiesList();
        List<Long> priorities = Parser.parseprioritiesStringListToIntList(prioritiesStr);
        int[] counts = Selector.selectPairs(count, priorities);
        List<Pair> res = new ArrayList<Pair>();
        for (int i = 0; i < 10; i++){
            res.addAll(myRepository.findPairsByPriorityAndLimit(counts[i], i+1));
        }
        return res;
    }

    @Transactional
    public List<Pair> getByIds (List<Long> ids){
        List<Pair> res = new ArrayList<Pair>();
        res.addAll(Lists.newArrayList(pairRepository.findAll(new ArrayList(ids))));
        return res;
    }

    @Transactional
    public void update (List<Pair> pairs){
        for (Pair p : pairs)
            pairRepository.save(p);
    }
}
