package br.com.mayaugusto.hrpayroll.services;

import br.com.mayaugusto.hrpayroll.entities.Payment;
import br.com.mayaugusto.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(long workerId, int days) {
        Map<String, String> uriAttributes = new HashMap<>();
        uriAttributes.put("id", String.valueOf(workerId));

        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriAttributes);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
