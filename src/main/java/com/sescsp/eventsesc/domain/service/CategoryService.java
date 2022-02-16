package com.sescsp.eventsesc.domain.service;

import com.sescsp.eventsesc.domain.exception.CategoryNotFoundException;
import com.sescsp.eventsesc.domain.exception.EntityInUseException;
import com.sescsp.eventsesc.domain.model.Category;
import com.sescsp.eventsesc.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    public static final String MSG_CATEGORY_IN_USE =
            "Categoria de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category save (Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void exclude (Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            categoryRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(categoryId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CATEGORY_IN_USE, categoryId));
        }
    }

    public Category searchOrFail(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow( () -> new CategoryNotFoundException(categoryId));
    }
}
