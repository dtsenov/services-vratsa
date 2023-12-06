package bg.softuni.servicesvratsa.utils;

import bg.softuni.servicesvratsa.model.view.InvoiceModelView;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;
import bg.softuni.servicesvratsa.service.OrderService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceGenerationScheduler {

    private static final String FILE_PATH = "src/main/resources/files/";

    private final OrderService orderService;

    public InvoiceGenerationScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void generateInvoices() {
        String today = String.valueOf(LocalDate.now());
        List<OrderViewModel> allDailyOrders = orderService.findAllDailyOrders();

        if (allDailyOrders.isEmpty()) {
            InvoiceWriter.writeToJsonFileNoOrders("ДНЕС НЯМА ПОРЪЧКИ.", FILE_PATH + "invoice-" + today + ".json");
        } else {
            List<InvoiceModelView> invoices = new ArrayList<>();


            allDailyOrders.forEach(orderViewModel -> {
                InvoiceModelView invoice = new InvoiceModelView();
                invoice.setOrderId(orderViewModel.getProductId());
                invoice.setCustomerName(orderViewModel.getUsername());
                invoice.setAmount(orderViewModel.getQuantity());
                invoice.setPrice(orderViewModel.getPrice());
                invoice.setName(orderViewModel.getName());

                invoices.add(invoice);
            });

            InvoiceWriter.writeToJsonFile(invoices, FILE_PATH + "invoice-" + today + ".json");
        }
    }
}
