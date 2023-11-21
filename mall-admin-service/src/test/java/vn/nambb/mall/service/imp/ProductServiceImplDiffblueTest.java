package vn.nambb.mall.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.repository.ProductRepository;
import vn.nambb.mall.service.CategoryService;
import vn.nambb.mall.util.Snowflake;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplDiffblueTest {
    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private Snowflake snowflake;

    /**
     * Method under test: {@link ProductServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(productRepository).updateProductEntityById(Mockito.<Long>any());
        int actualDeleteResult = productServiceImpl.delete(1L);
        verify(productRepository).updateProductEntityById(Mockito.<Long>any());
        assertEquals(0, actualDeleteResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new CRuntimeException("An error occurred")).when(productRepository)
                .updateProductEntityById(Mockito.<Long>any());
        assertThrows(CRuntimeException.class, () -> productServiceImpl.delete(1L));
        verify(productRepository).updateProductEntityById(Mockito.<Long>any());
    }
}
