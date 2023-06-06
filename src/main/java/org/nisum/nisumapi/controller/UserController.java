package org.nisum.nisumapi.controller;

import lombok.RequiredArgsConstructor;

import org.nisum.nisumapi.dto.Converter;
import org.nisum.nisumapi.dto.UserDTORequest;
import org.nisum.nisumapi.dto.UserDTOResponse;
import org.nisum.nisumapi.exceptions.BadRequestException;
import org.nisum.nisumapi.exceptions.InternalServerErrorException;
import org.nisum.nisumapi.exceptions.ResourceNotFoundException;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.service.UserServiceImpl;
import org.nisum.nisumapi.utils.Properties;
import org.nisum.nisumapi.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserServiceImpl userService;
    
	@Autowired
	private Converter userConverter;
    
	@Autowired
	private Properties properties;
    
	@Autowired
	private Validations validations;

    @GetMapping(value="/all", produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();

        if(users.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        else
        {
            List<UserDTOResponse> usersDTO = users
                    .stream()
                    .map(userConverter::userToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
        }
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Integer idUser) {

        Optional<User> optional = userService.findById(idUser);

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("El usuario $0 no fue encontrado.", idUser));
        }
        return ResponseEntity.ok(optional.get());
    }
    

    @PostMapping(value="/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody UserDTORequest userDTO) {

        if(userDTO.getEmail().isEmpty()) {
            throw new BadRequestException("El email es requerido");
        }

        if (userService.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new InternalServerErrorException("El email ya se encuentra registrado");
        }

        User user = userService.insert(userDTO);
        UserDTOResponse userDTOResponse = userConverter.userToDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOResponse);
    }
}
