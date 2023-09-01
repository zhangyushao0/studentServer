package com.studentDemo.campusStore;

import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.studentDemo.user.UserDAOImpl;
import com.studentDemo.bank.BankAccount;
import com.studentDemo.user.User;
import java.util.ArrayList;
import java.util.List;

public class CampusStoreServiceImplTest {
    private UserDAOImpl userDAO;
    private CampusStoreServiceImpl campusStoreService;
    User testUser;
    Product testProduct;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOImpl();
        testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        testUser.setBankAccount(bankAccount);
        testProduct = new Product();
        testProduct.setName("test");
        testProduct.setPrice(100D);
        testProduct.setImageUrl("test");
        testProduct.setDescription("test");
        testProduct.setCategory("test");
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setUserId(testUser.getId());
        comment.setContent("test");
        comment.setProduct(testProduct);
        comments.add(comment);
        testProduct.setComments(comments);
        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.saveProduct(testProduct);
        CampusStore campusStore = new CampusStore();
        campusStore.sellProduct(testProduct, testUser);
        userDAO.saveUser(testUser);
    }

    @Test
    public void testBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User user = userDAO.getUserById(testUser.getId());
        assert (user.getBankAccount().getBalance() == 1000D);
    }

    @Test
    public void testGetProductById() throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new CampusStoreServiceImpl())
                .build()
                .start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        CampusStoreServiceGrpc.CampusStoreServiceBlockingStub stub = CampusStoreServiceGrpc.newBlockingStub(channel);
        getProductRequest request = getProductRequest.newBuilder()
                .setId(1L)
                .build();
        getProductResponse response = stub.getProductById(request);
        System.out.println("Response received from server:\n" + response);
        assert (response.getProduct().getId() == 1L);
        assert (response.getProduct().getName().equals("test"));
        assert (response.getProduct().getPrice() == 100D);
        assert (response.getProduct().getImageUrl().equals("test"));
        assert (response.getProduct().getDescription().equals("test"));
        assert (response.getProduct().getCategory().equals("test"));
        assert (response.getProduct().getComments(0).getId() == 1L);
        assert (response.getProduct().getComments(0).getUserId() == 1L);
        assert (response.getProduct().getComments(0).getContent().equals("test"));
        channel.shutdown();
    }

    @Test
    public void testMakeComment() throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new CampusStoreServiceImpl())
                .build()
                .start();
        System.out.println("Server started at " + server.getPort());
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        CampusStoreServiceGrpc.CampusStoreServiceBlockingStub stub = CampusStoreServiceGrpc.newBlockingStub(channel);
        makeCommentRequest request = makeCommentRequest.newBuilder()
                .setUserId(testUser.getId())
                .setProductId(testProduct.getId())
                .setContent("test")
                .build();
        makeCommentResponse response = stub.makeComment(request);
        System.out.println("Response received from server:\n" + response);
        assert (response.getSuccess() == true);
        channel.shutdown();
    }
}
