package org.nisum.nisumapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Entity 
@Table(name = "phones")
public class Phone implements Serializable {
  
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPhone;

    @NotNull
    private String number;
    
    @NotNull
    private String citycode;

    @NotNull
    private String countrycode;
    
    public Phone(String number, String citycode, String countrycode, User user) {
    	this.number=number;
    	this.citycode=citycode;
    	this.countrycode=countrycode;
    }
}
