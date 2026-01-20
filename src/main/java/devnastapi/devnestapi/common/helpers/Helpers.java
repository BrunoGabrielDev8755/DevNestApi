package devnastapi.devnestapi.common.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Helpers {

    public UUID idFromString(String id){
        return UUID.fromString(id);
    }



}
