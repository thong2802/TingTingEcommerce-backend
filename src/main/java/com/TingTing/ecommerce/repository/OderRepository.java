package com.TingTing.ecommerce.repository;

import com.TingTing.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<Order, Integer> {
}
