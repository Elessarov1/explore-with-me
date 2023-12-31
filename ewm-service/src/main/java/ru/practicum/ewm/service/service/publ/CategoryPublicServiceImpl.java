package ru.practicum.ewm.service.service.publ;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.controller.advice.exception.NotFoundException;
import ru.practicum.ewm.service.dto.category.CategoryResponseDto;
import ru.practicum.ewm.service.entity.Category;
import ru.practicum.ewm.service.repository.CategoryRepository;
import ru.practicum.ewm.service.util.DefaultValues;
import ru.practicum.ewm.service.util.mapper.CategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class CategoryPublicServiceImpl implements CategoryPublicService {
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getCategories(int from, int size) {
        List<Category> categories = categoryRepository.findAll(DefaultValues.createPage(from, size)).getContent();
        return CategoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("no such category"));
        return CategoryMapper.toDto(category);
    }
}
