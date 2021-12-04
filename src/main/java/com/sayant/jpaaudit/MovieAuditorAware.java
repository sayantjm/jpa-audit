package com.sayant.jpaaudit;

import com.sayant.jpaaudit.domain.User;
import com.sayant.jpaaudit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class MovieAuditorAware implements AuditorAware<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(userRepository.findByName("admin"));
    }
}
