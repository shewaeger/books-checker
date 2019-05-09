package com.github.shewaeger.bookschecker.repositories.specification;

import com.github.shewaeger.bookschecker.entity.User;
import com.github.shewaeger.bookschecker.filters.SimpleFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final SimpleFilter filter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
