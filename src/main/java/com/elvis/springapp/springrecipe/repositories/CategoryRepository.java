package com.elvis.springapp.springrecipe.repositories;

import com.elvis.springapp.springrecipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
