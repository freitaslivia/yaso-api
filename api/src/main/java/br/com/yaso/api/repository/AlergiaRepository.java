package br.com.yaso.api.repository;

import br.com.yaso.api.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlergiaRepository extends JpaRepository<Alergia, Long> {
}
