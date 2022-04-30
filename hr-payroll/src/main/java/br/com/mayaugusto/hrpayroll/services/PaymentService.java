package br.com.mayaugusto.hrpayroll.services;

import br.com.mayaugusto.hrpayroll.entities.Payment;
import br.com.mayaugusto.hrpayroll.entities.Worker;
import br.com.mayaugusto.hrpayroll.foreignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final WorkerFeignClient workerFeignClient;

    @Autowired
    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }
    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
