# Bootcamp - Simulador de Creditos (Reto #01)

Repositorio
https://github.com/syslet/bootcamp-credit-simulation.git


Camper: Rene Plaz Cabrera



EndPoints de Objeto Parametros
-----------------------------------------------
```
[GET]: http://localhost:8080/parametro/tarjetas

Response:
[
    {
        "familia": "Tarjetas",
        "producto": "Clasica",
        "id": 1
    },
    {
        "familia": "Tarjetas",
        "producto": "Oro",
        "id": 2
    },
    {
        "familia": "Tarjetas",
        "producto": "Black",
        "id": 3
    }
]

[GET]: http://localhost:8080/parametro/cuotas
[GET]: http://localhost:8080/parametro/diaspago
[GET]: http://localhost:8080/parametro/tea
[GET]: http://localhost:8080/parametro/listar

```

EndPoints de Objeto Persona
-----------------------------------------------
```
[GET]: http://localhost:8080/persona/listar

Response:
[
    {
        "dni": "10602933",
        "nombre": "Jim",
        "apellido": "Morrison",
        "correo": "jim.morrison@gmail.com",
        "id": 1
    },
    {
        "dni": "30102931",
        "nombre": "Marco",
        "apellido": "Vito",
        "correo": "Marco.Vito@gmail.com",
        "id": 2
    },
    {
        "dni": "30622937",
        "nombre": "Pedro",
        "apellido": "Pecaldri",
        "correo": "Pedro.Pecaldri@gmail.com",
        "id": 3
    },
    {
        "dni": "70007630",
        "nombre": "Maria",
        "apellido": "Rambosi",
        "correo": "Maria.Rambosi@gmail.com",
        "id": 4
    },
    {
        "dni": "50605936",
        "nombre": "Camilo",
        "apellido": "Trason",
        "correo": "Camilo.Trason@gmail.com",
        "id": 5
    }
]
```

EndPoints de Simulación de Crédito
-----------------------------------------------
```
[POST]: http://localhost:8080/simula-credito

Request:
{
    "dni": "10602933",
    "tarjeta": "CLASICA",
    "moneda": "S/",
    "monto": 5662,
    "cuota": 13,
    "tea": "99.90%",
    "diaPago": "20"
}

Response:
{
    "cuota": "729.0",
    "moneda": "S/",
    "primeraCuota": "22/03/2021",
    "estado": "EXITOSO"
}

```


Carga de Parámetros
-----------------------------------------------
Tabla: [parametro]
```
INSERT INTO parametro(familia, producto) VALUES ('Tarjetas', 'Clasica');
INSERT INTO parametro(familia, producto) VALUES ('Tarjetas', 'Oro');
INSERT INTO parametro(familia, producto) VALUES ('Tarjetas', 'Black');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '1');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '2');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '3');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '4');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '5');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '6');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '7');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '8');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '9');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '10');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '11');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '12');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '13');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '14');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '15');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '16');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '17');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '18');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '19');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '20');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '21');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '22');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '23');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '24');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '25');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '26');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '27');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '28');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '29');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '30');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '31');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '32');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '33');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '34');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '35');
INSERT INTO parametro(familia, producto) VALUES ('Cuotas', '36');
INSERT INTO parametro(familia, producto) VALUES ('DiasPago', '5');
INSERT INTO parametro(familia, producto) VALUES ('DiasPago', '20');
INSERT INTO parametro(familia, producto) VALUES ('TEA', '99.90%');
INSERT INTO parametro(familia, producto) VALUES ('TEA', '95.90%');
INSERT INTO parametro(familia, producto) VALUES ('TEA', '90.90%');
```

Tabla: [persona]
```
INSERT INTO persona(dni, nombre, apellido, correo) VALUES ('10602933', 'Jim', 'Morrison', 'jim.morrison@gmail.com');
INSERT INTO persona(dni, nombre, apellido, correo) VALUES ('30102931', 'Marco', 'Vito', 'Marco.Vito@gmail.com');
INSERT INTO persona(dni, nombre, apellido, correo) VALUES ('30622937', 'Pedro', 'Pecaldri', 'Pedro.Pecaldri@gmail.com');
INSERT INTO persona(dni, nombre, apellido, correo) VALUES ('70007630', 'Maria', 'Rambosi', 'Maria.Rambosi@gmail.com');
INSERT INTO persona(dni, nombre, apellido, correo) VALUES ('50605936', 'Camilo', 'Trason', 'Camilo.Trason@gmail.com');
```