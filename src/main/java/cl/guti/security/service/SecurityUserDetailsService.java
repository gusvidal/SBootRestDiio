package cl.guti.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cl.guti.security.SecurityUser;
import cl.guti.security.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	public SecurityUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var optUser  = this.userRepository.findByUsername(username);
		
		if(optUser.isPresent()) {
			return new SecurityUser(optUser.get());
		}
		return (UserDetails) new UsernameNotFoundException("User not found: " + username);
	}

}
