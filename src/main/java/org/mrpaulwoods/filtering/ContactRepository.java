package org.mrpaulwoods.filtering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID>,
        QueryByExampleExecutor<Contact>,
        JpaSpecificationExecutor<Contact> {
}
