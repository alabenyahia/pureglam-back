package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.dtos.customer.PhotoDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.entities.customer.Photo;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreRepository;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerStoreServiceImpl implements CustomerStoreService{

    private final CustomerStoreRepository customerStoreRepository;
    private final CustomerUserRepository customerUserRepository;

    @Override
    public List<CustomerStoreDto> getAllStoresByCustomerId(Long customerId) {
        return customerStoreRepository.findAllByCustomerUserId(customerId).stream().map(CustomerStore::getCustomerStoreDto).collect(Collectors.toList());
    }

    @Override
    public boolean addStore(CustomerStoreDto customerStoreDto, Long customerId) throws IOException {
        Optional<CustomerUser> optionalCustomer = customerUserRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {

            CustomerStore store = new CustomerStore();

            store.setName(customerStoreDto.getName());
            store.setBrandColor(customerStoreDto.getBrandColor());
            store.setCustomerUser(optionalCustomer.get());

            // TODO: CONSIDER IMPLEMENTING ADD STORE PHOTO GALLERY ON ITS OWN
            if(customerStoreDto.getPhotos() != null && (!customerStoreDto.getPhotos().isEmpty())) {
                List<Photo> photos = new ArrayList<>();
                for (PhotoDto photoDto : customerStoreDto.getPhotos()) {
                    Photo photo = new Photo();
                    try {
                        photo.setPhoto(photoDto.getPhoto().getBytes());
                        photos.add(photo);

                        //TODO: MAYBE I NEED TO ADD IT TO REPO FIRST: CHECK LATER

                    } catch (Exception e) {
                        System.out.println("error while parsing customer store photo");
                        throw new IOException();
                    }
                }
                store.setPhotos(photos);
            }
            //CANT ADD A SERVICE AT THE TIME YOU CREATE A NEW STORE

            customerStoreRepository.save(store);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStore(Long storeId, CustomerStoreDto customerStoreDto) throws IOException {
        Optional<CustomerStore> optionalCustomerStore = customerStoreRepository.findById(storeId);
        if (optionalCustomerStore.isPresent()) {
            CustomerStore existingCustomerStore = optionalCustomerStore.get();
            existingCustomerStore.setName(customerStoreDto.getName());
            existingCustomerStore.setBrandColor(customerStoreDto.getBrandColor());

            // TODO: CONSIDER IMPLEMENTING UPDATE STORE PHOTO GALLERY ON ITS OWN
            if(customerStoreDto.getPhotos() != null && (!customerStoreDto.getPhotos().isEmpty())) {
                List<Photo> photos = new ArrayList<>();
                for (PhotoDto photoDto : customerStoreDto.getPhotos()) {
                    Photo photo = new Photo();
                    try {
                        photo.setPhoto(photoDto.getPhoto().getBytes());
                        photos.add(photo);

                        //TODO: MAYBE I NEED TO ADD IT TO REPO FIRST: CHECK LATER

                    } catch (Exception e) {
                        System.out.println("error while parsing customer store photo");
                        throw new IOException();
                    }
                }
                existingCustomerStore.setPhotos(photos);
            }

            customerStoreRepository.save(existingCustomerStore);
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteStore(Long storeId) {
        Optional<CustomerStore> optionalCustomerStore = customerStoreRepository.findById(storeId);
        if (optionalCustomerStore.isPresent()) {
            customerStoreRepository.deleteById(storeId);
            return true;
        }
        return false;
    }

    @Override
    public CustomerStoreDto getStoreById(Long storeId) {
        Optional<CustomerStore> optionalCustomerStore = customerStoreRepository.findById(storeId);
        return optionalCustomerStore.map(CustomerStore::getCustomerStoreDto).orElse(null);
    }
}
