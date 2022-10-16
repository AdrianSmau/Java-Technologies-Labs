import threading

import requests

url = "http://localhost:8080/Lab1-1.0-SNAPSHOT/my-servlet?word=word&size=0"


def call_lab1_servlet():
    try:
        print(requests.get(url, data={"word": "word", "size": 0}, headers={"Accept": "text/plain", "User-Agent": "My Python Script v1.0"}).text)
    except Exception as ex:
        print("Call failed!")


for i in range(1, 10):
    threading.Thread(target=call_lab1_servlet()).start()
