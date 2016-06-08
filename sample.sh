#!/bin/sh
echo "Processing Tika > WCRFT > Liner2 > ?Fextor?"

while read LINE; do
    echo ${LINE}
    var=${LINE}
    if [ -f $var ]; then

	txtData=$(java -jar /data/tika/tika-app.jar -t $var)

	wcrftData=$(echo $txtData | wcrft-app nkjp_e2 -i txt -o ccl -)

  	echo $wcrftData | liner2 pipe -i ccl -o ccl -t /data/fextor/liner2Out.xml -ini /data/liner/liner2_modele/liner2-models-fat-pack/config-nam.ini

	python -c 'import fextorbis; \
                print fextorbis.process("/data/fextor/liner2Out.xml" , \
                {"features":"base orth interp_signs person_last_nam \
                person_first_nam road_nam country_nam city_nam verb12 \
                bigrams trigrams"}, "/data/fextor/fextorOut.csv")'

#	if [ -f "$lOut/liner2Out.xml" ]; then
#		echo "istnieje"
#	fi
        echo "$(cat /data/fextor/fextorOut.csv)"
fi
done
