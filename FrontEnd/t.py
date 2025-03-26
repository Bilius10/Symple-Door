import requests

data = {
    "senha": "adm"
}
respota = requests.post("http://localhost:8080/credenciais/entrar", data=data)
print(respota.text)