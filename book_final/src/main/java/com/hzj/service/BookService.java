package com.hzj.service;

import com.hzj.pojo.Book;
import com.hzj.pojo.Page;

import java.util.List;

/**
 * @author hzj
 * @create 2022-02-27 11:28
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteByookId(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
