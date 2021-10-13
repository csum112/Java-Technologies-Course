import requests

ENDPOINT = "http://localhost:8080/lab1_war_exploded/homework"
headers = {'user-agent': 'python'}
r = requests.get(ENDPOINT, headers=headers)
print(r.content)

