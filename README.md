CODE2040 API Challenge
======================
Doing this challenge was interesting; I learnt new things that I probably would not have as of today. In each of the stages ofthe challenge, I can boast of having learnt a thing or two.

**In general, I tried to implement some design principles such as`SOLID principle` and `DRY principle`.**

**Here are the things I learnt for each stage of this challenge;**

Registration
------------
This was very easy to `curl` in the commandline. However, converting the `curl` to its Ruby equivalent wasn't an easy feat. But I researched and found the `HTTParty gem`, which I studied and was able to implement. 

Stage I
-------
After I made the `get_string` method, I made multiple calls to it and that caused some errors because each call to `get_string` returned a different string. When the first string that was returned by the first call is reversed, a newly returned string from the most recent call is posted as the result -in place of the reversed string-, which always was wrong.

I figured that out and fixed my code accordingly.

Stage II
--------
I learnt new array manipulation functions in Ruby.

Stage III
---------
Believe it or not, I've always ran into some confusions when dealing with methods that take in arguments. With this challenge I took the bold step because that's what its all about and now every iota of confusion is cleared. Also, I learnt some cool string manipulation functions in Ruby.

Stage IV
--------
Just like it said: "Dates are hard! Don't feel badly if you're scratching your head on this one...". This was the most challenging, no kidding!

Before I solved the problem, I researched and learned about iso8601 and UNIX timestamps. And here is a summary of how I solved it; **ISO -> UNIX -> add Interval -> ISO**


