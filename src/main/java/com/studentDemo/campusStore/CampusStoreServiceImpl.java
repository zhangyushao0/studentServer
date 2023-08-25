package com.studentDemo.campusStore;

import io.grpc.stub.StreamObserver;

public class CampusStoreServiceImpl extends campusStoreServiceGrpc.campusStoreServiceImplBase {
        private ProductMessage convertToProductMessage(Product product) {
                ProductMessage productMessage = ProductMessage.newBuilder()
                                .setId(product.getId())
                                .setName(product.getName())
                                .setPrice(product.getPrice())
                                .setImageUrl(product.getImageUrl())
                                .setDescription(product.getDescription())
                                .setCategory(product.getCategory())
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

}
