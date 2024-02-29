# Multithreading
Multithreading uses multithreading library from python to efficiently perform tasks that can be subdivided into different categories using multiple cores. There are three tasks performed using increasing number of cores with a graph being plotted of time against the number of cores used. The three tasks include:

### Greyscale Conversion
This task includes converting multiple images (approx. 1000) to greyscale, while with normal coding this would be performed lossing a loop. We can use multithreading to convert more than one image simultaneously. The graph of time taken for conversion with the number of cores is:

![image](https://github.com/DSam327/Multithreading/assets/113661235/a1e49634-e74f-470a-8cb0-4a3cfb71b379)

### Video to Audio
Similar to greyscale conversion, this task converts nearly 25 video to audios using multithreading

![image](https://github.com/DSam327/Multithreading/assets/113661235/403b58b0-6ca8-40f4-a597-6f5aa584c2b9)

### Web Scraping with Java
This task is done in Java, both the web sraping and the multithreading. First a list of URLs are extracted from XML and then the URLs are scrapped to find all emails in the URL and the result is saved in URL. Multithreading can be used to reduce the time taken. Although care must be taken to not give too many requests to the site.

![image](https://github.com/DSam327/Multithreading/assets/113661235/a4127042-b4be-4169-b6c8-8b8ad83b8ac6)




