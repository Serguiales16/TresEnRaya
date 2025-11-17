# 🎮 Tres en Raya Android: Juego Completo con Canvas y Vista Personalizada

Este proyecto es una implementación nativa del clásico **Tres en Raya (Tic Tac Toe)** desarrollada en Android usando **Kotlin**, **Canvas** y una **Vista Personalizada**.  
Su objetivo es demostrar conocimientos en:

- Creación de vistas personalizadas con `Canvas`
- Manejo de eventos táctiles
- Comunicación entre `Activity` y `View`
- Gestión de lógica de juego
- Redibujado dinámico en pantalla
- Uso limpio de patrones como “listener interface”
- Añadido en el manifest para que no reinicie la app al girar la pantalla


---

## ✨ Funcionalidades Destacadas


| Funcionalidad | Descripción |
|----------------|-------------|
| 🖼️ **Dibujo con Canvas** | El tablero y las piezas X/O se dibujan manualmente usando `Canvas` y `Paint`. |
| 🎯 **Detección de toques** | El usuario toca una casilla y el juego detecta automáticamente la posición. |
| 🔄 **Turnos automáticos** | El turno alterna entre X y O con indicador visual. |
| 🏆 **Detección de victoria** | Algoritmo completo que revisa filas, columnas y diagonales. |
| 🤝 **Empates incluidos** | Muestra cuando no quedan casillas libres. |
| 📢 **Comunicación con MainActivity** | La vista personalizada avisa al Activity cuando cambia el turno o alguien gana. |
| 🔁 **Reinicio instantáneo** | Un botón reinicia el tablero completo desde la interfaz. |
| 📋 **Estado visible** | Un TextView indica siempre de quién es el turno o el resultado final. |

---

## 🗺️ Flujo y Estructura de la Aplicación

La app está dividida en dos componentes principales:

| Clase | Propósito | Punto de interés |
|--------|-----------|------------------|
| `MainActivity.kt` | Control de interfaz, textos y reinicio | Maneja eventos que vienen del tablero y actualiza la UI. |
| `Tablero.kt` | Lógica completa del juego y dibujo | Implementación de Canvas y detección de toques. |

---

## 🟩 Métodos Principales en MainActivity

### `onCreate()`
Inicializa la UI, configura eventos y conecta la vista `Tablero` con sus listeners.

### `btnReset.setOnClickListener`
- Limpia el tablero
- Restablece el texto *“Turno: X”*

### `listener.juegoTermina(ganador: Int)`
Se llama cuando:
- 1 → gana X  
- 2 → gana O  
- 3 → empate  

Acciones:
- Muestra mensaje Toast  
- Actualiza el estado  
- Reinicia el tablero  

### `listener.cambioTurno(player: Int)`
Actualiza el texto para mostrar qué jugador debe jugar.

---

## 🟥 Métodos Principales en Tablero

### `onDraw(canvas)`
Dibuja:
- Líneas del tablero
- Marco exterior
- Fichas X y O según la matriz `board`

### `drawX()` y `drawO()`
Dibujan la pieza correspondiente dentro de la casilla tocada.

### `onTouchEvent(event)`
Detecta dónde toca el usuario y:
1. Coloca una ficha
2. Comprueba ganador
3. Cambia turno si es necesario
4. Redibuja todo

### `quienGana()`
Devuelve:
- 1 → Gana X  
- 2 → Gana O  
- 3 → Empate  
- 0 → Nadie aún  

Revisa todas las filas, columnas y diagonales.

### `restablecer()`
Reinicia el juego dejando el tablero vacío.

---

## 🟦 Requisitos Técnicos

- Android Studio
- Kotlin
- API mínima recomendada: 24
- No usa librerías externas

---

## 🟪 Instalación

```
git clone https://github.com/usuario/TresEnRayaAndroid.git
```

Abrir el proyecto en **Android Studio** y ejecutar.

---

## 🟫 Licencia

Proyecto libre para aprendizaje y uso personal.



