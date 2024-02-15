package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
       private Map<UUID, CustomerDTO> customerMap;
       public CustomerServiceImpl(){
           this.customerMap= new HashMap<>();

           CustomerDTO customer1= CustomerDTO.builder()
                   .customerName("henos")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();

           CustomerDTO customer2= CustomerDTO.builder()
                   .customerName("lidya")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();
           CustomerDTO customer3= CustomerDTO.builder()
                   .customerName("ghirmay")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();

           customerMap.put(customer1.getId(),customer1);
           customerMap.put(customer2.getId(),customer2);
           customerMap.put(customer3.getId(),customer3);
       }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO getById(UUID Id) {
        return customerMap.get(Id);
    }



    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        CustomerDTO customerSaved= CustomerDTO.builder()
                .customerName(customer.getCustomerName())
                .Id(UUID.randomUUID())
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .version(new BigDecimal(1))
                .build();
        customerMap.put(customerSaved.getId(),customerSaved);
        return customerSaved;
    }



    @Override
    public void updateCustomerById(CustomerDTO customer, UUID customerId) {
        CustomerDTO existingCustomer= customerMap.get(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setVersion(customer.getVersion());

    }


    @Override
    public void deleteById(UUID customerId){
           customerMap.remove(customerId);
    }


}