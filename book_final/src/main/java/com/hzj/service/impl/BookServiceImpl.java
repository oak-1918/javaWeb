package com.hzj.service.impl;

import com.hzj.dao.BookDao;
import com.hzj.dao.impl.BookDaoImpl;
import com.hzj.pojo.Book;
import com.hzj.pojo.Page;
import com.hzj.service.BookService;

import java.util.List;

/**
 * @author hzj
 * @create 2022-02-27 11:31
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteByookId(Integer id) {
        bookDao.deleteBookById(id);
    }


    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin = ( page.getPageNo() - 1) * pageSize;
        List<Book> items =  bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryForPageTotalCount(min, max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin = ( page.getPageNo() - 1) * pageSize;
        List<Book> items =  bookDao.queryForPageItems(begin, pageSize,min, max);
        page.setItems(items);
        return page;

    }
}
