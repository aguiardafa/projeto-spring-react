package com.aguiardafa.backend.controllers;

import com.aguiardafa.backend.entities.Sale;
import com.aguiardafa.backend.services.SaleService;
import com.aguiardafa.backend.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public Page<Sale> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                Pageable pageable){
        return service.findSales(minDate, maxDate, pageable);
    }

    @GetMapping("/notification")
    public void notifySms(){
        smsService.sendSmsTest();
    }

    @GetMapping("/{saleId}/notification")
    public void notifySms(@PathVariable Long saleId){
        smsService.sendSmsSale(saleId);
    }
}
