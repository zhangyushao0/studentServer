package com.studentDemo.campusStore;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CampusStoreServiceImplTest {
    Server server;
    ManagedChannel channel;
    CampusStoreServiceGrpc.CampusStoreServiceBlockingStub blockingStub;

    @BeforeEach
    public void setUp() throws Exception {
        server = ServerBuilder.forPort(8080).addService(new CampusStoreServiceImpl()).build();
        server.start();
        channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        blockingStub = CampusStoreServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.shutdown();
        channel.shutdown();
    }

    @Test
    public void testGetProductById() {
        LocalDateTime date = LocalDateTime.now();
        int id = setProduct(date);
        getProductRequest request = getProductRequest.newBuilder().setId(id).build();
        getProductResponse response = blockingStub.getProductById(request);
        ProductMessage product = response.getProduct();
        assertEquals(product.getId(), id);
        assertEquals(product.getName(), "test");
        assertEquals(product.getPrice(), 100.0, 0.01);
        assertEquals(product.getImageUrl(), "test");
        assertEquals(product.getDescription(), "test");
        assertEquals(product.getCommentsCount(), 0);
    }

    @Test
    public void testSetProduct() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setContent("test");
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        ProductMessage product = ProductMessage.newBuilder().setName("test").setPrice(100.0)
                .setImageUrl("test").setDescription("test").setCategory("test").build();
        setProductRequest request = setProductRequest.newBuilder().setProduct(product).build();
        setProductResponse response = blockingStub.setProduct(request);
        assertEquals(response.getSuccess(), true);
    }

    public int setProduct(LocalDateTime date) {
        ProductMessage product = ProductMessage.newBuilder().setName("test").setPrice(100.0)
                .setImageUrl("test").setDescription("test").setCategory("test" + date).build();
        setProductRequest request = setProductRequest.newBuilder().setProduct(product).build();
        setProductResponse response = blockingStub.setProduct(request);
        assertEquals(response.getSuccess(), true);
        return ((int) response.getId());
    }

    @Test
    public void testGetProducts() {
        LocalDateTime date = LocalDateTime.now();
        int id1 = setProduct(date);
        int id2 = setProduct(date);
        getProductsRequest request = getProductsRequest.newBuilder().setCategory("test" + date).setPage(1).build();
        getProductsResponse response = blockingStub.getProducts(request);
        List<ProductMessage> products = response.getProductsList();
        assertEquals(products.size(), 2);
        assertEquals(products.get(0).getId(), id1);
        assertEquals(products.get(1).getId(), id2);
    }
}