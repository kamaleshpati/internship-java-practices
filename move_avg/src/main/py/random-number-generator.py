import random
import time
# code for giving 100 random number varing from 100-1000

for i in range(0, 100):
	print(random.randint(100,1000), flush = True)
	time.sleep(1)
