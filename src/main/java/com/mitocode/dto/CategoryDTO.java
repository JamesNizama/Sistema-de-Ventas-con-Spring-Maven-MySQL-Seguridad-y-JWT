package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer idCategory;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 50, message = "min 3 y max 50 characters")
    private String nameofCategory;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 150, message = "min 3 y max 50 characters")
    private String descriptionCategory;

    @NotNull
    private boolean enabledCategory;

//    @Min(value = 1)
//    @Max(value = 150)
//    private short age;
//
//    @Email
//    private String email;
//
//    @Pattern(regexp = "[0-9]*") // Expresión regular.
//    private String phoneNumber;
}
