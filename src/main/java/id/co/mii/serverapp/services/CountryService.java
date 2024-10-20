package id.co.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.request.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!!!"));
    }

    //without DTO
    public Country create(Country region) {
        return countryRepository.save(region);
    }

    //with DTO
    public Country createWithDTO(CountryRequest countryRequest){
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);
        return countryRepository.save(country);        
    }

    //with DTO and Model Mapper
    public Country createWithDTOModelMapper(CountryRequest countryRequest){
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country); 
    }

    public Country update(Integer id, Country region) {
        getById(id);
        region.setId(id);
        return countryRepository.save(region);
    }

    public Country delete(Integer id) {
        Country region = getById(id);
        countryRepository.delete(region);
        return region;
    }
}
