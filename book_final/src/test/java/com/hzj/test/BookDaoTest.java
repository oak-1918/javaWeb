package com.hzj.test;

import com.hzj.dao.BookDao;
import com.hzj.dao.impl.BookDaoImpl;
import com.hzj.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-02-27 11:20
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"国哥为什么这么帅","19467",new BigDecimal(9999),1000,10,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(22,"杰哥为什么这么帅","1940",new BigDecimal(9999),1000,10,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookDao.queryBooks()
             ) {
            System.out.println(book);

        }

    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount(0,50));
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 4);
        for (Book book:books
             ) {
            System.out.println(book.toString());
        }
    }

    public int quick(int[] arr, int lo, int hi){
        int i = lo , j = hi;
        int temp = arr[lo];
        while(i < j){
            while(i < hi && arr[i] <= temp){
                i++;
            }
            while(j > lo &&arr[j] >= temp){
                j--;
            }
            if( i < j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }

        }
        if(lo < j){
            arr[lo] = arr[j];
            arr[j] = temp;
        }
        return j;
    }
    public void quickSort(int[] arr, int lo,  int hi){
        if(lo >= hi){
            return;
        }
        int mid = quick(arr,lo, hi);
        quickSort(arr, lo, mid - 1);
        quickSort(arr,mid + 1, hi);
    }
    @Test
    public void testQueryForPageItems() {
        int[] arr = new int[]{5,1,0,156,456,20,5,65,-5,10,4,5,12,6,4};

//        4,1,0,-5,4,5,5,12,6,10     -5,1,0,4,4,
//
//        5,1,0,-5,10,4,5,12,6,4
//        5,1,0,-5,10,4,5,12,6,4
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");

        }

//        List<Book> books = bookDao.queryForPageItems(0, 4, 0,50);
//        for (Book book:books
//        ) {
//            System.out.println(book.toString());
//        }
    }
}