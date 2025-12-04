<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <title>Calculadora de Intereses EJB</title>
</head>
<body>
    <h1>Calculadora de Intereses (Struts + EJB)</h1>
    
    <html:errors/>
    
    <html:form action="/calcular.do" method="POST">
    
        <table border="0" cellpadding="5">
            <tr>
                <td>Principal (€):</td>
                <td><html:text property="principal" size="20"/></td>
                </tr>
            <tr>
                <td>Tasa de Interés (ej. 0.05 para 5%):</td>
                <td><html:text property="tasa" size="20"/></td>
            </tr>
            <tr>
                <td>Años:</td>
                <td><html:text property="anios" size="20"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <html:submit value="Calcular Interés"/>
                </td>
            </tr>
        </table>
        
    </html:form>
    
    <p style="margin-top: 20px;">
        * Esta aplicación usa Struts 1.2 como capa de presentación para invocar un EJB 3.1 Session Bean.
    </p>

</body>
</html>