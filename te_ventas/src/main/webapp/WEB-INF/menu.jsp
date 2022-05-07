
<%
    String opcion = request.getParameter("opcion");
%>

<nav class="nav nav-pills flex-column flex-sm-row">
    <a class="flex-sm-fill text-sm-center nav-link <%=(opcion.equals("productos")?"active":"")%>" aria-current="page" href="ProductoControlador">Productos</a>
    <a class="flex-sm-fill text-sm-center nav-link <%=(opcion.equals("clientes")?"active":"")%>" href="ClienteControlador">Clientes</a>
    <a class="flex-sm-fill text-sm-center nav-link <%=(opcion.equals("ventas")?"active":"")%>" href="VentaControlador">Ventas</a>

</nav>