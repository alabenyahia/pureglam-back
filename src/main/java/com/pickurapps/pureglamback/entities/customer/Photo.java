package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.PhotoDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_store_photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longblob")
    private byte[] photo;

    public PhotoDto getPhotoDto() {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(id);
        photoDto.setReturnedPhoto(photo);

        return photoDto;
    }

}
