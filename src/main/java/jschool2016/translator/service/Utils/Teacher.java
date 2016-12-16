package jschool2016.translator.service.Utils;

import jschool2016.translator.entity.Pair;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    public static List<Pair> getInCorrectAnswers(List<Pair> pairs, List<String> answers){
        List<Pair> res = new ArrayList<Pair>();
        for (int i = 0; i < pairs.size(); i++){
            Pair p = pairs.get(i);
            if (p.getEnWord().equals(answers.get(i))){
                p.setPriority(p.getPriority() - 1);
            }
            else{
                p.setPriority(p.getPriority() + 1);
                res.add(p);
            }
        }
        return res;
    }
}
