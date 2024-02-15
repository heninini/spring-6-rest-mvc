package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


    List<CustomerDTO> listCustomers();
    CustomerDTO getById(UUID Id);

    CustomerDTO saveCustomer(CustomerDTO customer);

    void deleteById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDTO customer);

    void updateCustomerById(CustomerDTO customer, UUID customerId);
}