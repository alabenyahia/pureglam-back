package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.dtos.customer.PhotoDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "customer_store")
public class CustomerStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brandColor;
    // add rating functionality private Float rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "customer_user_id", nullable = false)
    private CustomerUser customerUser;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CustomerStoreService> services = Collections.emptySet();

    public void addService(CustomerStoreService service) {
        services.add(service);
        if (service.getStore() != this) {
            service.setStore(this);
        }
    }

    public CustomerStoreDto getCustomerStoreDto() {
        CustomerStoreDto customerStoreDto = new CustomerStoreDto();
        customerStoreDto.setId(id);
        customerStoreDto.setName(name);

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
            List<PhotoDto> photosDtoList = new ArrayList<>();
            for (Photo storePhoto : photos) {
                photosDtoList.add(storePhoto.getCustomerStorePhotoDto());
            }
            customerStoreDto.setPhotos(photosDtoList);
        }

        return customerStoreDto;

    }

    //TODO: ADD [lat, long] for store's map location

}
