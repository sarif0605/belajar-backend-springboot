package id.co.mii.serverapp.models;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // //Id otomatis dengan auto increment
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "employee_name", length = 50, nullable = false)
    private String name;

    @Column(name = "employee_email", unique = true, nullable = false)
    private String email;

    @Column(name = "employee_phone", length = 13)
    private Integer phone;

    // ====== using foreign key ======
    // @OneToOne(mappedBy = "employee")
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    /// ==== using primary key =====
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

}
