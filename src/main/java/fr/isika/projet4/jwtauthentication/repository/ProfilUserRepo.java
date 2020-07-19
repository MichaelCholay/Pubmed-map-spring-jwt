package fr.isika.projet4.jwtauthentication.repository;

import org.springframework.data.repository.CrudRepository;

import fr.isika.projet4.jwtauthentication.model.ProfilUser;

public interface ProfilUserRepo extends CrudRepository<ProfilUser, Long>{

}
