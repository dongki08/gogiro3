package com.green.gogiro.butchershop;

import com.green.gogiro.entity.butcher.ButcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ButcherRepository extends JpaRepository<ButcherEntity,Long> {
}
