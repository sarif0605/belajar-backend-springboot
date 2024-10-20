package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "region") // Tanpa penggunaan mappedBy, kita harus menuliskan kueri JOIN antara tabel
                                    // Region dan Country secara manual, yang membutuhkan waktu dan usaha yang lebih
                                    // banyak.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // digunakan untuk menandai bahwa properti countries hanya
                                                           // dapat ditulis (write-only), tetapi tidak dapat dibaca
                                                           // (read-only) ketika objek Region dikonversi menjadi JSON
    private List<Country> countries;

}
