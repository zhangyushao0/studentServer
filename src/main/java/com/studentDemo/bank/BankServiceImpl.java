package com.studentDemo.bank;

import com.studentDemo.user.User;
import com.studentDemo.user.UserDAOImpl;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class BankServiceImpl extends BankServiceGrpc.BankServiceImplBase {
    @Override
    public void transfer(TransferRequest request, StreamObserver<TransferResponse> responseObserver) {
        Long fromUserId = request.getFromUserId();
        Long toUserId = request.getToUserId();
        Double amount = request.getAmount();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User fromUser = userDAOImpl.getUserById(fromUserId);
        if (fromUser.getBankAccount().getPassword() != request.getPassword()) {
            TransferResponse response = TransferResponse.newBuilder().setSuccess(false).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        User toUser = userDAOImpl.getUserById(toUserId);
        Bank bank = new Bank();
        boolean result = bank.transferMoney(fromUser, toUser, amount);
        TransferResponse response = TransferResponse.newBuilder()
                .setTransactionId(fromUser.getBankAccount().getTransactions()
                        .get(fromUser.getBankAccount().getTransactions().size() - 1).getTransactionId())
                .setSuccess(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void cancelTransfer(CancelTransferRequest request, StreamObserver<CancelTransferResponse> responseObserver) {
        Long transactionId = request.getTransactionId();
        Long fromUserId = request.getFromUserId();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User fromUser = userDAOImpl.getUserById(fromUserId);
        BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
        Transaction transaction = bankAccountDAOImpl.getTransactionById(transactionId);
        Bank bank = new Bank();
        boolean result = bank.cancelTransferMoney(fromUser, transaction);
        CancelTransferResponse response = CancelTransferResponse.newBuilder().setSuccess(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getTransactionsById(GetTransactionsByIdRequest request,
            StreamObserver<GetTransactionsByIdResponse> responseObserver) {
        Long userId = request.getUserId();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User user = userDAOImpl.getUserById(userId);
        List<Transaction> transactions = user.getBankAccount().getTransactions();
        GetTransactionsByIdResponse.Builder builder = GetTransactionsByIdResponse.newBuilder();
        for (Transaction transaction : transactions) {
            builder.addTransactions(TransactionMessage.newBuilder().setTransactionId(transaction.getTransactionId())
                    .setAmount(transaction.getAmount()).setCounterUserId(transaction.getCounterUserId())
                    .setTransactionType(transaction.getTransactionType()).build());
        }
        GetTransactionsByIdResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTransactions(GetAllTransactionsRequest request,
            StreamObserver<GetAllTransactionsResponse> responseObserver) {
        BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
        List<Transaction> transactions = bankAccountDAOImpl.getAllTransactions();
        GetAllTransactionsResponse.Builder builder = GetAllTransactionsResponse.newBuilder();
        for (Transaction transaction : transactions) {
            builder.addTransactions(TransactionMessage.newBuilder().setTransactionId(transaction.getTransactionId())
                    .setAmount(transaction.getAmount()).setCounterUserId(transaction.getCounterUserId())
                    .setTransactionType(transaction.getTransactionType()).build());
        }
        GetAllTransactionsResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
