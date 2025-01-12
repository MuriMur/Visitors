package org.visitors.models_and_repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}