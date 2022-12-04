package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Stores;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StoreRepo extends JpaRepository<Stores,Long> {
    List<Stores> findByStoreNameContaining(String name);


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
