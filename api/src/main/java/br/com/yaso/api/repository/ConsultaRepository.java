package br.com.yaso.api.repository;

import br.com.yaso.api.model.Alergia;
import br.com.yaso.api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
