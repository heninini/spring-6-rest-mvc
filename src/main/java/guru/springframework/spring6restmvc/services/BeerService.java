package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional <BeerDTO> getById(UUID Id);
    List <BeerDTO> listBeers();

    BeerDTO save(BeerDTO beer);

    void updateBeer(BeerDTO beer, UUID beerId);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}