package com.mitocode.service.impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.ISaleRepository;
import com.mitocode.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends ICRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepository repository;

    @Override
    protected IGenericRepository<Sale, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> list = new ArrayList<>();
        repository.callProcedure1().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantity(Integer.parseInt(String.valueOf(e[0])));
            dto.setDatetime(String.valueOf(e[1]));
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return repository.callProcedure2();
    }

    @Override
    public List<ProcedureDTO> callProcedure3() {
        return repository.callProcedure3();
    }

    @Override
    public void callProcedure4() {
        repository.callProcedure4();
    }
}
