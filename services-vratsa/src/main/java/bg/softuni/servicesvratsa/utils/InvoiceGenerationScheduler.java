package bg.softuni.servicesvratsa.utils;

import bg.softuni.servicesvratsa.model.view.InvoiceModelView;
import bg.softuni.servicesvratsa.model.view.OrderViewModel;
import bg.softuni.servicesvratsa.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceGenerationScheduler {

    private static final String FILE_PATH = "src/main/resources/files/";

    private final OrderService orderService;

    public InvoiceGenerationScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "* * 16 * * ?")
    public void generateInvoices() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-M-yyyy (H-mm-ss)");
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(customFormat);
        String currentFileName = "invoice_" + today;
        String filePath = FILE_PATH + currentFileName;


        List<OrderViewModel> allDailyOrders = orderService.findAllDailyOrders();

        if (allDailyOrders.isEmpty()) {
            InvoiceWriter.writeToJsonFileNoOrders("ДНЕС НЯМА ПОРЪЧКИ.", filePath);
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

            InvoiceWriter.writeToJsonFile(invoices, filePath);
        }
    }
}
