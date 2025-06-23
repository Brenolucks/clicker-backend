package dev.brenolucks.clicker_backend.service.picsum;

import dev.brenolucks.clicker_backend.domain.model.Picsum;
import dev.brenolucks.clicker_backend.utils.PicsumUtils;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PicsumService {
    private final PicsumUtils picsumUtils;

    public PicsumService(PicsumUtils picsumUtils) {
        this.picsumUtils = picsumUtils;
    }

    public Mono<ResponseEntity<Resource>> consultarPersonagemPorId(){
        return picsumUtils.findPicsumById();
    }

}
