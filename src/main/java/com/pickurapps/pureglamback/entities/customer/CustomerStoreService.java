package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceCommentDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.dtos.customer.PhotoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "customer_store_service")
public class CustomerStoreService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;
    // add rating functionality private Float rating;
    private Date addedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    @PrePersist
    public void setDefaultAddedDate() {
        if (addedDate == null) {
            addedDate = new Date();
        }
    }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CustomerStoreServiceComment> comments = Collections.emptySet();

    public void addComment(CustomerStoreServiceComment comment) {
        comments.add(comment);
    }



    @ManyToOne
    private CustomerStore store;

    public CustomerStoreServiceDto getCustomerStoreServiceDto() {
        CustomerStoreServiceDto customerStoreServiceDto = new CustomerStoreServiceDto();
        customerStoreServiceDto.setId(id);
        customerStoreServiceDto.setName(name);
        customerStoreServiceDto.setDescription(description);
        customerStoreServiceDto.setPrice(price);
        customerStoreServiceDto.setAddedDate(addedDate);

        if (!photos.isEmpty()) {
            List<PhotoDto> photosDtoList = new ArrayList<>();
            for (Photo storeServicePhoto : photos) {
                photosDtoList.add(storeServicePhoto.getPhotoDto());
            }
            customerStoreServiceDto.setPhotos(photosDtoList);
        }

        if (!comments.isEmpty()) {
            Set<CustomerStoreServiceCommentDto> commentsListDto = new HashSet<>();
            for (CustomerStoreServiceComment storeComment : comments) {
                commentsListDto.add(storeComment.getCustomerStoreServiceCommentDto());
            }
            customerStoreServiceDto.setComments(commentsListDto);
        }
        customerStoreServiceDto.setStoreId(store.getId());

        return customerStoreServiceDto;
    }

}
