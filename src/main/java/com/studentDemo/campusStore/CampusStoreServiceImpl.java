package com.studentDemo.campusStore;

import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.ArrayList;

public class CampusStoreServiceImpl extends CampusStoreServiceGrpc.CampusStoreServiceImplBase {
        private List<CommentMessage> convertToCommentMessages(List<Comment> comments) {
                List<CommentMessage> commentMessages = new ArrayList<>();
                for (Comment comment : comments) {
                        CommentMessage commentMessage = CommentMessage.newBuilder()
                                        .setId(comment.getId())
                                        .setUserId(comment.getUserId())
                                        .setContent(comment.getContent())
                                        .build();
                        commentMessages.add(commentMessage);
                }
                return commentMessages;
        }

        private ProductMessage convertToProductMessage(Product product) {
                ProductMessage productMessage = ProductMessage.newBuilder()
                                .setId(product.getId())
                                .setName(product.getName())
                                .setPrice(product.getPrice())
                                .setImageUrl(product.getImageUrl())
                                .setDescription(product.getDescription())
                                .setCategory(product.getCategory())
                                .addAllComments(convertToCommentMessages(product.getComments()))
                                .build();
                return productMessage;
        }

        @Override
        public void getProductById(getProductRequest request, StreamObserver<getProductResponse> responseObserver) {
                System.out.println("Request received from client:\n" + request);
                Product product = new Product();
                product.setId(request.getId());
                ProductDAOImpl productDAO = new ProductDAOImpl();
                product = productDAO.getProductById(request.getId());
                getProductResponse response = getProductResponse.newBuilder()
                                .setProduct(convertToProductMessage(product))
                                .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }

        @Override
        public void setProduct(setProductRequest request, StreamObserver<setProductResponse> responseObserver) {
                System.out.println("Request received from client:\n" + request);
                Product product = new Product();
                if (request.getProduct().getId() == 0) {
                        product.setId(null);
                } else {
                        product.setId(request.getProduct().getId());
                }
                product.setName(request.getProduct().getName());
                product.setPrice(request.getProduct().getPrice());
                product.setImageUrl(request.getProduct().getImageUrl());
                product.setDescription(request.getProduct().getDescription());
                product.setCategory(request.getProduct().getCategory());
                ProductDAOImpl productDAO = new ProductDAOImpl();
                productDAO.saveProduct(product);
                setProductResponse response = setProductResponse.newBuilder().setId(product.getId()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }

        @Override
        public void getProducts(getProductsRequest request, StreamObserver<getProductsResponse> responseObserver) {
                System.out.println("Request received from client:\n" + request);
                ProductDAOImpl productDAO = new ProductDAOImpl();
                List<Product> products = productDAO.getProducts(request.getPage());
                List<ProductMessage> productMessages = new ArrayList<>();
                for (Product product : products) {
                        ProductMessage productMessage = ProductMessage.newBuilder()
                                        .setId(product.getId())
                                        .setName(product.getName())
                                        .setPrice(product.getPrice())
                                        .setImageUrl(product.getImageUrl())
                                        .setDescription(product.getDescription())
                                        .setCategory(product.getCategory())
                                        .build();
                        productMessages.add(productMessage);
                }
        }

}
