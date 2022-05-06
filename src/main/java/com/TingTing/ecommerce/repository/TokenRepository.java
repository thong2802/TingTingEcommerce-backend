package com.TingTing.ecommerce.repository;

import com.TingTing.ecommerce.model.AuthenticationToken;
import com.TingTing.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
}
