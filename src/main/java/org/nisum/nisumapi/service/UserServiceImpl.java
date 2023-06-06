package org.nisum.nisumapi.service;

import lombok.RequiredArgsConstructor;

import org.nisum.nisumapi.dto.Converter;
import org.nisum.nisumapi.dto.UserDTORequest;
import org.nisum.nisumapi.exceptions.ResourceNotFoundException;
import org.nisum.nisumapi.model.Phone;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.repository.UserRepository;
import org.nisum.nisumapi.utils.Properties;
import org.nisum.nisumapi.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
    private  UserRepository userRepository;
    
	@Autowired
	private  Converter converter;
    
	@Autowired
	private  Properties properties;
    
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public User insert(UserDTORequest userDTO) {

        User user = converter.userDTORequestToUser(userDTO);

        user.setToken(Token.generateToken(user));
        user.setPasswordHash(properties.getPassEncrypt());
        user.setActive(true);
       
        userRepository.save(user);

        logger.debug("Informacion creadada para el usuario: {}", user);

        return user;
    }

    public Optional<User> findById(Integer idUser) {

        if (!userRepository.existsById(idUser)) {
            throw new ResourceNotFoundException("El usuario no puede ser encontrado, intente nuevamente o contacte al admin");
        }

        return userRepository.findById(idUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
