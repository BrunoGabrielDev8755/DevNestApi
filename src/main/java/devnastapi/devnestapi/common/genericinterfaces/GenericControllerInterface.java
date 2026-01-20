package devnastapi.devnestapi.common.genericinterfaces;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericControllerInterface {

     default URI generateHeaderLocation(UUID id){
         return ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("{/id}")
                 .buildAndExpand()
                 .toUri();
    }
}
