#!/bin/sh
echo "Running shell script"

while read LINE; do
    ls | grep -E -i 'a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p'
   echo ${LINE} + "modyfikacja"

   # test z uzyciem pipe
#   ls | grep -i 'sam'
done