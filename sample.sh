#!/bin/sh
echo "Running shell script"

while read LINE; do
    inputFile=${LINE}
    result=`pdftotext $inputFile`
    dir_name=`dirname $inputFile`

    dirView=`ls $dir_name`
    processedFile="$dir_name/testPDF.txt"
    if [ -f "$processedFile" ]; then
        echo `cat $processedFile`
    fi

    echo "wynik = $dirView"
    #echo "taki plik NIENIENIE istnieje :("
   # test z uzyciem pipe
#   ls | grep -i 'sam'
done