package com.pszymanski.library.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@lombok.Getter
@lombok.Setter
public class NewsletterDTO extends BaseDTO {

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    @Email
    private String email;


}
