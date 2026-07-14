## 🐳 Docker

Este proyecto puede compilarse y ejecutarse dentro de un contenedor Docker.

### Requisitos
- Tener [Docker](https://www.docker.com/) instalado.

### Construir la imagen
\`\`\`bash
docker build -t spaceinvaders .
\`\`\`

Esto compila todos los archivos `.java` dentro del contenedor usando `javac`.

### Ejecutar el contenedor (sin interfaz gráfica)
\`\`\`bash
docker run --rm spaceinvaders
\`\`\`

> **Nota:** SpaceInvaders es un juego de escritorio con interfaz gráfica (Swing/AWT).
> Al ejecutarlo así vas a ver un `HeadlessException`, porque el contenedor no tiene
> acceso a un entorno gráfico. Esto confirma que el juego compiló y arrancó
> correctamente hasta el punto de intentar abrir la ventana. Para ver la interfaz
> gráfica de verdad, sigue las instrucciones según tu sistema operativo abajo.

### Ver la ventana del juego (interfaz gráfica)

El contenedor no tiene pantalla propia, así que necesita conectarse a un servidor
gráfico (X11) de tu sistema operativo.

#### En Linux

\`\`\`bash
xhost +local:docker
docker run --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix spaceinvaders
\`\`\`

#### En Windows

1. Instala [VcXsrv](https://sourceforge.net/projects/vcxsrv/) (servidor X para Windows).
2. Ábrelo con **XLaunch** y configura:
   - *Display settings*: `Multiple windows`, Display number: `0`
   - *Session type*: `Start no client`
   - *Extra settings*: marca ✅ **"Disable access control"**
   - Finish (déjalo corriendo en segundo plano; verás su ícono en la bandeja del sistema)
3. Si Windows Firewall pregunta, permite el acceso en redes privadas.
4. Ejecuta el contenedor apuntando al display de Windows:

\`\`\`powershell
docker run --rm -e DISPLAY=host.docker.internal:0.0 spaceinvaders
\`\`\`

Si `host.docker.internal` no resuelve, usa tu IP local (obtenida con `ipconfig`) en su lugar:

\`\`\`powershell
docker run --rm -e DISPLAY=192.168.X.X:0.0 spaceinvaders
\`\`\`

La ventana del juego debería abrirse como una ventana normal de Windows.

#### En macOS

1. Instala [XQuartz](https://www.xquartz.org/).
2. Ábrelo y en sus preferencias, en la pestaña *Security*, marca **"Allow connections from network clients"**. Reinicia XQuartz.
3. En una terminal:

\`\`\`bash
xhost + 127.0.0.1
docker run --rm -e DISPLAY=host.docker.internal:0 spaceinvaders
\`\`\`