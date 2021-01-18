John Lei x500: LEI00007
Partner: Khoi Nguyen x500: NGUY3969

For this project we implemented four different hash functions but we used our
first one because that one was the most efficient. The first hash function finds the character value for the whole string
which improves the chances of having that token be at an unique spot in the hash table.
The second function only find the character value of the first two characters in that string,
which we found to be worse than the first one as many words shared the same values when looking at only the first two characters(like "the" and "there").
The third function only works on specific hashtables, so it worked perfectly for keyword.txt. Basically if there is already a token at a spot,
the function will just put the token in a spot that doesn't have a token yet.
The fourth function attempted to put the token in a different spot once the amount of tokens in a given spot reached five tokens. But for some reason the function
actually didn't put all the tokens in the hashtable, resulting in every spot only having a maximum of one token. So this function definitely didn't work.
However, we also had two add
functions as well. One specifically for keywords.txt (being hash3) and one for the rest of the
text files.

We managed to get very minimal null space for the hashtables of each of the text
files but the difference between number of null space and the longest space we found
to be sufficient but not the best ranging with about a difference of six.

Our keywords.txt has 0 null spaces and all of the keys went to their own space.

We included the percent null space in our comments within the code in main for each
run of the text files.

Also txt files need to outside src folder in order for the program to run.