package pl.sda.projectY.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.projectY.dto.PaymentDto;
import pl.sda.projectY.entity.Payment;
import pl.sda.projectY.repository.PaymentRepository;
import pl.sda.projectY.repository.StudentRepository;

/**
 * author:
 * Mateusz
 * Marczak
 **/
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public void addNewPayment(PaymentDto paymentDto) {
        Payment payment = getPayment(paymentDto);
        paymentRepository.save(payment);
    }

    private Payment getPayment(PaymentDto paymentDto) {
        Payment payment  = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setDate(paymentDto.getDate());
        payment.setPaymentId(paymentDto.getPaymentId());
        if(paymentDto.getStudent()!=null) {
            payment.setStudent(studentRepository.findOne(paymentDto.getStudent().getUserId()));
        }
        payment.setType(paymentDto.getType());
        return payment;
    }

    public void deletePaymentById(int paymentId) {
        paymentRepository.delete(paymentId);
    }


    public void editPayment(PaymentDto paymentDto) {
        Payment payment  = paymentRepository.findByPaymentId(paymentDto.getPaymentId());
        payment.setAmount(paymentDto.getAmount());
        payment.setDate(paymentDto.getDate());

        if(paymentDto.getStudent()!=null) {
            payment.setStudent(studentRepository.findOne(paymentDto.getStudent().getUserId()));
        }
        payment.setType(paymentDto.getType());

        paymentRepository.save(payment);
    }
}
