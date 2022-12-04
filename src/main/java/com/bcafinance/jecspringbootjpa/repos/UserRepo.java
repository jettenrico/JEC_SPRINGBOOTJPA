package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<Users,Long> {

    List<Users> findByFullNameContaining(String fullname);
    List<Users> findUsersByTokenLike(String token);
    Optional<Users> findByEmail(String email);
}
