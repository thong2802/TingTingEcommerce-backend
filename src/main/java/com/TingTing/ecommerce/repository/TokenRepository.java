package com.TingTing.ecommerce.repository;

import com.TingTing.ecommerce.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

}
