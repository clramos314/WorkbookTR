<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head><title>Resultado del Cálculo</title></head>
<body>
    <h1>Cálculo Exitoso (Struts + EJB)</h1>
    
    <p>Principal: <bean:write name="calculadoraForm" property="principal"/></p>
    <p>Tasa: <bean:write name="calculadoraForm" property="tasa"/></p>
    <p>Años: <bean:write name="calculadoraForm" property="anios"/></p>
    
    <h2>Interés Calculado: <bean:write name="interesCalculado"/></h2>
    
    <html:link page="/index.jsp">Volver al inicio</html:link>
</body>
</html>