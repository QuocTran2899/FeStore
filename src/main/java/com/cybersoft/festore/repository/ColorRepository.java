package com.cybersoft.festore.repository;

import com.cybersoft.festore.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,Integer> {

}
