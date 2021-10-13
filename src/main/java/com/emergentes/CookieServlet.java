
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        boolean nuevaVisita=true;
        //OBTENER EL ARREGLO DE COOKIES
        Cookie[]cookies=request.getCookies();
        //Verificar si tiene elementos
       if(cookies!=null)
       {
           for(Cookie c:cookies){
               if((c.getName().equals("visitante")) && c.getValue().equals("SI")){
                   nuevaVisita=false;
                   break;
               }
             }
       }
       if(nuevaVisita){
           //crear ccokie
           Cookie ck=new Cookie("visitante", "SI");
           // tiempo y comentario
           ck.setMaxAge(120);
           ck.setComment("Control de nuevos visitantes");
           //poner al navegador
           response.addCookie(ck);
           mensaje = "Gracias por visitar nuestra página";
       }
       else{
           mensaje= "Estamos agradecidos por tenerlo nuevamente";
       }
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out=response.getWriter();
       out.println("<h1>"+mensaje+"</h1>");
       out.println("<a href='index.jsp'>Ir al inicio</a>");
    }

}
