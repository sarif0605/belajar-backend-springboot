package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_is_enabled", nullable = false)
    private boolean isEnabled = true;

    @Column(name = "user_is_account_non_locked")
    private boolean isAccountNonLocked = true;

    // ======= using foreign key =========
    // @OneToOne(cascade = CascadeType.ALL)
    // cascade = CascadeType.ALL adalah parameter yang digunakan untuk menentukan
    // bagaimana operasi yang terjadi pada kelas utama akan mempengaruhi kelas yang
    // terhubung dengannya. Dalam hal ini, CascadeType.ALL menunjukkan bahwa semua
    // operasi (termasuk persist, remove, merge, detach, dan refresh) pada kelas
    // User akan menyebabkan operasi yang sama juga terjadi pada kelas yang
    // terhubung dengannya (Employee).
    // Sebagai contoh, ketika Anda menghapus entitas User, entitas Employee yang
    // terkait dengan entitas tersebut juga akan dihapus secara otomatis.
    // @JoinColumn(name = "employee_id", nullable = false, referencedColumnName =
    // "employee_id")
    // name : untuk memberi nama column foreign key untuk menggabungkan ke tabel
    // employee
    // referencedColumnName : digunakan untuk menentukan nama kolom pada tabel
    // referensi (User) yang akan dijadikan sebagai target foreign key

    // ==== using primary key =====
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    // constructors, getters, setters
}
