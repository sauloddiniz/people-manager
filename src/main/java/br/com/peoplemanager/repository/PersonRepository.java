package br.com.peoplemanager.repository;

import br.com.peoplemanager.entity.Person;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long>,
        JpaSpecificationExecutor<Person> {

    static Specification<Person> containsHasName(List<String> names) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String name : names) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%"));
            }
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
