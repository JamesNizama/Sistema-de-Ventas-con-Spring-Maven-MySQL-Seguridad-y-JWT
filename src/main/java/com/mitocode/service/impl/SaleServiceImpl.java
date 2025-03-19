package com.mitocode.service.impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Product;
import com.mitocode.model.Sale;
import com.mitocode.model.SaleDetail;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.ISaleRepository;
import com.mitocode.service.ISaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public Sale getSaleMostExpensive() {
        //SQL -> select max(s.total) as Total from sale s;
        return repository.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        //SQL ->
        // select sum(s.total) as suma_ventas, ud.username
        //	from sale s
        //    inner join user ud on s.id_user = ud.id_user
        //    group by ud.username;

//        Map<String, Double> byUser = repository.findAll()
        var byUser = repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(s -> s.getUser().getUsername(),
                        Collectors.summingDouble(Sale::getTotal)));
        log.info(byUser.toString());

        String key =
                Collections.max(byUser.entrySet(),
                        Comparator.comparingDouble(e -> e.getValue())).getKey();
        log.info(key);
        return key;
    }

    @Override
    public Map<String, Long> getSaleCountBySeller() {
        // SQL ->
        // select
        //	count(*) as cantidad,
        //    ud.username
        //	from sale s
        //    inner join user ud on s.id_user = ud.id_user
        //    group by ud.username;

        Map<String, Long> saleCount = repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getUser().getUsername(),Collectors.counting()));
        log.info(saleCount.toString());

        return saleCount;
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        // SQL ->
        // select p.name, sum(sd.quantity) as cantidad
        //		from sale_detail sd
        //        inner join product p on sd.id_product = p.id_product
        //        group by p.name;
        Stream<Sale> saleStream = repository.findAll().stream();
        Stream<List<SaleDetail>> lsStream = saleStream.map(Sale::getDetails); // e -> e.getDetails()

    //[ [dev1, dev2],[dev3, dev4],[dev5, dev6] ]
    //[ [dev1, dev2, dev3, dev4, dev5, dev6] ]
        Stream<SaleDetail> streamDetail = lsStream.flatMap(Collection::stream); // e -> e.stream()

        Map<String, Double> byProduct = streamDetail.
                collect(Collectors.groupingBy(e -> e.getProduct().getName(), Collectors.summingDouble(SaleDetail::getQuantity)));
        log.info(byProduct.toString());

        Map<String, Double> orderDesc =
                byProduct.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValuer) -> oldValue, LinkedHashMap::new//() -> new LinkedHashMap<>()
                        ));

        return orderDesc;
    }
}
