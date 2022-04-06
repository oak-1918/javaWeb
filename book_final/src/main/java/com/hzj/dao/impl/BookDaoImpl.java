package com.hzj.dao.impl;

import com.hzj.dao.BookDao;
import com.hzj.pojo.Book;

import java.util.List;

/**
 * @author hzj
 * @create 2022-02-27 11:08
 */
public class BookDaoImpl extends BaseDao implements BookDao {



    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) VALUES(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book where id = ?";

        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";

        return update(sql,book.getName(),book.getAuthor(),book.getPrice(), book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";

        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?, ?";

        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where price between ? and ?  limit ?, ?";

        return queryForList(Book.class, sql,min, max, begin, pageSize);
    }
}
