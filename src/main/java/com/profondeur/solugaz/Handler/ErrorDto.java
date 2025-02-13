package com.profondeur.solugaz.Handler;

import com.profondeur.solugaz.Exceptions.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

        private Integer httpcode;
        private ErrorCodes code;
        private String message;
        private List<String> errors = new ArrayList<>();

}
