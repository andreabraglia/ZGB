#!/bin/bash

# This script generates the documentation for the project.
javadoc $(find . -type f -name "*.java" -print0 | tr '\0' ' ') -d docs
