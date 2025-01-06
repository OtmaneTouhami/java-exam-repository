package ma.enset.javaexamrepository.dao;

import ma.enset.javaexamrepository.entity.PlatPrincipal;

import java.util.List;

public interface PlatPrincipalDAO {
    PlatPrincipal ajouter(PlatPrincipal platPrincipal);
    PlatPrincipal modifier(PlatPrincipal platPrincipal);
    void supprimer(int id);
    PlatPrincipal findById(int id);
    List<PlatPrincipal> getAll();
}
