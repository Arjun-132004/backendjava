// Deployment Contract §2 — auto-added by deploy_my_app.
// Kubernetes readiness/liveness probes hit /health. Do NOT gate this on
// beans, DB, or downstream services — /health only proves the JVM is up
// and Spring is serving HTTP. Add /ready if you need a dependency-checked
// signal.
package io.okami101.realworld;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeepAgentHealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }
}
