package entity;

import javax.persistence.*;

@Entity
@Table(name = "term_discipline")
public class Term_discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int id_term;
    private int id_discipline;

    public Term_discipline() {
    }

    public Term_discipline(int id, int id_term, int id_discipline) {
        Id = id;
        this.id_term = id_term;
        this.id_discipline = id_discipline;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_term() {
        return id_term;
    }

    public void setId_term(int id_term) {
        this.id_term = id_term;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }
}
