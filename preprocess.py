from numpy import *
import csv
inp = open('D:\Mini_Project\dataset1.csv','r')
out = open('D:\Mini_Project\dataset_new.csv','w') 

writer = csv.writer(out)

for row in csv.reader(inp):
	if(row[11] >= "0" and row[12] >= "0"):
		writer.writerow(row)
print(out)
inp.close()
out.close()

