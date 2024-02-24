package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceCommentDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.dtos.customer.PhotoDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.entities.customer.CustomerStoreService;
import com.pickurapps.pureglamback.entities.customer.CustomerStoreServiceComment;
import com.pickurapps.pureglamback.entities.customer.Photo;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreRepository;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreServiceRepository;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerStoreServiceServiceImpl implements CustomerStoreServiceService{

    private final CustomerStoreRepository customerStoreRepository;
    private final CustomerStoreServiceRepository customerStoreServiceRepository;
    private final CustomerUserRepository customerUserRepository;
    @Override
    public List<CustomerStoreServiceDto> getAllStoreServices(Long storeId) {
        return customerStoreServiceRepository.findAllByStoreId(storeId).stream().map(CustomerStoreService::getCustomerStoreServiceDto).collect(Collectors.toList());

    }

    @Override
    public CustomerStoreServiceDto getStoreServiceById(Long storeServiceId) {
        Optional<CustomerStoreService> optionalCustomerStoreService = customerStoreServiceRepository.findById(storeServiceId);
        return optionalCustomerStoreService.map(CustomerStoreService::getCustomerStoreServiceDto).orElse(null);
    }

    @Override
    public boolean addStoreService(CustomerStoreServiceDto customerStoreServiceDto, Long customerId) throws IOException {
        Optional<CustomerUser> optionalCustomer = customerUserRepository.findById(customerId);
        Optional<CustomerStore> customerStore = customerStoreRepository.findById(customerStoreServiceDto.getStore());
        if (optionalCustomer.isPresent() && customerStore.isPresent()) {
            CustomerStoreService customerStoreService = new CustomerStoreService();
            customerStoreService.setName(customerStoreServiceDto.getName());
            customerStoreService.setPrice(customerStoreServiceDto.getPrice());
            customerStoreService.setDescription(customerStoreServiceDto.getDescription());
            customerStoreService.setStore(customerStore.get());

            // TODO: CONSIDER IMPLEMENTING ADD STORE PHOTO GALLERY ON ITS OWN
            if(customerStoreServiceDto.getPhotos() != null && (!customerStoreServiceDto.getPhotos().isEmpty())) {
                List<Photo> photos = new ArrayList<>();
                for (PhotoDto photoDto : customerStoreServiceDto.getPhotos()) {
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
                customerStoreService.setPhotos(photos);
            }

            customerStoreServiceRepository.save(customerStoreService);
            return true;
        }

        return false;

    }

    @Override
    public boolean updateStoreService(Long storeServiceId, CustomerStoreServiceDto customerStoreServiceDto) throws IOException {
        Optional<CustomerStoreService> customerStoreService = customerStoreServiceRepository.findById(storeServiceId);
        if (customerStoreService.isPresent()) {
            CustomerStoreService existingService = customerStoreService.get();
            existingService.setName(customerStoreServiceDto.getName());
            existingService.setDescription(customerStoreServiceDto.getDescription());
            existingService.setPrice(customerStoreServiceDto.getPrice());
            if (!customerStoreServiceDto.getComments().isEmpty()) {
                Set<CustomerStoreServiceComment> commentsList = new HashSet<>();
                for (CustomerStoreServiceCommentDto commentDto : customerStoreServiceDto.getComments()) {
                    CustomerStoreServiceComment comment = new CustomerStoreServiceComment();
                    comment.setComment(commentDto.getComment());

                    Optional<CustomerUser> commentUser = customerUserRepository.findById(commentDto.getUserId());
                    commentUser.ifPresent(comment::setCustomerUser);

                    commentsList.add(comment);
                }

                existingService.setComments(commentsList);
            }

            // TODO: CONSIDER IMPLEMENTING UPDATE STORE PHOTO GALLERY ON ITS OWN
            if(customerStoreServiceDto.getPhotos() != null && (!customerStoreServiceDto.getPhotos().isEmpty())) {
                List<Photo> photos = new ArrayList<>();
                for (PhotoDto photoDto : customerStoreServiceDto.getPhotos()) {
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
                existingService.setPhotos(photos);
            }

            customerStoreServiceRepository.save(existingService);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStoreSerice(Long storeServiceId) {
        Optional<CustomerStoreService> optionalCustomerStoreService = customerStoreServiceRepository.findById(storeServiceId);
        if (optionalCustomerStoreService.isPresent()) {
            customerStoreServiceRepository.deleteById(storeServiceId);
            return true;
        }
        return false;
    }
}
