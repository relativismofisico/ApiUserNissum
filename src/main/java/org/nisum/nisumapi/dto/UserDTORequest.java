package org.nisum.nisumapi.dto;

import lombok.*;
import org.nisum.nisumapi.model.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORequest implements Serializable {

    private static final long serialVersionUID = 1L;

	@NonNull
    private String name;

    @Email
    @NonNull
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @NonNull
    public String password;

    private List<Phone> phones;
}
