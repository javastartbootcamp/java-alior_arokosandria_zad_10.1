package pl.javastart.streamsexercise;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DateTimeProvider dateTimeProvider;

    PaymentService(PaymentRepository paymentRepository, DateTimeProvider dateTimeProvider) {
        this.paymentRepository = paymentRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    /*
    Znajdź i zwróć płatności posortowane po dacie rosnąco
     */
    List<Payment> findPaymentsSortedByDateAsc() {
        return paymentRepository.findAll().stream().sorted(Comparator.comparing(Payment::getPaymentDate)).collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności posortowane po dacie malejąco
     */
    List<Payment> findPaymentsSortedByDateDesc() {
        return paymentRepository.findAll().stream().sorted(Comparator.comparing(Payment::getPaymentDate).reversed()).collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności posortowane po liczbie elementów rosnąco
     */
    List<Payment> findPaymentsSortedByItemCountAsc() {
        return paymentRepository.findAll().stream()
                .sorted(Comparator.comparingInt(payment -> payment.getPaymentItems().size()))
                .collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności posortowane po liczbie elementów malejąco
     */
    List<Payment> findPaymentsSortedByItemCountDesc() {

        return paymentRepository.findAll().stream()
                .sorted(Comparator.comparingInt((Payment payment) -> payment.getPaymentItems().size()).reversed())
                .collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności dla wskazanego miesiąca
     */
    List<Payment> findPaymentsForGivenMonth(YearMonth yearMonth) {

        return paymentRepository.findAll().stream()
                .filter(month -> YearMonth.from(month.getPaymentDate()).equals(yearMonth)).collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności dla aktualnego miesiąca
     */
    List<Payment> findPaymentsForCurrentMonth() {
        return paymentRepository.findAll().stream()
                .filter(month -> YearMonth.from(month.getPaymentDate()).equals(YearMonth.from(dateTimeProvider.zonedDateTimeNow()))).collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności dla ostatnich X dni
     */
    List<Payment> findPaymentsForGivenLastDays(int days) {
        return paymentRepository.findAll().stream()
                .filter(month -> month.getPaymentDate()
                        .isAfter(dateTimeProvider.zonedDateTimeNow().minusDays(days))).collect(Collectors.toList());
    }

    /*
    Znajdź i zwróć płatności z jednym elementem
     */
    Set<Payment> findPaymentsWithOnePaymentItem() {
        return  paymentRepository.findAll().stream()
                .filter(payment -> payment.getPaymentItems().size()==1)
                .collect(Collectors.toSet());
    }

    /*
    Znajdź i zwróć nazwy produktów sprzedanych w aktualnym miesiącu
     */
    Set<String> findProductsSoldInCurrentMonth() {
        throw new RuntimeException("Not implemented");
    }

    /*
    Policz i zwróć sumę sprzedaży dla wskazanego miesiąca
     */
    BigDecimal sumTotalForGivenMonth(YearMonth yearMonth) {
        throw new RuntimeException("Not implemented");
    }

    /*
    Policz i zwróć sumę przyznanych rabatów dla wskazanego miesiąca
     */
    BigDecimal sumDiscountForGivenMonth(YearMonth yearMonth) {
        throw new RuntimeException("Not implemented");
    }

    /*
    Znajdź i zwróć płatności dla użytkownika z podanym mailem
     */
    List<PaymentItem> getPaymentsForUserWithEmail(String userEmail) {
        throw new RuntimeException("Not implemented");
    }

    /*
    Znajdź i zwróć płatności, których wartość przekracza wskazaną granicę
     */
    Set<Payment> findPaymentsWithValueOver(int value) {
        throw new RuntimeException("Not implemented");
    }

}
