package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceCommentDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
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
    private List<Photo> photos;

    @PrePersist
    public void setDefaultAddedDate() {
        if (addedDate == null) {
            addedDate = new Date();
        }
    }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CustomerStoreServiceComment> comments;

    @ManyToOne
    private CustomerStore store;

    public CustomerStoreServiceDto getCustomerStoreServiceDto() {
        CustomerStoreServiceDto customerStoreServiceDto = new CustomerStoreServiceDto();
        customerStoreServiceDto.setId(id);
        customerStoreServiceDto.setName(name);
        customerStoreServiceDto.setDescription(description);
        customerStoreServiceDto.setPrice(price);
        customerStoreServiceDto.setAddedDate(addedDate);

        if (!comments.isEmpty()) {
            Set<CustomerStoreServiceCommentDto> commentsListDto = new HashSet<>();
            for (CustomerStoreServiceComment storeComment : comments) {
                commentsListDto.add(storeComment.getCustomerStoreServiceCommentDto());
            }
            customerStoreServiceDto.setComments(commentsListDto);
        }
        customerStoreServiceDto.setStore(store.getId());

        return customerStoreServiceDto;
    }

}
