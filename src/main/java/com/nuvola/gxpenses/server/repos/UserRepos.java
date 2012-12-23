package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.server.business.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
