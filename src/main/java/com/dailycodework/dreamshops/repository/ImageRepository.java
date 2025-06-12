package com.dailycodework.dreamshops.repository;

import com.dailycodework.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
