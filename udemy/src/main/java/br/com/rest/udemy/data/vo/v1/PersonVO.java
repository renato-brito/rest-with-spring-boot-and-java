package br.com.rest.udemy.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonPropertyOrder({"address", "firstName", "lastName", "id", "gender"})
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

   private Long id;
   private String firstName;
   private String lastName;
   private String address;
   private String gender;

    public PersonVO(){}
}
