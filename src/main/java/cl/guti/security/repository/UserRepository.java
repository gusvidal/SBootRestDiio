package cl.guti.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.guti.security.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional <User> findByUsername(String username); 
}
