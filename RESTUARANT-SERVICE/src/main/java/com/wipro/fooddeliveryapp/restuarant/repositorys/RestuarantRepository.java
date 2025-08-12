package com.wipro.fooddeliveryapp.restuarant.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.fooddeliveryapp.restuarant.entitys.Restuarant;

@Repository
public interface RestuarantRepository extends JpaRepository<Restuarant, Long> {

}
