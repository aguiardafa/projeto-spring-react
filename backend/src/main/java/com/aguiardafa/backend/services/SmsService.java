package com.aguiardafa.backend.services;

import com.aguiardafa.backend.entities.Sale;
import com.aguiardafa.backend.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SaleRepository repository;

    public void sendSmsTest() {
        String msg = "Teste Envio de SMS";
        sendSms(msg);
    }

    public void sendSmsSale(Long saleId) {
        Optional<Sale> saleOptional = repository.findById(saleId);
        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            String saleDate = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
            String msg = "O Vendedor " + sale.getSellerName() +
                    " foi destaque em " + saleDate +
                    " com um total de R$ " + String.format("%.2f", sale.getAmount());
            sendSms(msg);
        } else {
            // Venda não encontrada
            // TODO - implementar tratamento de exceção
            System.out.println("Venda de Id: " + saleId + " não encontrada!!!");
        }
    }

    private void sendSms(String msg){
        Twilio.init(twilioSid, twilioKey);
        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
        Message message = Message.creator(to, from, msg).create();

        System.out.println("SMS enviado - MsgId: " + message.getSid() + "msgText: " + msg);
    }
}
