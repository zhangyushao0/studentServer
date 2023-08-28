// package com.studentDemo.campusStore;

// import io.grpc.stub.StreamObserver;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.ArrayList;
// import java.util.List;

// import static org.mockito.Mockito.*;

// public class CampusStoreServiceImplTest {
// @Mock
// private ProductDAOImpl productDAO;

// @Mock
// private StreamObserver<getProductResponse> getProductResponseObserver;

// @Mock
// private StreamObserver<setProductResponse> setProductResponseObserver;

// private CampusStoreServiceImpl campusStoreService;

// @BeforeEach
// public void setUp() {
// MockitoAnnotations.initMocks(this);
// campusStoreService = new CampusStoreServiceImpl(productDAO);
// }

// @Test
// public void testGetProductById() {
// // Arrange
// int productId = 1;
// Product product = new Product();
// product.setId(productId);
// product.setName("Product 1");
// product.setPrice(10.0);
// product.setImageUrl("http://example.com/product1.jpg");
// product.setDescription("Description for Product 1");
// product.setCategory("Category 1");
// List<Comment> comments = new ArrayList<>();
// Comment comment1 = new Comment();
// comment1.setId(1);
// comment1.setUserId(1);
// comment1.setContent("Comment 1 for Product 1");
// comments.add(comment1);
// Comment comment2 = new Comment();
// comment2.setId(2);
// comment2.setUserId(2);
// comment2.setContent("Comment 2 for Product 1");
// comments.add(comment2);
// product.setComments(comments);

// getProductRequest request =
// getProductRequest.newBuilder().setId(productId).build();

// // Act
// when(productDAO.getProductById(productId)).thenReturn(product);
// campusStoreService.getProductById(request, getProductResponseObserver);

// // Assert
// verify(getProductResponseObserver,
// times(1)).onNext(any(getProductResponse.class));
// verify(getProductResponseObserver, times(1)).onCompleted();
// }

// @Test
// public void testSetProduct() {
// // Arrange
// Product product = new Product();
// product.setId(1);
// product.setName("Product 1");
// product.setPrice(10.0);
// product.setImageUrl("http://example.com/product1.jpg");
// product.setDescription("Description for Product 1");
// product.setCategory("Category 1");

// setProductRequest request =
// setProductRequest.newBuilder().setProduct(convertToProductMessage(product)).build();

// // Act
// campusStoreService.setProduct(request, setProductResponseObserver);

// // Assert
// verify(setProductResponseObserver,
// times(1)).onNext(any(setProductResponse.class));
// verify(setProductResponseObserver, times(1)).onCompleted();
// }
// }