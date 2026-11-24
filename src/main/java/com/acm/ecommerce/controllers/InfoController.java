package com.acm.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Expone qué versión de la aplicación está corriendo en producción.
 * El pipeline de CD consulta este endpoint para verificar el despliegue.
 */
@Tag(name = "Info", description = "Versión y commit desplegados")
@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    private static final Instant STARTED_AT = Instant.now();

    private final String version;
    private final String commit;

    public InfoController(@Value("${app.version}") String version,
                          @Value("${app.commit}") String commit) {
        this.version = version;
        this.commit = commit;
    }

    @Operation(summary = "Versión desplegada", description = "Devuelve versión, commit y hora de arranque.")
    @GetMapping
    public Map<String, String> info() {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("app", "ACM E-commerce");
        body.put("version", version);
        body.put("commit", commit);
        body.put("startedAt", STARTED_AT.toString());
        return body;
    }
}
