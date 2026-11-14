package in.ashokit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SHIPPING_ADDRESS")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addrId;

    private String houseNum;

    private String city;

    private String state;

    private String zipcode;

    private String addrType;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
