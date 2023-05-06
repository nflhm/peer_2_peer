package com.project.mini.peer2peertransaction.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mini.peer2peertransaction.model.Transaction;
import com.project.mini.peer2peertransaction.repository.TransactionRepository;
import com.project.mini.peer2peertransaction.request.ProductRequest;
import com.project.mini.peer2peertransaction.request.UserRequest;
import com.project.mini.peer2peertransaction.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
public class TransactionService {

//    @Value("${api.url.product}") productApiUrl;
    private final int dayInYear = 365;

    private final String apiProduct = "http://localhost:8887/";
    private final String apiUser = "http://localhost:8889/";
    @Autowired
    TransactionRepository transactionRepository;

    public void insert(Transaction transaction) {
        borrowLoan(transaction);
        transactionRepository.save(transaction);
    }

    public void update(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public Transaction findTheTransaction(Integer integer) {
        return transactionRepository.findTransactionByTranId(integer);
    }

    public List<Transaction> allTransactions() {
        return transactionRepository.findAllByOrderByBorrowerId();
    }

    @Transactional
    public void borrowLoan (Transaction transactionRequest){
        try {
            UserRequest borrower = getUserDetail(apiUser,"user/{user_id}",transactionRequest.getBorrowerId());
            ProductRequest product = getProductDetail(apiProduct, "product/{product_id}", transactionRequest.getProductId());
            UserRequest lender = getUserDetail(apiUser,"user/{user_id}",product.getLenderId());
            if (borrower != null && lender !=null && lender.getBalance() > product.getAmount()){
                lender.setBalance(lender.getBalance()-product.getAmount());
                borrower.setBalance(borrower.getBalance()+product.getAmount());
                transactionRequest.setAmount(Double.parseDouble(product.getAmount().toString()));
                updateUserDetail(apiUser, "/user/update", borrower);
                updateUserDetail(apiUser, "/user/update", lender);
            }
        } catch (Exception e){
            System.out.println( e.getStackTrace());
        }
    }

    @Transactional
    public void payLoanWithInterest (int borrowerId, int tranId){
        try {
            Transaction trans = transactionRepository.findTransactionByTranId(tranId);
            ProductRequest product = getProductDetail(apiProduct, "product/{product_id}", trans.getProductId());
            long daysBetween = Duration.between(trans.getTranDate().atStartOfDay(), trans.getDueDate().atStartOfDay()).toDays();
            Float interest = product.getInterest()*daysBetween/dayInYear*product.getAmount();
            Float totalLend = interest + product.getAmount();
            trans.setAmount(Double.parseDouble(totalLend.toString()));
            UserRequest borrower = getUserDetail(apiUser,"user/{user_id}",borrowerId);
            UserRequest lender = getUserDetail(apiUser,"user/{user_id}",product.getLenderId());
            if (borrower != null && lender !=null && borrower.getBalance() >= trans.getAmount()){
                borrower.setBalance(borrower.getBalance()-trans.getAmount());
                lender.setBalance(lender.getBalance()+trans.getAmount());
                trans.setPaymentStatus(true);
                product.setDeleteStatus(true);
                updateUserDetail(apiUser, "/user/update", borrower);
                updateUserDetail(apiUser, "/user/update", lender);
            }
        } catch (Exception e){
            System.out.println( e.getStackTrace());
        }
    }

    public ProductRequest getProductDetail(String url, String endpoint, int productId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("username", "password");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url+endpoint,
                HttpMethod.GET,
                entity,
                String.class, productId);

        ObjectMapper mapper = new ObjectMapper();
        MessageResponse messageResponse = mapper.readValue(response.getBody(), MessageResponse.class);
        ProductRequest product = mapper.convertValue(messageResponse.getData(), ProductRequest.class);
        return product;
    }

    public UserRequest getUserDetail(String url, String endpoint, int userId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("username", "password");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url+endpoint,
                HttpMethod.GET,
                entity,
                String.class, userId);

        ObjectMapper mapper = new ObjectMapper();
        MessageResponse messageResponse = mapper.readValue(response.getBody(), MessageResponse.class);
        UserRequest user = mapper.convertValue(messageResponse.getData(), UserRequest.class);
        return user;
    }

    public void updateUserDetail(String url, String endpoint, UserRequest user) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(user.getName(), user.getPassword());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(user, headers);
        System.out.println("sending request to "+ url+endpoint + ", with data: " + user);

        ResponseEntity<String> response = restTemplate.exchange(
                url+endpoint,
                HttpMethod.PUT,
                requestEntity,
                String.class);
    }
}
