package com.hzj.web; /**
 * @author hzj
 * @create 2022-03-02 19:55
 */

import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import com.google.gson.Gson;
import com.hzj.pojo.Book;
import com.hzj.pojo.Cart;
import com.hzj.pojo.CartItem;
import com.hzj.service.BookService;
import com.hzj.service.impl.BookServiceImpl;
import com.hzj.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    /**
     * 加入购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        // 重定向回原来商品所在的地址页面

        request.getSession().setAttribute("lastItemName", cartItem.getName());
        response.sendRedirect(request.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        // 最后一个添加的商品名称
        request.getSession().setAttribute("lastItemName", cartItem.getName());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
     /**
     * 删除商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
           cart.deleteItem(id);
        }
        response.sendRedirect(request.getHeader("Referer"));


    }
    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));


    }
    /**
     * 修改商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"),2);

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id, count);
        }
        response.sendRedirect(request.getHeader("Referer"));

    }


}
