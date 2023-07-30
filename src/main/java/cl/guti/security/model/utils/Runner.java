package cl.guti.security.model.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import cl.guti.security.model.Authority;
import cl.guti.security.model.User;
import cl.guti.security.repository.AuthorityRepository;
import cl.guti.security.repository.UserRepository;

@Component
public class Runner implements CommandLineRunner{

	private final AuthorityRepository authorityRepository;
	private final UserRepository userRepository;
	
	public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}
	
	public void run(String... args) throws Exception {
		if(this.authorityRepository.count()==0) {
			this.authorityRepository.saveAll(List.of(
					new Authority(AuthorityName.ADMIN),
					new Authority(AuthorityName.READ),
					new Authority(AuthorityName.WRITE)
					));
		}
		
		if(this.userRepository.count()==0) {
			var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			this.userRepository.saveAll(List.of(
					new User("uncledave", encoders.encode("UncleDave123"), List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())),
					new User("guti", encoders.encode("qwerty123"), List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())),
					new User("write", "qwerty1234", List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get())),
					new User("read", "qwerty", List.of(this.authorityRepository.findByName(AuthorityName.READ).get()))
					));
		}
	}
}
