package com.hzj.web; /**
 * @author hzj
 * @create 2022-03-03 11:34
 */

import com.hzj.pojo.Cart;
import com.hzj.pojo.User;
import com.hzj.service.OrderService;
import com.hzj.service.impl.OrderServiceImpl;
import com.hzj.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User loginUser = (User) request.getSession().getAttribute("user");
        if(loginUser == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }

        Integer userId = loginUser.getId();
        String orderId = null;

        orderId = orderService.createOrder(cart, userId);

        request.getSession().setAttribute("orderId", orderId);

        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");



    }


}
