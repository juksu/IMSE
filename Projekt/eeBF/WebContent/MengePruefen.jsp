 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "DAO.*"%>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*"%>
<%@ page import= "Controller.*" %>

<html>
    <head>
        <style type="text/css">body {background-color: #99FF66;background-image: url(http://www.topglobus.ru/fotoalbum/pozadi/galerie/520.jpg);}</style>
        <style> 
            select {
                width: 40%;
                padding: 16px 20px;
                border: none;
                border-radius: 4px;
                background-color: #CFCFCF;
            }
            .button {
                background-color: #87CEFA;/* Green */
                border: none;
                color: white;
                padding: 16px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                -webkit-transition-duration: 0.4s; /* Safari */
                transition-duration: 0.4s;
                cursor: pointer;
            }
            .button1 {
                background-color: white; 
                color: black; 
                border: 2px solid #000080;
            }

            .button1:hover {
                background-color: #87CEFA;
                color: white;
            }
            input[type=submit] {
                background-color: #87CEFA;
                border: none;
                color: white;
                padding: 8px 15px;
                text-decoration: none;
                cursor: pointer;

            }

            input[type=text] {
                width: 250px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                font-size: 30px;
                background-color: white;

            }
            .inputsearch[type=text]{
                background-image: url('./images/search.png');
                background-position: 10px 10px; 
                background-repeat: no-repeat;
                padding: 12px 20px 12px 40px;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;

            }

            .inputsearch[type=text]:focus {
                width: 100%;
            }
            .zebra {
                list-style: none;
                border-left: 10px solid #FC7574;
                padding: 0;
                font-family: "Lucida Sans";
            }
            .zebra li {
                padding: 10px;
            }
            .zebra li:nth-child(odd) {
                background: #E1F1FF;
            }
            .zebra li:nth-child(even) {
                background: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>The list of Products</title>
    </head>
    <body>
       <a href="userContent.jsp">Back to menu</a>

        <h2>Look for quantity of the product</h2> 
        <br/><br/>

        <form method="post" name="Search" action="MengePruefen">
            <table cellpadding="15" border="0" width="100%" align="center"   background="images/logo.jpg">
                <tr><td colspan=2 style="font-size:12pt;" align="center">
                <tr> <td> <input type="text"  class="input inputsearch" name="keyword" placeholder="Search.."required>
                    </td> </tr>      
                <tr><td colspan=2 align="center">
                        <input type="submit"  class="button button1" value="Search" /></td></tr>
            </table>
        </form>
        <br/><br/>
        
</body>
</html>