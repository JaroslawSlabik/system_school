<%-- 
    Document   : index2
    Created on : 2018-06-03, 17:31:32
    Author     : Jarek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uczelnia system</title>
        
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

        <div class="container">

            <header>
                <h1>Uczelnia system</h1>
            </header>
  
            <nav>
                <ul>
                    <li><a href="index.jsp">Strona główna</a></li>
                    <li><a href="StudentServlet?logic_name=show_all_students">Studenci</a></li>
                    <li><a href="StudentServlet?logic_name=show_all_groups">Grupy</a></li>
                    <li><a href="StudentServlet?logic_name=show_all_specjalizations">Specjalizacje</a></li>
                </ul>
            </nav>

            <article class="main_article">
                <%= request.getAttribute("view") != null ? request.getAttribute("view") : ""
                        + "<h1>Opis systemu</h1>"
                        + "<p>System został przygotowany na zlecenie uczelni. Główną funkcjonalność tego systemu jest zarządzanie studentami, grupami oraz specjalizacjami. "
                        + "Główną technologią wykonania jest JAVA (JSP oraz servlet). Logika została oparta o jeden servlet i wiele 'Logik' powiązane wzorcem "
                        + "projektowym o nazwie 'Strategia' (ang. Strategy). Jako baza danych jest wykorzystany MySQL </p>"
                        + "" %>
            </article>

            <footer>Copyright &copy; Jarosław Słabik 7376 Z610</footer>

        </div>

    </body>
</html>
