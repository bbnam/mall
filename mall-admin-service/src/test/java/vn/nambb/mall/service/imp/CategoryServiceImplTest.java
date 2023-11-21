package vn.nambb.mall.service.imp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.entity.CategoryEntity;
import vn.nambb.mall.repository.CategoryRepository;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Method under test: {@link CategoryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryEntity actualById = categoryServiceImpl.getById(1L);
        verify(categoryRepository).findById(Mockito.<Long>any());
        assertSame(categoryEntity, actualById);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() {
        Optional<CategoryEntity> emptyResult = Optional.empty();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(CRuntimeException.class, () -> categoryServiceImpl.getById(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() {
        when(categoryRepository.findById(Mockito.<Long>any())).thenThrow(new CRuntimeException("An error occurred"));
        assertThrows(CRuntimeException.class, () -> categoryServiceImpl.getById(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }
}
