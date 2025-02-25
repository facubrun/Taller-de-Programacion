#!/usr/bin/env bash
#
# EstacionTrabajo compiling utility
# Usage: compile.sh [--all]
#
# 31-10-2024

usage() {
  echo "
    Usage: compile.sh [--all]
             -a o --all:  Compile all components
    " 1>&2
}


parse_arg() {

  # Set default values
  all_flag=FALSE

  # Parse options
  for arg in "$@"; do
    shift
    case "$arg" in
      "--help" | "-h" ) usage; exit 0 ;;
      "--all"  | "-a" ) all_flag=TRUE ;;
      "--"*    | "-"* )  echo  "\n""Illegal option ${arg}" 1>&2; usage; exit 1;;
      *               )  set -- "$@" "$arg" ;;
    esac
  done

  shiftcount=$(( OPTIND - 1 ))
  shift $shiftcount

}

compileEstacionTrabajo() {
  cd EstacionTrabajo
  mvn clean install
  cd ..
  cp EstacionTrabajo/target/estacion-trabajo-1.0-SNAPSHOT.jar servidor.jar
  java -jar servidor.jar
}


compileWeb() {
    cd SitioWeb
    mvn clean install
    cd target
    mv SitioWeb-web.war ../../apache-tomcat-10.1.31/webapps
}

compileMovil() {
    cd SitioMobile
    mvn clean install
    cd target
    mv SitioMobile-mobile.war ../../apache-tomcat-10.1.31/webapps
}

finalizar() {
  mkdir -p ~/.volandoUy
  cp EstacionTrabajo/config.properties ~/.volandoUy/
  echo ...
}

tomcat() {
  cd apache-tomcat-10.1.31/
  ./bin/startup.sh
}

printMenu() {
    echo "----------------------------------------------"
    echo "-      Compilador Estacion de Trabajo        -"
    echo "----------------------------------------------"
    echo "- Selecciona una opcion                      -"
    echo "- 1. Compilar Todos los Projectos            -"
    echo "- 2. Compilar EstacionTrabajo                -"
    echo "- 3. Compiilar Componente Web                -"
    echo "- 4. Compilar Componente Mobile              -"
    echo "- 5. Generar properties                      -"
    echo "- 6. Iniciar Tomcar                          -"
    echo "- 7. Salir                                   -"
    echo "----------------------------------------------"
    read -p "Input Selection: " input
    if [ "$input" = "1" ]; then
        compileEstacionTrabajo
        compileWeb
        compileMovil
        finalizar
    elif [ "$input" = "2" ]; then
	finalizar
	compileEstacionTrabajo
    elif [ "$input" = "3" ]; then
        compileWeb
    elif [ "$input" = "4" ]; then
        compileMovil
    elif [ "$input" = "5" ]; then
	    finalizar
    elif [ "$input" = "5" ]; then
            tomcat
    elif [ "$input" = "7" ]; then
        exit
    else
        clear
        printMenu
    fi
}

# Copy settings.xml to ~/.m2/
mv settings.xml ~/.m2/

# Parse arguments and execute based on flags
parse_arg "$@"
if [ "$all_flag" = TRUE ]; then
  compileEstacionTrabajo
  compileWeb
  compileMovil
  finalizar
else
  printMenu
fi


#mvn install:install-file -Dfile=Tarea1Def9.jar -DgroupId=com.example -DartifactId=tarea1def9 -Dversion=1.0 -Dpackaging=jar
