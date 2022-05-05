package org.bci.app.infraestructure.repositories.jpa;

import org.bci.app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJPARepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
