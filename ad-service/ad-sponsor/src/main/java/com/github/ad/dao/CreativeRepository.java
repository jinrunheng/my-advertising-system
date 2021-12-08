package com.github.ad.dao;

import com.github.ad.entity.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 完成对 Creative 的增删改查
 */
public interface CreativeRepository extends JpaRepository<Creative, Long> {
}
