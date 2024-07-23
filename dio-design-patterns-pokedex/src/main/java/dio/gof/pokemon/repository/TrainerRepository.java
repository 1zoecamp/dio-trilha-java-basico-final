package dio.gof.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.gof.pokemon.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{

}
