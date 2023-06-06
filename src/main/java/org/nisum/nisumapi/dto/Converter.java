package org.nisum.nisumapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nisum.nisumapi.model.User;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Converter {

    private final ModelMapper modelMapper;

    public UserDTOResponse userToDTO (User user) {
        return modelMapper.map(user, UserDTOResponse.class);
    }

    public User userDTORequestToUser (UserDTORequest request) {
        return modelMapper.map(request, User.class);
    }
}
