package com.hzj.web; /**
 * @author hzj
 * @create 2022-03-01 17:02
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


public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    /**
     * 处理前端分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAFE_SIZE);
        //2 调用BookService.page(pageNo,pageSize):Page对象

        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3、保存page对象到Request域中
        request.setAttribute("page", page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

    /**
     * 首页价格搜索功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAFE_SIZE);
        int max = WebUtils.parseInt(request.getParameter("max"), 1000);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        //2 调用BookService.page(pageNo,pageSize):Page对象

        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }
//        page.setUrl("client/bookServlet?action=pageByPrice&min="+request.getParameter("min")+"&max="+request.getParameter("max"));
        //3、保存page对象到Request域中
        page.setUrl(sb.toString());
        request.setAttribute("page", page);
        //4、请求转发到pages/client/index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
}
