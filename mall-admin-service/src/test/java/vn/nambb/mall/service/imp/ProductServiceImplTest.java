package vn.nambb.mall.service.imp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.dto.ProductParam;
import vn.nambb.mall.entity.CategoryEntity;
import vn.nambb.mall.entity.ProductEntity;
import vn.nambb.mall.repository.ProductRepository;
import vn.nambb.mall.service.CategoryService;
import vn.nambb.mall.util.Snowflake;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private Snowflake snowflake;

    /**
     * Method under test: {@link ProductServiceImpl#create(ProductParam)}
     */
    @Test
    void testCreate() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));
        when(categoryService.getById(Mockito.<Long>any())).thenReturn(categoryEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        when(productRepository.save(Mockito.any())).thenReturn(productEntity);
        when(snowflake.nextId()).thenReturn(1L);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setName("Name");
        int actualCreateResult = productServiceImpl.create(productParam);
        verify(productRepository).save(Mockito.any());
        verify(categoryService).getById(Mockito.<Long>any());
        verify(snowflake).nextId();
        assertEquals(1, actualCreateResult);
    }

    @Test
    void testGetById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ProductEntity actualById = productServiceImpl.getById(1L);
        verify(productRepository).findById(Mockito.<Long>any());
        assertSame(productEntity, actualById);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() {
        Optional<ProductEntity> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(CRuntimeException.class, () -> productServiceImpl.getById(1L));
        verify(productRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() {
        when(productRepository.findById(Mockito.<Long>any())).thenThrow(new CRuntimeException("An error occurred"));
        assertThrows(CRuntimeException.class, () -> productServiceImpl.getById(1L));
        verify(productRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdate() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        int actualUpdateResult = productServiceImpl.update(productParam);
        verify(productRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualUpdateResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#update(ProductParam)}
     */
    @Test
    void testUpdate2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryImage("Category Image");
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setCreatedAt(mock(Timestamp.class));
        categoryEntity2.setId(1L);
        categoryEntity2.setStatus(1);
        categoryEntity2.setUpdatedAt(mock(Timestamp.class));
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getName()).thenReturn("Name");
        when(productEntity.getCategoryEntity()).thenReturn(categoryEntity2);
        doNothing().when(productEntity).setCreatedAt(Mockito.any());
        doNothing().when(productEntity).setId(Mockito.<Long>any());
        doNothing().when(productEntity).setStatus(Mockito.<Integer>any());
        doNothing().when(productEntity).setUpdatedAt(Mockito.any());
        doNothing().when(productEntity).setCategoryEntity(Mockito.any());
        doNothing().when(productEntity).setName(Mockito.any());
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        int actualUpdateResult = productServiceImpl.update(productParam);
        verify(productRepository).findById(Mockito.<Long>any());
        verify(productEntity).setCreatedAt(Mockito.any());
        verify(productEntity).setId(Mockito.<Long>any());
        verify(productEntity).setStatus(Mockito.<Integer>any());
        verify(productEntity).setUpdatedAt(Mockito.any());
        verify(productEntity).getCategoryEntity();
        verify(productEntity).getName();
        verify(productEntity).setCategoryEntity(Mockito.any());
        verify(productEntity).setName(Mockito.any());
        assertEquals(1, actualUpdateResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#update(ProductParam)}
     */
    @Test
    void testUpdate3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryImage("Category Image");
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setCreatedAt(mock(Timestamp.class));
        categoryEntity2.setId(1L);
        categoryEntity2.setStatus(1);
        categoryEntity2.setUpdatedAt(mock(Timestamp.class));
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getName()).thenReturn("foo");
        when(productEntity.getCategoryEntity()).thenReturn(categoryEntity2);
        doNothing().when(productEntity).setCreatedAt(Mockito.any());
        doNothing().when(productEntity).setId(Mockito.<Long>any());
        doNothing().when(productEntity).setStatus(Mockito.<Integer>any());
        doNothing().when(productEntity).setUpdatedAt(Mockito.any());
        doNothing().when(productEntity).setCategoryEntity(Mockito.any());
        doNothing().when(productEntity).setName(Mockito.any());
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        int actualUpdateResult = productServiceImpl.update(productParam);
        verify(productRepository).findById(Mockito.<Long>any());
        verify(productEntity).setCreatedAt(Mockito.any());
        verify(productEntity).setId(Mockito.<Long>any());
        verify(productEntity).setStatus(Mockito.<Integer>any());
        verify(productEntity).setUpdatedAt(Mockito.any());
        verify(productEntity).getCategoryEntity();
        verify(productEntity).getName();
        verify(productEntity).setCategoryEntity(Mockito.any());
        verify(productEntity, atLeast(1)).setName(Mockito.any());
        assertEquals(1, actualUpdateResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#update(ProductParam)}
     */
    @Test
    void testUpdate4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));
        CategoryEntity categoryEntity2 = mock(CategoryEntity.class);
        when(categoryEntity2.getId()).thenReturn(1L);
        doNothing().when(categoryEntity2).setCreatedAt(Mockito.any());
        doNothing().when(categoryEntity2).setId(Mockito.<Long>any());
        doNothing().when(categoryEntity2).setStatus(Mockito.<Integer>any());
        doNothing().when(categoryEntity2).setUpdatedAt(Mockito.any());
        doNothing().when(categoryEntity2).setCategoryImage(Mockito.any());
        doNothing().when(categoryEntity2).setCategoryName(Mockito.any());
        categoryEntity2.setCategoryImage("Category Image");
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setCreatedAt(mock(Timestamp.class));
        categoryEntity2.setId(1L);
        categoryEntity2.setStatus(1);
        categoryEntity2.setUpdatedAt(mock(Timestamp.class));
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getName()).thenReturn("Name");
        when(productEntity.getCategoryEntity()).thenReturn(categoryEntity2);
        doNothing().when(productEntity).setCreatedAt(Mockito.any());
        doNothing().when(productEntity).setId(Mockito.<Long>any());
        doNothing().when(productEntity).setStatus(Mockito.<Integer>any());
        doNothing().when(productEntity).setUpdatedAt(Mockito.any());
        doNothing().when(productEntity).setCategoryEntity(Mockito.any());
        doNothing().when(productEntity).setName(Mockito.any());
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        int actualUpdateResult = productServiceImpl.update(productParam);
        verify(productRepository).findById(Mockito.<Long>any());
        verify(categoryEntity2).getId();
        verify(categoryEntity2).setCreatedAt(Mockito.any());
        verify(productEntity).setCreatedAt(Mockito.any());
        verify(categoryEntity2).setId(Mockito.<Long>any());
        verify(productEntity).setId(Mockito.<Long>any());
        verify(categoryEntity2).setStatus(Mockito.<Integer>any());
        verify(productEntity).setStatus(Mockito.<Integer>any());
        verify(categoryEntity2).setUpdatedAt(Mockito.any());
        verify(productEntity).setUpdatedAt(Mockito.any());
        verify(categoryEntity2).setCategoryImage(Mockito.any());
        verify(categoryEntity2).setCategoryName(Mockito.any());
        verify(productEntity).getCategoryEntity();
        verify(productEntity).getName();
        verify(productEntity).setCategoryEntity(Mockito.any());
        verify(productEntity).setName(Mockito.any());
        assertEquals(1, actualUpdateResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#update(ProductParam)}
     */
    @Test
    void testUpdate5() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryImage("Category Image");
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setCreatedAt(mock(Timestamp.class));
        categoryEntity.setId(1L);
        categoryEntity.setStatus(1);
        categoryEntity.setUpdatedAt(mock(Timestamp.class));
        when(categoryService.getById(Mockito.<Long>any())).thenReturn(categoryEntity);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryImage("Category Image");
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setCreatedAt(mock(Timestamp.class));
        categoryEntity2.setId(1L);
        categoryEntity2.setStatus(1);
        categoryEntity2.setUpdatedAt(mock(Timestamp.class));
        CategoryEntity categoryEntity3 = mock(CategoryEntity.class);
        when(categoryEntity3.getId()).thenReturn(0L);
        doNothing().when(categoryEntity3).setCreatedAt(Mockito.any());
        doNothing().when(categoryEntity3).setId(Mockito.<Long>any());
        doNothing().when(categoryEntity3).setStatus(Mockito.<Integer>any());
        doNothing().when(categoryEntity3).setUpdatedAt(Mockito.any());
        doNothing().when(categoryEntity3).setCategoryImage(Mockito.any());
        doNothing().when(categoryEntity3).setCategoryName(Mockito.any());
        categoryEntity3.setCategoryImage("Category Image");
        categoryEntity3.setCategoryName("Category Name");
        categoryEntity3.setCreatedAt(mock(Timestamp.class));
        categoryEntity3.setId(1L);
        categoryEntity3.setStatus(1);
        categoryEntity3.setUpdatedAt(mock(Timestamp.class));
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getName()).thenReturn("Name");
        when(productEntity.getCategoryEntity()).thenReturn(categoryEntity3);
        doNothing().when(productEntity).setCreatedAt(Mockito.any());
        doNothing().when(productEntity).setId(Mockito.<Long>any());
        doNothing().when(productEntity).setStatus(Mockito.<Integer>any());
        doNothing().when(productEntity).setUpdatedAt(Mockito.any());
        doNothing().when(productEntity).setCategoryEntity(Mockito.any());
        doNothing().when(productEntity).setName(Mockito.any());
        productEntity.setCategoryEntity(categoryEntity2);
        productEntity.setCreatedAt(mock(Timestamp.class));
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setStatus(1);
        productEntity.setUpdatedAt(mock(Timestamp.class));
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        int actualUpdateResult = productServiceImpl.update(productParam);
        verify(productRepository).findById(Mockito.<Long>any());
        verify(categoryEntity3).getId();
        verify(categoryEntity3).setCreatedAt(Mockito.any());
        verify(productEntity).setCreatedAt(Mockito.any());
        verify(categoryEntity3).setId(Mockito.<Long>any());
        verify(productEntity).setId(Mockito.<Long>any());
        verify(categoryEntity3).setStatus(Mockito.<Integer>any());
        verify(productEntity).setStatus(Mockito.<Integer>any());
        verify(categoryEntity3).setUpdatedAt(Mockito.any());
        verify(productEntity).setUpdatedAt(Mockito.any());
        verify(categoryEntity3).setCategoryImage(Mockito.any());
        verify(categoryEntity3).setCategoryName(Mockito.any());
        verify(productEntity).getCategoryEntity();
        verify(productEntity).getName();
        verify(productEntity, atLeast(1)).setCategoryEntity(Mockito.any());
        verify(productEntity).setName(Mockito.any());
        verify(categoryService).getById(Mockito.<Long>any());
        assertEquals(1, actualUpdateResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#update(ProductParam)}
     */
    @Test
    void testUpdate6() {
        Optional<ProductEntity> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        ProductParam productParam = new ProductParam();
        productParam.setCategoryId(1L);
        productParam.setDescription("The characteristics of someone or something");
        productParam.setId(1L);
        productParam.setName("Name");
        assertThrows(CRuntimeException.class, () -> productServiceImpl.update(productParam));
        verify(productRepository).findById(Mockito.<Long>any());
    }
}
