package com.example.springdatadtoexercise.service.impl;

import com.example.springdatadtoexercise.repository.OrderRepository;
import com.example.springdatadtoexercise.service.OrderService;
import com.example.springdatadtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


}
