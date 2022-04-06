package com.hzj.dao;

import com.hzj.pojo.Book;

import java.util.List;

/**
 * @author hzj
 * @create 2022-02-27 11:03
 */
public interface BookDao {
     /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

     /**
     * 通过Id来删除图书
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

     /**
     * 修改图书
     * @param book
     * @return
     */
    public int updateBook(Book book);

     /**
     * 通过Id来查询图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询所有的图书总数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询价格在min~max之间的所有图书总数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCount(int min, int max);

    /**
     * 查询当前页面的图书
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize);
    List<Book> queryForPageItems(int begin, int pageSize, int min, int max);
}
