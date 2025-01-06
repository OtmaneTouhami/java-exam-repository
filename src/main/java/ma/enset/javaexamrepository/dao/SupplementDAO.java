package ma.enset.javaexamrepository.dao;

import ma.enset.javaexamrepository.entity.Supplement;

import java.util.List;

public interface SupplementDAO {
    Supplement ajouter(Supplement supplement);
    Supplement modifier(Supplement supplement);
    void supprimer(int id);
    Supplement findById(int id);
    List<Supplement> getAll();
}
