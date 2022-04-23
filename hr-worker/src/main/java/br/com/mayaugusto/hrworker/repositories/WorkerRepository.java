package br.com.mayaugusto.hrworker.repositories;

import br.com.mayaugusto.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
