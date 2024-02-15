package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entity.Beer;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerTestIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;


    @Test
    void testGetByIdNotFoundException(){
        assertThrows(NotFoundException.class,()->{
            beerController.getById(UUID.randomUUID());
        });

    }
    @Test
    void testGetBeerById(){
        Beer beer= beerRepository.findAll().get(0);

        BeerDTO dto = beerController.getById(beer.getId());

        assertThat(dto).isNotNull();

    }
    @Test
    void testListBeer(){

        List<BeerDTO> dtos= beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void  testEmptyList(){
        beerRepository.deleteAll();

        List<BeerDTO> dtos=beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(0);

    }
   @Test
    void testSaveBeer(){
        BeerDTO beerDTO=BeerDTO.builder()
                .beerName("asmaraa").build();
       ResponseEntity responseEntity=beerController.saveNewBeer(beerDTO);

       assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
       assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

       String[] locationUUID=responseEntity.getHeaders().getLocation().getPath().split("/");
       UUID savedUUID=UUID.fromString(locationUUID[4]);

       Optional<Beer> beer=beerRepository.findById(savedUUID);
       assertThat(beer).isNotNull();

   }
}