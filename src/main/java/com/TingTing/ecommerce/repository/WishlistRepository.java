package com.TingTing.ecommerce.repository;

import com.TingTing.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    List<Wishlist>findAllByUserIdOrderByCreatedDateDesc(Integer user);
}
