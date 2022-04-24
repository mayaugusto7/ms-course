package br.com.mayaugusto.hrpayroll.resources;

import br.com.mayaugusto.hrpayroll.entities.Payment;
import br.com.mayaugusto.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "payments")
public class PaymentResource {

    private final PaymentService paymentService;

    @Autowired
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        Payment payment = this.paymentService.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }
}
