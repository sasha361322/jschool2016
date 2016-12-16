package jschool2016.translator.service.Utils;

import jschool2016.translator.entity.Pair;

import java.util.List;

public class Teacher {
    public static List<Pair> getInCorrectAnswers(List<Pair> pairs, List<String> answers){
        for (int i = 0; i < pairs.size(); i++){
            if (pairs.get(i).getEnWord().equals(answers.get(i))){
                pairs.remove(i);
            }
        }
        return pairs;
    }
}
