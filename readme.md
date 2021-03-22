**Simple Recommender /w Java and Apache Map-reduce (Apache Mahout)**

Program will generate 5 (based on the treshold) recommended items from MovieLens database and value expressing the strength of the preference for the recommended item 

Program uses smallest MovieLens dataset from GroupLens, a research lab in the Department of Computer Science and Engineering at the University of Minnesota. 

It uses 100,000 ratings and 3,600 tag applications applied to 9,000 movies by 610 users. Last updated 9/2018. 
Can be downloaded from **(make sure to remove the timestamp value from dataset first)**:
https://files.grouplens.org/datasets/movielens/ml-latest-small.zip

The treshold can be altered, from -1.0 to 1.0 (perfect similarity), according to documentation.
 
0.0 will give you 5 value 99,99% of time, which is close to best value you can get.

You can also change number of items to recommend. 

**Usage:**
1. Download dataset (link above) or fixed ratings.csv file from repository
2. Change the directory of _ratings2.csv_ to your chosen folder
3. Decide on the directory of recommendation.txt file which will be the output file
3. Remove the timestamp value from _ratings.csv_ 
4. Decide on treshold value from -1.0 to 1.0
5. Decide on number of items
6. run