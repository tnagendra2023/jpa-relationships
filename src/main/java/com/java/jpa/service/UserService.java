package com.java.jpa.service;

import com.java.jpa.dto.AddressDTORequest;
import com.java.jpa.dto.UserDTORequest;
import com.java.jpa.dto.UserDTOResponse;
import com.java.jpa.entity.Address;
import com.java.jpa.entity.User;
import com.java.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTOResponse addUser(UserDTORequest userDTORequest){
       User user =new User(userDTORequest.getUserName());
        List<AddressDTORequest> requestAddress = userDTORequest.getAddress();
        List<Address> addresses=new ArrayList<>();

        if(!CollectionUtils.isEmpty(requestAddress)){
            requestAddress.forEach(addressDTORequest -> {
                Address address =new Address(addressDTORequest.getStreet(),user);
                addresses.add(address);
            });

        }
        user.setAddresses(addresses);
        userRepository.save(user);
        List<String> streets = getStreets(user);
        return UserDTOResponse.builder()
                .userName(user.getName())
                .street(streets)
                .build();
    }

    public List<UserDTOResponse> fetchAllUsersAndAddresses() {
        List<UserDTOResponse> userDTOResponses = new ArrayList<>();
        /* When we are calling findAll we are getting N+1 problem
           Let's say user have 2 address now user will make one query
           and Address will fire 2 times for the corresponding user
           total calls will 2+1 i.e, 3
        *  List<User> users = userRepository.findAll();
        */
        List<User> users = userRepository.findAllUsersAndAddress();
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(user -> {
                String name = user.getName();
                UserDTOResponse userDTOResponse = UserDTOResponse.builder()
                        .userName(name)
                        .street(getStreets(user))
                        .build();
                userDTOResponses.add(userDTOResponse);
            });
        }
        return userDTOResponses;
    }

    public void deleteUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            userRepository.deleteById(userId);
        }
    }

    private static List<String> getStreets(User user) {
        List<Address> addresses = user.getAddresses();
        List<String> streets=new ArrayList<>();
        addresses.forEach(address -> streets.add(address.getStreet()));
        return streets;
    }
}
