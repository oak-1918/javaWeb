package com.hzj.service;

import com.hzj.pojo.Book;
import com.hzj.pojo.Page;
import com.hzj.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-02-27 11:34
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"国哥zaishoutianxiawoou","19467",new BigDecimal(9999),1000,10,null));
    }

    @Test
    public void deleteByookId() {
        bookService.deleteByookId(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"国哥hhhh为什么这么帅","19467",new BigDecimal(9999),1000,10,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookService.queryBooks()
        ) {
            System.out.println(book);

        }
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(2, 5);
        System.out.println(page);
    }

    @Test
    public void pageByPrice() {
        Page<Book> page = bookService.pageByPrice(0, 5,0,50);
        System.out.println(page);
    }
}