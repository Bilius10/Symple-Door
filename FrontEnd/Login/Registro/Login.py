import flet as ft
import requests
import time

def login_page(on_registro):

    image = "C:/Users/João Vitor/IdeaProjects/CarteiraAcao/FrontEnd/Imagens/FundoLoginRegistro.png"
    nome_value = ft.TextField(label="Nome", width=400, height=50, label_style= ft.TextStyle(color="#000000"), border_color="#f7931a", text_style=ft.TextStyle(color="#000000"))
    senha_value = ft.TextField(label="Senha", width=400, height=50, label_style= ft.TextStyle(color="#000000"), border_color="#f7931a",  text_style=ft.TextStyle(color="#000000"), password=True)

    mensagem_api = ft.Text("", color="white", font_family="MinhaFonte")

    def enviar(evento):
        
        data = {
            "login": nome_value.value,
            "senha": senha_value.value
        }
      
        try:
            response = requests.post("http://localhost:8080/auth/login",json=data)
                
            if response.status_code == 200 or response.status_code == 201:
                mensagem_api.value = "Seja Bem vindo! "+response.json().get('login') 
                mensagem_api.color = "green"
                    
            else:
                    
                mensagem_api.value = response.json().get("Mensagem", "Erro desconhecido.")
                mensagem_api.color = "red"

        except requests.exceptions.RequestException as e:
            mensagem_api.value = f"Erro ao enviar dados: {e}"
            mensagem_api.color = "red"

        senha_value.value = ""
        nome_value.value = ""
        senha_value.update()
        nome_value.update()

        time.sleep(1)

        mensagem_api.value = ""
        mensagem_api.update()

    return ft.Container(
        content=ft.Column(
            [
                ft.Text(
                    value="Login", color="#ed8200", font_family="MinhaFonte", size=80
                ),

                ft.Container(height=40),

                nome_value,

                senha_value,

                ft.CupertinoButton(
                    content=ft.Text("Enviar", color="#ed8200", font_family="MinhaFonte", size=30), width=400, height=70, 
                    on_click=enviar
                ),
                mensagem_api,
                ft.CupertinoButton(
                    content=ft.Text("Registrar", color="#ed8200", font_family="MinhaFonte", size=20), width=150, height=55, 
                    on_click=on_registro
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
