package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <title>Login</title>\n");
      out.write("  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>\n");
      out.write("      <link rel=\"stylesheet\" href=\"resources/css/style.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("  <div class=\"login-wrap\">\n");
      out.write("    <div class=\"login-html\">\n");
      out.write("      <input id=\"tab-1\" type=\"radio\" name=\"tab\" class=\"sign-in\" checked><label for=\"tab-1\" class=\"tab\">Login</label>\n");
      out.write("      <div class=\"login-form\">\n");
      out.write("          <form class=\"sign-in-htm\" action=\"loginUsuario\" method=\"POST\">\n");
      out.write("\t  <div class=\"group\">\n");
      out.write("\t    <label for=\"email\" class=\"label\">Email</label>\n");
      out.write("            <input id=\"emailSI\" name=\"emailSI\" type=\"text\" class=\"input\" required>\n");
      out.write("\t  </div>\n");
      out.write("\t  <div class=\"group\">\n");
      out.write("\t    <label for=\"pass\" class=\"label\">Cotraseña</label>\n");
      out.write("            <input id=\"passSI\" name=\"passSI\" type=\"password\" class=\"input\" data-type=\"password\" required    >\n");
      out.write("\t  </div>\n");
      out.write("\t  <div class=\"group\">\n");
      out.write("\t    <input type=\"submit\" class=\"button\" value=\"Entra\">\n");
      out.write("\t  </div>\n");
      out.write("\t  <div class=\"hr\"></div>\n");
      out.write("\t</form>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
