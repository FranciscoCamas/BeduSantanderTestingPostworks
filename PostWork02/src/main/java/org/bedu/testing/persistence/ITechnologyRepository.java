package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
public interface ITechnologyRepository extends JpaRepository<TechnologyEntity , Long> {
    public Optional<TechnologyEntity> findBySlug(String slugToFind);
}
