# CursoJava-GestionLibreria

Diseñar un sistema que gestione préstamos de libros con restricciones adicionales.

• Crea una clase Libro con atributos id, titulo, autor, anioPublicacion y disponible (booleano), códigoPrestamista (será el
código de la persona que tiene el libro en el caso de que este esté prestado), fechaPrestamo, fechaDevolución será
una fecha calculada de 15 días a contar desde la fechaPrestamo).

• Crea la clase usuario que recogerá código (que debe ser único), nombre, array de tres libros que es el máximo de
libros que debe de tener un usuario en préstamo y un atributo multado o no. Cuando un usuario entrega un libro
posterior a la fecha de préstamo deberá permanecer multado, Establece los
parámetros que consideres apropiados para llevar a cabo esta restricción.

• Todos los libros comienzan como disponibles cuando se crean, implementa métodos para prestar que además calcule
el resto de parámetros que dependen de su préstamo y para devolver libros.

• En el main permite Agregar libros a nuestra particular biblioteca que tendrá una capacidad máxima de 100 libros, los
libros deberán almacenarse en la primera posición vacía, eliminar libros, prestar libros siempre que el usuario no esté
multado y no tenga más de tres libros prestados actualmente. Para realizar una gestión eficiente deberemos de tener
igualmente un array de usuarios de nuestra biblioteca en este caso nuestra biblioteca es muy exclusiva y solo tendrá
cabida para 10 usuarios.

Genera pruebas en el main que permitan comprobar todo lo que establece el ejercicio.
