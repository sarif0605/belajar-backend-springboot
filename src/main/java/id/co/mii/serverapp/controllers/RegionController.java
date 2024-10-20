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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionService;
import lombok.AllArgsConstructor;

@RestController // Anotasi ini menandakan bahwa class tersebut adalah sebuah controller RESTful
                // API dan setiap method di dalamnya secara otomatis menghasilkan respons HTTP
                // dengan tipe data JSON atau XML.

@RequestMapping("/region") // Anotasi ini menunjukkan bahwa URL untuk API akan dimulai dengan /region atau
                           // anotasi yang menunjukkan bahwa class tersebut menangani permintaan (request)
                           // HTTP dengan URL yang diawali dengan '/region'.

@AllArgsConstructor // Anotasi ini digunakan untuk menghasilkan constructor dengan parameter untuk
                    // semua bidang instance pada kelas. Dalam hal ini, digunakan untuk
                    // menginjeksikan dependensi dari RegionService ke dalam RegionController
@PreAuthorize("hasRole('USER')")
public class RegionController {
    private RegionService regionService;

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id) {
        return regionService.getById(id);
    }

    // http://localhost:9000/region/id => path parameter digunakan untuk merujuk
    // pada sumber daya spesifik yang memiliki identitas unik (seperti id)
    // http://localhost:9000/region?id=1 => query parameter digunakan untuk
    // memberikan opsi pencarian atau filter pada sumber daya

    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionService.create(region);
    }// anotasi @RequestBody juga menunjukkan bahwa data yang dikirimkan oleh klien
     // (dalam format JSON atau XML) akan dimasukkan ke dalam objek Region sebagai
     // argumen method create()

    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region) {
        return regionService.update(id, region);
    }

    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id) {
        return regionService.delete(id);
    }

    // JPQL
    @GetMapping("/jpql")
    public List<Region> searchByName(@RequestParam(name = "name") String name) {
        return regionService.searchByName(name);
    }

    // Native
    @GetMapping("/native/{name}")
    public List<Region> searchByNameNative(@PathVariable String name) {
        return regionService.searchByNameNative(name);
    }

}
