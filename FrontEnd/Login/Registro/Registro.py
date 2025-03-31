import flet as ft
import requests
import time
from session import session

def registro_page(on_login):
    
    image = "C:/Users/João Vitor/IdeaProjects/Symple-Door/FrontEnd/Imagens/LoginRegistro.png"
    nome_value = ft.TextField(label="Nome", width=400, height=50, label_style= ft.TextStyle(color="#000000"), border_color="#f5deb3", text_style=ft.TextStyle(color="#000000"))
    senha_value = ft.TextField(label="Senha", width=400, height=50, label_style= ft.TextStyle(color="#000000"), border_color="#f5deb3",  text_style=ft.TextStyle(color="#000000"), password=True)

    mensagem_api = ft.Text("", color="white", font_family="MinhaFonte")

    def enviar(evento):
        
        data = {
            "login": nome_value.value,
            "senha": senha_value.value
        }
      
        try:
            response = requests.post(f"{session.user_data['url']}/auth/registro",json=data)
        
            if response.status_code == 200 or response.status_code == 201:
                mensagem_api.value = "Cadastrado!"
                mensagem_api.color = "green"
                    
            else:
                print(response.json().get("mensagem"))
                mensagem_api.value = response.json().get("mensagem", "Erro desconhecido.")
                mensagem_api.color = "red"

        except requests.exceptions.RequestException as e:
            mensagem_api.value = f"Erro ao enviar dados: {e}"
            mensagem_api.color = "red"

        senha_value.value = ""
        nome_value.value = ""
        senha_value.update()
        nome_value.update()
        mensagem_api.update()

        time.sleep(1)

        mensagem_api.value = ""
        mensagem_api.update()

    return ft.Container(
        content=ft.Column(
            [
                ft.Text(
                    value="Registro", color="#f5deb3", font_family="MinhaFonte", size=80
                ),

                ft.Container(height=40),

                nome_value,

                senha_value,

                ft.CupertinoButton(
                    content=ft.Text("Enviar", color="#f5deb3", font_family="MinhaFonte", size=30), width=400, height=70, 
                    on_click=enviar
                ),
                mensagem_api,
                ft.CupertinoButton(
                    content=ft.Text("Voltar", color="#f5deb3", font_family="MinhaFonte", size=20), width=150, height=55, 
                    on_click=on_login
                ),
            ],
            alignment=ft.MainAxisAlignment.CENTER,
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            spacing=9,
        ),
        width=500,
        height=500,
        padding=20,
        border_radius=ft.border_radius.all(50),
        alignment=ft.alignment.center,
        image_src=image,
        image_fit=ft.ImageFit.COVER,  
        
    )