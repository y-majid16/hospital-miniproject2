package id.co.indivara.miniproject.hospital.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
public class ResponseData<T>{

    private boolean status;

    private List<String> messages = new ArrayList<>();

    private T data;

}
