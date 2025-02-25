/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.31
 * Generated at: 2024-11-17 17:48:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Volando.uy | Iniciar Sesión</title>\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/main.css\">\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            background-color: #f8f9fa;\n");
      out.write("        }\n");
      out.write("        .main-content {\n");
      out.write("            margin: 20px auto;\n");
      out.write("            padding: 20px;\n");
      out.write("            max-width: 600px;\n");
      out.write("            background-color: #ffffff;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n");
      out.write("        }\n");
      out.write("        h1 {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("        button[type=\"submit\"] {\n");
      out.write("            width: 100%;\n");
      out.write("        }\n");
      out.write("        .form-label {\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        .register-link {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 15px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<div class=\"main-content\">\n");
      out.write("	    <h1>Iniciar Sesión</h1>\n");
      out.write("	    <form action=\"login\" method=\"post\">\n");
      out.write("	        <div class=\"mb-3\">\n");
      out.write("	            <label for=\"email\" class=\"form-label\">Email:</label>\n");
      out.write("	            <input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" required>\n");
      out.write("	            <small id=\"emailError\" style=\"color: red; display: none;\">El usuario no existe.</small>\n");
      out.write("	        </div>\n");
      out.write("	        <div class=\"mb-3\">\n");
      out.write("	            <label for=\"password\" class=\"form-label\">Contraseña:</label>\n");
      out.write("	            <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" required>\n");
      out.write("	        </div>\n");
      out.write("	        <button type=\"submit\" class=\"btn btn-primary\">Ingresar</button>\n");
      out.write("	    </form>\n");
      out.write("\n");
      out.write("	    ");
 String error = (String) request.getAttribute("error"); 
      out.write("\n");
      out.write("	    ");
 if (error != null) { 
      out.write("\n");
      out.write("	        <p style=\"color: red;\">");
      out.print( error );
      out.write("</p>\n");
      out.write("	    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("	    <div class=\"register-link\">\n");
      out.write("	        <p>¿No tienes cuenta? <a href=\"registro_user\">Regístrate</a></p>\n");
      out.write("	    </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("    <script>\n");
      out.write("    document.getElementById(\"email\").addEventListener(\"blur\", function () {\n");
      out.write("        var email = this.value;\n");
      out.write("        var emailError = document.getElementById(\"emailError\");\n");
      out.write("        \n");
      out.write("        if (email) {\n");
      out.write("            var xhr = new XMLHttpRequest();\n");
      out.write("            xhr.open(\"GET\", \"VerificarUsuarioServlet?email=\" + encodeURIComponent(email), true);\n");
      out.write("            xhr.onreadystatechange = function () {\n");
      out.write("                if (xhr.readyState === 4 && xhr.status === 200) {\n");
      out.write("                    var response = JSON.parse(xhr.responseText);\n");
      out.write("                    if (!response.existe) {\n");
      out.write("                        emailError.style.display = \"block\";\n");
      out.write("                    } else {\n");
      out.write("                        emailError.style.display = \"none\";\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            };\n");
      out.write("            xhr.send();\n");
      out.write("        } else {\n");
      out.write("            // Si el campo está vacío, oculta el mensaje de error\n");
      out.write("            emailError.style.display = \"none\";\n");
      out.write("        }\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("    \n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
