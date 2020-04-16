package com.yil3.service;

import com.yil3.pojo.Books;

import java.util.List;

public interface BookService {
    int addBook(Books books);

    int deleteBookById(int id);

    int updateBook(Books books);

    Books queryBookById(int id);

    List<Books> queryBookAll();
}
