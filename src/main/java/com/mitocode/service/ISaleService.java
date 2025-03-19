package com.mitocode.service;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

import java.util.List;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale, Integer> {
    List<ProcedureDTO> callProcedure1();
    List<IProcedureDTO> callProcedure2();
    List<ProcedureDTO> callProcedure3();
    void callProcedure4();
    Sale getSaleMostExpensive(); // Obtener la venta mayor
    String getBestSellerPerson(); // Obtener el UserName del mejor vendedor;
    Map<String, Long> getSaleCountBySeller();//Contar la cantidad de ventas por vendedor
    Map<String, Double> getMostSellerProduct(); // Obtener el producto más vendido

}
