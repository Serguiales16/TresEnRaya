🟪 Tres en Raya (Android – Kotlin + Canvas)

Este proyecto es un juego de Tres en Raya (Tic Tac Toe) hecho en Android utilizando Kotlin y una vista personalizada (Tablero) que dibuja todo con Canvas.

El juego permite:

Dibujar el tablero y las fichas.

Detectar toques en la pantalla.

Cambiar de turno entre X y O.

Detectar ganador o empate.

Mostrar mensajes en pantalla.

Resetear la partida automáticamente.

🟦 Estructura del Proyecto

El proyecto tiene dos partes principales:

✔ MainActivity

Controla la interfaz y recibe las notificaciones del tablero (turnos, ganador, empate).

✔ Tablero (View personalizada)

Se encarga de dibujar el juego, detectar toques y manejar la lógica interna del Tres en Raya.

🟩 MainActivity – Explicación de métodos
### onCreate()

Carga el layout con ViewBinding.

Prepara los bordes y márgenes de la pantalla.

Obtiene la vista personalizada Tablero.

Gestiona el botón Reiniciar.

Recibe eventos del tablero mediante listener.

### btnReset.setOnClickListener

Reinicia el tablero llamando a restablecer().

Actualiza el texto del estado a "Turno: X".

### listener de Tablero

El Activity escucha dos cosas:

juegoTermina(ganador: Int)

Se llama cuando:

Gana X → devuelve 1

Gana O → devuelve 2

Empate → devuelve 3

El método:

Muestra un Toast

Cambia el texto del estado

Reinicia el tablero

cambioTurno(player: Int)

Se llama cada vez que cambia el turno.

Si player == 1 → Turno de X

Si player == 2 → Turno de O

Actualiza el TextView con el turno.

🟥 Tablero – Explicación de métodos

La clase Tablero es una vista personalizada que dibuja el juego y controlará toda la lógica interna.

🎨 Dibujo
### onDraw(canvas: Canvas)

Dibuja:

Líneas del tablero

Marco exterior

Las fichas (X y O) en cada casilla

Depende del contenido de la matriz board.

✖ / ⭕ Dibujar fichas
drawX()

Dibuja una X usando dos líneas diagonales.

drawO()

Dibuja un círculo perfecto en el centro de la casilla.

🟧 Interacción del jugador
### onTouchEvent(event: MotionEvent)

Detecta cuando el usuario toca el tablero.

Hace esto:

Calcula en qué casilla tocó el usuario.

Si la casilla está vacía → coloca X o O.

Comprueba si hay ganador con quienGana().

Si alguien gana o hay empate → avisa al Activity con listener.juegoTermina().

Si no hay ganador → alterna el turno.

Llama a invalidate() para redibujar.

🟨 Lógica del juego
### quienGana(): Int

Devuelve:

1 → Gana X

2 → Gana O

3 → Empate

0 → Aún no gana nadie

Comprueba:

Las 3 filas

Las 3 columnas

La diagonal principal

La diagonal inversa

Si no hay huecos y nadie ganó → empate

🔄 Reiniciar partida
### restablecer()

Limpia la matriz del tablero a 0.

Pone el turno de nuevo en 1 (X).

Llama a invalidate() para redibujar vacío.

🟫 Variables importantes
### board

Matriz 3×3 que guarda el estado del juego:

0 → vacío

1 → X

2 → O

### jugadorActual

Controla de quién es el turno:

1 → X

2 → O

### listener

Permite que el Tablero le “hable” a la Activity.

🟪 Resumen Principal

Este proyecto muestra cómo hacer un Tres en Raya funcional con:

Canvas para dibujar

Toques para jugar

Eventos para comunicar el resultado

ViewBinding para manejar la interfaz

Lógica completa del juego
