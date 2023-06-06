package org.nisum.nisumapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nisum.nisumapi.model.Phone;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTOResponse implements Serializable {
    
	
	private static final long serialVersionUID = 1L;

	private Integer idUser;
    private String name;
    private String email;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean active;
    private List<Phone> phones;
}
