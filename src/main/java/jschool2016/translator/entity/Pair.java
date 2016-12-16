package jschool2016.translator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Pair {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    private String ruWord;

    private String enWord;

    @Min(1)
    @Max(10)
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority > 10)
            this.priority = 10;
        else if(priority < 1)
            this.priority = 1;
        else this.priority = priority;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuWord() {
        return ruWord;
    }

    public void setRuWord(String ruWord) {
        this.ruWord = ruWord;
    }

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }
}
