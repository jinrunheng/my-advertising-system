package com.github.ad.dao;

import com.github.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 完成对 AdUser 的增删改查
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    AdUser findByUsername(String username);


}
