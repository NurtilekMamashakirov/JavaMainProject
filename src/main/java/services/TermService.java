package services;

import entity.Discipline;
import entity.Term;

import java.util.List;

public class TermService {
    public static Term getTermById(List<Term> terms, String termId) {
        for (Term t: terms) {
            if(String.valueOf(t.getId()).equals(termId))
                return t;
        }
        return null;
    }
}
