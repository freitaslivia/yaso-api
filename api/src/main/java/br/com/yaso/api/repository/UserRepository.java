package br.com.yaso.api.repository;

import br.com.yaso.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
/*
    User findTopByOrderByNumeroDesc();


 */

}


