package com.example.buysell.repos;

import com.example.buysell.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
