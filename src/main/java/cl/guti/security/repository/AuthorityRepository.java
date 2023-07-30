package cl.guti.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.guti.security.model.Authority;
import cl.guti.security.model.utils.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Optional<Authority> findByName(AuthorityName name);
}
