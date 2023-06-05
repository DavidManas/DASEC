package com.dasec.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasec.spring.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
