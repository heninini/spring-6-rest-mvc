package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mapper.BeerMapper;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Primary

public class BeerServiceImplJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;


    @Override
    public Optional <BeerDTO> getById(UUID Id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(Id).orElse(null)));
    }

    @Override
    public List<BeerDTO> listBeers() {

        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDTO save(BeerDTO beer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public void updateBeer(BeerDTO beer, UUID beerId) {

    }


    @Override
    public void deleteBeerById(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}