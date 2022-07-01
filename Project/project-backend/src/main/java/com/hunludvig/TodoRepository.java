package com.hunludvig;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

@Repository
public interface TodoRepository extends JpaRepository<Todo, BigInteger>{
}
