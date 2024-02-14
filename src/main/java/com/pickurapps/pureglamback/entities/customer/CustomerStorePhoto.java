package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStorePhotoDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_store_photo")
public class CustomerStorePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longblob")
    private byte[] photo;

    public CustomerStorePhotoDto getCustomerStorePhotoDto() {
        CustomerStorePhotoDto customerStorePhotoDto = new CustomerStorePhotoDto();
        customerStorePhotoDto.setId(id);
        customerStorePhotoDto.setReturnedPhoto(photo);

        return customerStorePhotoDto;
    }

}
