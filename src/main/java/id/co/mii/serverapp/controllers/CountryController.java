package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.request.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CountryController {
    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    // without DTO
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    // with DTO
    @PostMapping("/dto")
    public Country createWithDTO(@RequestBody CountryRequest countryRequest) {
        return countryService.createWithDTO(countryRequest);
    }

    // with DTO and Model Mapper
    @PostMapping("/dto-mm")
    public Country createWithDTOModelMapper(@RequestBody CountryRequest countryRequest) {
        return countryService.createWithDTOModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country update(
            @PathVariable Integer id,
            @RequestBody Country country) {
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryService.delete(id);
    }
}
