package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
public interface IDisciplineRepository extends JpaRepository<DisciplineEntity,Long> {
    public Optional<DisciplineEntity> findBySlug(String slugToFind);
}
