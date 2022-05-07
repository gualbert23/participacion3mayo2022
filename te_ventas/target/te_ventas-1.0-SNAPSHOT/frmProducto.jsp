<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Punto de venta</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Formulario de Productos</h1>

            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="productos"/>
            </jsp:include>

            <p></p>

            <form action="ProductoControlador" method="POST">
                <input type="hidden" name="id" value="${producto.id}" />
                    <div class="mb-3">
                        <label for="nombre" class="form-label" name="nombre"  >Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${producto.nombre}">
                        <div id="nombre" class="form-text">Escriba el Nombre del producto</div>
                    </div>
                    <div class="mb-3">
                        <label  class="form-label" name="descripcion"  >Descripcion:</label>
                        <input type="text" class="form-control" name="descripcion" value="${producto.descripcion}">
                        <div  class="form-text">Escriba la descripcion del producto</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" name="precio"  >Precio</label>
                        <input type="text" class="form-control"  name="precio" value="${producto.precio}">
                        <div  class="form-text">Escriba el precio del producto</div>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>


