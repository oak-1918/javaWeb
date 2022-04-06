package com.hzj.web; /**
 * @author hzj
 * @create 2022-02-27 11:42
 */

import com.hzj.pojo.Book;
import com.hzj.pojo.Page;
import com.hzj.service.BookService;
import com.hzj.service.impl.BookServiceImpl;
import com.hzj.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo++;
        //1、获取请求的参数==封装成为 Book 对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2 调用 BookService.addBook()保存图书
        bookService.addBook(book);
        //3、跳到图书列表页面
//        request.getRequestDispatcher("/pages/manager/bookServlet?action=list").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 1、获取请求的参数 id，图书编程
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        // 2、调用 bookService.deleteBookById();删除图书
            bookService.deleteByookId(id);
            // 3、重定向回图书列表管理页面
            // /book/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求的参数 id，图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        // 2、调用 bookService.queryBookById();查询图书
        Book book = bookService.queryBookById(id);
        //3、保存到图书到 Request 域中
        request.setAttribute("book",book);
        // 4 请求转发到。pages/manager/book_edit.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+ request.getParameter("pageNo"));
    }


    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到 Request 域中
        request.setAttribute("books",books);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("enter booksevlet?action=page");
        //1 获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAFE_SIZE);
        //2 调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到Request域中
        request.setAttribute("page", page);

        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

}
