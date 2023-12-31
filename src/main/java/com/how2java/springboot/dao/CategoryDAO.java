package com.how2java.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.springboot.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{
//JpaRepository相当于帮我写了daoimpl
}
