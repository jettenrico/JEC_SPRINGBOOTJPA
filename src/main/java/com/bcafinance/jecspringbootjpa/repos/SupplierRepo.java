package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SupplierRepo extends JpaRepository<Suppliers,Long> {

    Optional<Suppliers> findByEmail(String email);
    Optional<Suppliers> findSuppliersByCompanyName(String name);
    List<Suppliers> findByCompanyNameContaining(String name);
    List<Suppliers> findBySupervisorNameNotContaining(String name);
    List<Suppliers> findByCompanyNameStartsWith(String name);
    List<Suppliers> findBySupervisorNameEndsWith(String name);
//    @Query(value = )

//    Optional<Suppliers> deleteSuppliersById(int id);
//
//    List<Suppliers> searchCompanyByNameStartsWith(String name);

//    List<Suppliers> findSuppliersByCompanyNameContains("PT");
//    List<Products> findByNameNotContaining(String name);

//    @Query("SELECT p FROM Products p WHERE p.description LIKE %:description%")
//    List<Products> searchByDescriptionLike(@Param("description") String description);

//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
//    List<Products> findByNameNotLike(String name);

//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
//    List<Products> searchByNameStartsWith(String name);
//    @Query("SELECT p FROM Products p WHERE p.Name LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
//    List<Products> searchByNameEndsWith(String name)
}
