package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStorePhotoDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceCommentDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.Color;
import java.util.*;

@Entity
@Data
@Table(name = "customer_store")
public class CustomerStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Color brandColor;
    // add rating functionality private Float rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomerStorePhoto> photos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_user_id", nullable = false)
    private CustomerUser customerUser;

    @OneToMany(mappedBy = "customer_store", cascade = CascadeType.ALL)
    private Set<CustomerStoreService> services = Collections.emptySet();

    public CustomerStoreDto getCustomerStoreDto() {
        CustomerStoreDto customerStoreDto = new CustomerStoreDto();
        customerStoreDto.setId(id);
        customerStoreDto.setName(name);

        int[] brandColor = {this.brandColor.getRed(), this.brandColor.getGreen(), this.brandColor.getBlue()};
        customerStoreDto.setBrandColor(brandColor);

        customerStoreDto.setCustomerUserId(customerUser.getId());

        if (!services.isEmpty()) {
            Set<CustomerStoreServiceDto> servicesDtoList = new HashSet<>();
            for (CustomerStoreService storeService : services) {
                servicesDtoList.add(storeService.getCustomerStoreServiceDto());
            }
            customerStoreDto.setServices(servicesDtoList);
        }

        if (!photos.isEmpty()) {
            List<CustomerStorePhotoDto> photosDtoList = new ArrayList<>();
            for (CustomerStorePhoto storePhoto : photos) {
                photosDtoList.add(storePhoto.getCustomerStorePhotoDto());
            }
            customerStoreDto.setPhotos(photosDtoList);
        }

        return customerStoreDto;

    }

    //TODO: ADD [lat, long] for store's map location

}
