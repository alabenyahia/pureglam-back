package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStorePhotoDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.entities.customer.CustomerStorePhoto;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreRepository;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreServiceRepository;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerStoreServiceImpl implements CustomerStoreService{

    private final CustomerStoreRepository customerStoreRepository;
    private final CustomerStoreServiceRepository customerStoreServiceRepository;
    private final CustomerUserRepository customerUserRepository;

    @Override
    public List<CustomerStoreDto> getAllStoresByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public boolean addStore(CustomerStoreDto customerStoreDto) throws IOException {
        Optional<CustomerUser> optionalCustomer = customerUserRepository.findById(customerStoreDto.getCustomerUserId());

        if (optionalCustomer.isPresent()) {

            CustomerStore store = new CustomerStore();

            store.setName(customerStoreDto.getName());
            int[] storeDtoColor = customerStoreDto.getBrandColor();
            store.setBrandColor(new Color(storeDtoColor[0], storeDtoColor[1], storeDtoColor[2]));
            store.setCustomerUser(optionalCustomer.get());

            if(!customerStoreDto.getPhotos().isEmpty()) {
                List<CustomerStorePhoto> photos = new ArrayList<>();
                for (CustomerStorePhotoDto photoDto : customerStoreDto.getPhotos()) {
                    CustomerStorePhoto photo = new CustomerStorePhoto();
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
            int[] storeDtoColor = customerStoreDto.getBrandColor();
            existingCustomerStore.setBrandColor(new Color(storeDtoColor[0], storeDtoColor[1], storeDtoColor[2]));

            if(!customerStoreDto.getPhotos().isEmpty()) {
                List<CustomerStorePhoto> photos = new ArrayList<>();
                for (CustomerStorePhotoDto photoDto : customerStoreDto.getPhotos()) {
                    CustomerStorePhoto photo = new CustomerStorePhoto();
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
