package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class formularioUsuarios_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Usuarios</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"resources/css/formularios.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"nave\">\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "navegacion.jsp", out, false);
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div id=\"container\">\n");
      out.write("            <h1>&bull; Usuarios &bull;</h1>\n");
      out.write("            <div class=\"underline\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"icon_wrapper\">\n");
      out.write("                <i class=\"fa fa-users\" aria-hidden=\"true\"></i>\n");
      out.write("            </div>\n");
      out.write("            <form action=\"#\" method=\"post\" id=\"contact_form\">\n");
      out.write("                <div class=\"id\">\n");
      out.write("                    <label for=\"id\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"Id\" name=\"id\" id=\"id_input\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"name\">\n");
      out.write("                    <label for=\"name\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"Nombre(s)\" name=\"name\" id=\"name_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"lastName\">\n");
      out.write("                    <label for=\"lastName\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"Apellidos\" name=\"lastName\" id=\"lastName_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"rol\">\n");
      out.write("                    <label for=\"rol\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"Rol\" name=\"rol\" id=\"rol_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"telephone\">\n");
      out.write("                    <label for=\"telephone\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"Telefono\" name=\"telephone\" id=\"telephone_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"email\">\n");
      out.write("                    <label for=\"email\"></label>\n");
      out.write("                    <input type=\"email\" placeholder=\"Email\" name=\"email\" id=\"email_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"password\">\n");
      out.write("                    <label for=\"password\"></label>\n");
      out.write("                    <input type=\"password\" placeholder=\"Contraseña\" name=\"password\" id=\"password_input\" required>\n");
      out.write("                </div>\n");
      out.write("                <!--<div class=\"subject\">\n");
      out.write("                    <label for=\"subject\"></label>\n");
      out.write("                    <select placeholder=\"Subject line\" name=\"subject\" id=\"subject_input\" required>\n");
      out.write("                        <option disabled hidden selected>Subject line</option>\n");
      out.write("                        <option>I'd like to start a project</option>\n");
      out.write("                        <option>I'd like to ask a question</option>\n");
      out.write("                        <option>I'd like to make a proposal</option>\n");
      out.write("                    </select>\n");
      out.write("                </di>-->\n");
      out.write("                <div class=\"dateC\">\n");
      out.write("                    <label for=\"dateC\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"fecha de creacion\" name=\"dateC\" id=\"dateC_input\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"userC\">\n");
      out.write("                    <label for=\"userC\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"usuario Creador\" name=\"userC\" id=\"userC_input\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"dateU\">\n");
      out.write("                    <label for=\"dateU\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"fecha de ultima modificación\" name=\"dateU\" id=\"dateU_input\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"userU\">\n");
      out.write("                    <label for=\"userU\"></label>\n");
      out.write("                    <input type=\"text\" placeholder=\"usuario de ultima modificación\" name=\"userU\" id=\"userU_input\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"submit\">\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Alta\" id=\"alta_button\" />\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Baja\" id=\"baja_button\" />\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Modificar\" id=\"modific_button\" />\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Consultar\" id=\"consultar_button\" />\n");
      out.write("                </div>\n");
      out.write("            </form><!-- // End form -->\n");
      out.write("        </div><!-- // End #container -->\n");
      out.write("    </body>\n");
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
