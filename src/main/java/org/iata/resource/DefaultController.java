package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.ServerInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Server Information")
public class DefaultController {

    @GetMapping(value = "/", produces = JsonLd.MEDIA_TYPE)
    public ServerInformation getServerInformation() {
        return new ServerInformation();
    }
}
