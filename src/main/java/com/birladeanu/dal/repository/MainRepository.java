package com.birladeanu.dal.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by pavel on 9/1/16.
 */
@NoRepositoryBean
public interface MainRepository<T, ID extends Serializable> extends CrudRepository<T, ID>, QueryDslPredicateExecutor<T> {
}
