package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.ResponseDto;
import com.TingTing.ecommerce.dto.user.SignInDto;
import com.TingTing.ecommerce.dto.user.SignInResponseDto;
import com.TingTing.ecommerce.dto.user.SignUpDto;
import com.TingTing.ecommerce.exceptions.AuthenticationFailException;
import com.TingTing.ecommerce.exceptions.CustomException;
import com.TingTing.ecommerce.model.AuthenticationToken;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto) {
        // check if user is already present
      if (Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))) {
            // we have an user
            // throw an exception
            throw new CustomException("the user already present");
      }
        // hash the password
        String encrytedPassword = signUpDto.getPassword();
        try {
            encrytedPassword = hashPassWord(signUpDto.getPassword());
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        // save the user
        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(), encrytedPassword );
        userRepository.save(user);
        // create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created successfully");
        return responseDto;
    }

    private String hashPassWord(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }


    public SignInResponseDto signIn(SignInDto signInDto) {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user id not valid");
        }
        // hash the password
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassWord(signInDto.getPassword()))){
                // passowrd doesnot match
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // compare the password in DB

        // if password match
        AuthenticationToken token = authenticationService.getToken(user);
        // retrive the token;
        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }
        // return response
        return new SignInResponseDto("Success", token.getToken());
    }
}
