#!/bin/bash

FILE=$1
PATTERN="Insert"
TIMESTAMPSTART="to_timestamp("
TIMESTAMPSTARTEND=",'DD-MON-RR HH.MI.SSXFF AM')"
REPLACETIMESTAMP=""
#NEWLINE="\n"
DASH="-"
Month=(JAN FEB MAR APR MAY JUN JUL AGU SEP OCT NOV DEC)
while IFS= read -r var
do
#grep -q foo <<<$string; then
if  grep -q $PATTERN <<< $var;
then
while [[ $var != *\)\; ]]
do
read line
var=$var$line
done

if  grep -q $TIMESTAMPSTART <<< $var;
then
#var="$var" | replace "$TIMESTAMP" "$REPLACETIMESTAMP"
var=${var//$TIMESTAMPSTART/$REPLACETIMESTAMP}
var=${var//$TIMESTAMPSTARTEND/$REPLACETIMESTAMP}
#var=${var//$NEWLINE/$REPLACETIMESTAMP}
for (( i = 0 ; i < ${#Month[@]} ; i=$i+1 ));
do
Mon="${Month[${i}]}"

if  grep -q ${Month[${i}]} <<< $var;
then
 var=${var//${Month[${i}]}/$(($i+1))}
fi
done
fi
  echo "$var" >>  $HOME/Desktop/script/output/output.sql
fi
done < "$FILE"
