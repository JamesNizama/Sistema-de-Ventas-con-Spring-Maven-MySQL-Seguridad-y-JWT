package com.mitocode.service;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

import java.util.List;

public interface ISaleService extends ICRUD<Sale, Integer> {
    List<ProcedureDTO> callProcedure1();
    List<IProcedureDTO> callProcedure2();
    List<ProcedureDTO> callProcedure3();
    void callProcedure4();

}
