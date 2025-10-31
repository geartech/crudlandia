// Package declaration
package com.geartech.app.services.product.impl;

// Import necessary dependencies
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.inject.Inject;


class ProductServiceImpl implements ProductService {
  // Inject required services and repositories via @Autowired annotation
  private final ProductRepository repository;
private final ProductMapper mapper;
