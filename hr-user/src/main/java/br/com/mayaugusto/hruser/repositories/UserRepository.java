package br.com.mayaugusto.hruser.repositories;

import br.com.mayaugusto.hruser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
