FROM eclipse-temurin:21-jdk

# Necesario porque el proyecto tiene nombres de archivo con tildes y ñ (Menú.java, MúsicaYSonido, etc.)
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /app

# Copiamos el código fuente (incluye .java y los recursos: imágenes, sonidos, partidas guardadas)
COPY src ./src

# Compilamos todos los .java dentro del contenedor
RUN find src -name "*.java" > sources.txt \
    && javac -encoding UTF-8 -d out @sources.txt

# El juego usa Swing/AWT (interfaz gráfica), por eso el classpath incluye
# tanto las clases compiladas (out) como los recursos (src)
CMD ["java", "-cp", "out:src", "Main"]