# GenesisGym

## Grupo
### Integrante
* 49186 - Clemente Alvarez, Federico

### Repositorio
* https://github.com/federicoclementealvarez/GenesisGym


## Tema
### Descripción
*Aplicación Web de un gimnasio. La misma cuenta con cuatro niveles de acceso (tipos de usuario): cliente estándar y profesor. Cada cliente cuenta con un profesor, y también con un plan (con su debido precio mensual), que incluye ciertas actividades. Cada actividad, a su vez, puede incluir ciertos tipos de ejercicios con los que el cliente podrá crear rutinas o, en contraste, puede contar con clases (prestadas por un profesor) a las que el cliente se podrá anotar para asistir. Los profesores cuentan con un plan específico de precio $0 mensual, que les permite realizar cualquier actividad, y también pueden tener (o no) asignado a otro profesor.*


### Modelo
![image](https://github.com/federicoclementealvarez/GenesisGym/blob/main/readMeImages/Modelo%20de%20dominio%20Genesis%20Gym.png)

*Link*: https://drive.google.com/file/d/1GgyPlLdbYP7tpO-X-rwJm4kdSw9BMhCg/view?usp=sharing


### Alcance Funcional 

|Requerimientos regularidad|Detalle|
|:-|:-|
|ABMC simple|1. Crear una rutina (no es independiente pero su CRUD es muy básico)|
|ABMC dependiente|1. Crear un usuario (signUp de People)|
|CU NO-ABMC|1. Agregar ejercicios a una rutina: se crea un nuevo ejercicio para una rutina de un tipo de ejercicio específico según los que estén disponibles para el usuario (aquellos que sean de actividades incluidas en su plan). Los mismos se pueden borrar posteriormente|
|Listado simple|1. Listar todos los ejercicios de una rutina seleccionada por el usuario (en el orden en el que fueron creadas)<br>2. Listar los datos del usuario, junto a los datos de nombre y contacto de su profesor (si tuviera)<br>3. Listar el plan del cliente, en conjunto con las actividades que incluye y su precio por mes|
|Listado complejo|-|
