#!/bin/bash

while getopts ":gh:d:" opt; do
  case $opt in
  d)
    DIR=$OPTARG
    ;;
  g)
    DEBUG="-g"
    ;;
  h)
    echo "Usage: ./start.sh [options]" >&2
    echo "Options:"
    echo "  -g         Generate all debugging info" >&2
    echo "  -d [dir]   Specify where to place generated class files (default bin)" >&2
    exit 1
    ;;
  \?)
    echo "Invalid option: -$OPTARG" >&2
    exit 1
    ;;
  esac
done

# Check if compiler and runner are installed
for program in javac java; do
  if ((\
    $(
      $program --version >/dev/null 2>&1
      echo $?
    ) != 0)); then
    echo "$program compiler not found"
    exit 1
  fi
done

# Start the compilation process
javac ${DEBUG:-} -d ${DIR:-bin} $(find . -type f -name "*.java")

# Run the program
java -cp ${DIR:-bin} Main
