package br.ifsp.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifsp.tasks.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);
}
