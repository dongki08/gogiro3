package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.PickupMenuEntity;
import com.green.gogiro.entity.butcher.PickupMenuIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupMenuRepository extends JpaRepository<PickupMenuEntity, PickupMenuIds> {

}
