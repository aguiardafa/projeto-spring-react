package com.aguiardafa.backend.services;

import com.aguiardafa.backend.entities.Sale;
import com.aguiardafa.backend.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public List<Sale> findSales(){
        return repository.findAll();
    }
}
