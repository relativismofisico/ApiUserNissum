package org.nisum.nisumapi.service;

import java.util.List;
import java.util.Optional;

import org.nisum.nisumapi.dto.UserDTORequest;
import org.nisum.nisumapi.model.User;

public interface IUserService {

	public User insert(UserDTORequest userDTO);
	public Optional<User> findById(Long idUser);
	public List<User> findAll();
	public Optional<User> findByEmail(String email);
}
