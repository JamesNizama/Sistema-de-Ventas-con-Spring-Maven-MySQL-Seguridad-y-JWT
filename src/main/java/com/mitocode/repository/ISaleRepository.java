package com.mitocode.repository;


import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleRepository extends IGenericRepository<Sale, Integer> {

    @Query(value = "call mito_sales.fn_sales_count()", nativeQuery = true)
    List<Object[]> callProcedure1();

    @Query(value = "call mito_sales.fn_sales_count()", nativeQuery = true)
    List<IProcedureDTO> callProcedure2();

    @Query(name = "Sale.fn_sale", nativeQuery = true)
    List<ProcedureDTO> callProcedure3();

    @Procedure(procedureName = "update_enabled_sales")
    void callProcedure4();
}
