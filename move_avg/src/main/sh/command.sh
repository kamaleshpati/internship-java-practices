#!/bin/sh
echo Enter the Circularlist size
read input
python3 random-number-generator.py | java -jar ../lib/move_avg-1.0-SNAPSHOT.jar $input