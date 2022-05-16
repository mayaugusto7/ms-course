package br.com.mayaugusto.hrworker.resources;

import br.com.mayaugusto.hrworker.entities.Worker;
import br.com.mayaugusto.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "workers")
public class WorkerResource {
    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Value("${test.config}")
    private String testConfig;
    private final Environment env;

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerResource(Environment env, WorkerRepository workerRepository) {
        this.env = env;
        this.workerRepository = workerRepository;
    }

    @GetMapping(value = "configs")
    public ResponseEntity<Void> getConfigs() {
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workers = workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping( value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        /* Test Hystrix timeout
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } */

        logger.info("PORT: " + env.getProperty("local.server.port"));

        Worker worker = workerRepository.findById(id).get();
        return ResponseEntity.ok(worker);
    }

}
