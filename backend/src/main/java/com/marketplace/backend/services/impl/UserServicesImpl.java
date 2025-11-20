package com.marketplace.backend.services.impl;

import com.marketplace.backend.domain.dto.user.CreateUserDto;
import com.marketplace.backend.domain.dto.user.PreRegisterUserDto;
import com.marketplace.backend.domain.dto.user.ResponseUserDto;
import com.marketplace.backend.domain.entities.Faculty;
import com.marketplace.backend.domain.entities.User;
import com.marketplace.backend.exceptions.faculty.FacultyNotFound;
import com.marketplace.backend.exceptions.user.*;
import com.marketplace.backend.repositories.iFacultyRepository;
import com.marketplace.backend.repositories.iUserRepository;
import com.marketplace.backend.services.iEmailSenderServices;
import com.marketplace.backend.services.iOtpServices;
import com.marketplace.backend.services.iUserServices;
import com.marketplace.backend.utils.mappers.UserMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements iUserServices {

    private final iUserRepository userRepository;
    private final iFacultyRepository facultyRepository;
    private final iOtpServices otpServices;
    private final iEmailSenderServices emailSenderServices;
    private final PasswordEncoder encoder;
    private final UserMappers mappers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void preRegisterUser(PreRegisterUserDto user) {
        String otp = otpServices.generateOtp();
        otpServices.saveOtp(user.getEmail(), otp);
        emailSenderServices.sendEmail(user.getEmail(), otp);
    }

    @Override
    public void registerUser(CreateUserDto user) {
        if (checkUsername(user.getEmail())){
            throw new UserAlreadyExist();
        }

        Faculty faculty = facultyRepository.findFacultyByFacultyName(user.getFaculty());
        if (faculty == null){
            throw new FacultyNotFound();
        }

        if(!otpServices.verifyOtp(user.getEmail(), user.getOtpCode())){
          throw new EmailNotVerify();
        }

        otpServices.deleteOtp(user.getEmail());
        User userEntity = new User();

        userEntity.setName(user.getName());
        userEntity.setUsername(user.getEmail());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setRating(0);
        userEntity.setRole("USER");

        faculty.addUser(userEntity);
        userRepository.save(userEntity);
    }

    @Override
    public ResponseUserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UserNotFound();
        }

        return mappers.castUserData(user);
    }

    @Override
    public void reassignUserByUsername(String username, String role) {
        if(username.equals("admin@uca.edu.sv")){
            throw new ImpossibleAction();
        }

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UserNotFound();
        }

        if(user.getRole().equals(role)){
            throw new RoleAlreadySet();
        }

        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        User user = getUserSession();

        if(!encoder.matches(oldPassword,user.getPassword())){
            throw new UserNoAuthorized();
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changePhoneNumber(String newPhoneNumber) {
        User user = getUserSession();
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
    }

    @Override
    public User getUserSession() {
        User userNoSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userNoSession == null){
            throw new UserNotFound();
        }

        return userRepository.findByUsername(userNoSession.getUsername());
    }

    @Override
    public List<ResponseUserDto> getAllUsers(String role) {
        List<User> users = userRepository.findUsersByRole(role);

        return mappers.getCastedUserList(users);
    }

    @Override
    public ResponseUserDto getUserByEmail(String email) {
        User user =  userRepository.findByUsername(email);

        if (user == null){
            throw new UserNotFound();
        }

        return mappers.castUserData(user);
    }

    @Override
    public List<ResponseUserDto> getUsersByNameContainingIgnoreCase(String name) {
        List<User> users = userRepository.findUsersByNameContainingIgnoreCase(name);

        return mappers.getCastedUserList(users);
    }

    @Override
    public List<ResponseUserDto> getUsersByFaculty(String facultyName) {
        Faculty fac = facultyRepository.findFacultyByFacultyName(facultyName);

        if (fac == null){
            throw new FacultyNotFound();
        }

        List<User> users = userRepository.findUsersByFaculty(fac);

        return mappers.getCastedUserList(users);
    }

    @Override
    public List<ResponseUserDto> getUsersByRating(Integer rating) {
        List<User> users = userRepository.findUsersByRating(rating);

        return mappers.getCastedUserList(users);
    }

    private boolean checkUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
