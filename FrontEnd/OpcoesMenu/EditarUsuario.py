import requests
import flet as ft
from session import session
import time

def editarUsuario_page(on_menu):
    
    image = "C:/Users/João Vitor/IdeaProjects/Symple-Door/FrontEnd/Imagens/LoginRegistro.png"

  
    id_value = ft.TextField(
        label="ID", 
        width=400, height=50, 
        label_style=ft.TextStyle(color="#000000"), 
        border_color="#f5deb3", 
        text_style=ft.TextStyle(color="#000000"), 
    )
    
    nome_value = ft.TextField(
        label="Nome", 
        width=400, height=50, 
        label_style=ft.TextStyle(color="#000000"), 
        border_color="#f5deb3", 
        text_style=ft.TextStyle(color="#000000")
    )
    
    senha_value = ft.TextField(
        label="Senha", 
        width=400, height=50, 
        label_style=ft.TextStyle(color="#000000"), 
        border_color="#f5deb3",  
        text_style=ft.TextStyle(color="#000000"), 
        password=True
    )


    mensagem_api = ft.Text("", color="white", font_family="MinhaFonte")

    
    def deletar(evento):
        headers = {"Authorization": "Bearer " + session.user_data.get("token", "")}

        

        try:
            response = requests.delete(f"{session.user_data['url']}/credenciais/{id_value.value}/{nome_value.value}", headers=headers)
            
            if response.status_code == 200 or response.status_code == 201:
                mensagem_api.value = response.json().get('nome') + " deletado"
                mensagem_api.color = "green"
                    
            else:
                    
                mensagem_api.value = response.json().get("mensagem", "Erro desconhecido.")
                mensagem_api.color = "red"

        except requests.exceptions.RequestException as e:
            mensagem_api.value = f"Erro ao enviar dados: {e}"
            mensagem_api.color = "red"

        id_value.value = ""
        senha_value.value = ""
        nome_value.value = ""
        
        id_value.update()
        nome_value.update()
        senha_value.update()
        mensagem_api.update()

        time.sleep(1)

        mensagem_api.value = ""
        mensagem_api.update()

    def editar(evento):
        
        headers = {"Authorization": "Bearer " + session.user_data.get("token", "")}

        data = {
            "idCredencial": id_value.value,
            "nome": nome_value.value,
            "senha": senha_value.value
        }

        try:
            response = requests.put(f"{session.user_data['url']}/credenciais", headers=headers, json=data)
            
            if response.status_code == 200 or response.status_code == 201:
                mensagem_api.value = response.json().get('nome') + " atualizado"
                mensagem_api.color = "green"
                    
            else:
                    
                mensagem_api.value = response.json().get("mensagem", "Erro desconhecido.")
                mensagem_api.color = "red"

        except requests.exceptions.RequestException as e:
            mensagem_api.value = f"Erro ao enviar dados: {e}"
            mensagem_api.color = "red"

        id_value.value = ""
        senha_value.value = ""
        nome_value.value = ""
        
        id_value.update()
        nome_value.update()
        senha_value.update()
        mensagem_api.update()

        time.sleep(1)

        mensagem_api.value = ""
        mensagem_api.update()


    return ft.Container(
        content=ft.Column(
            [
           
                ft.Text(
                    value="Editar Usuario", 
                    color="#f5deb3", 
                    font_family="MinhaFonte", 
                    size=60
                ),

                ft.Container(height=20), 

       
                id_value,
                nome_value,
                senha_value,

                ft.Container(height=20),  

              
                ft.Row(
                    [
                        ft.CupertinoButton(
                            content=ft.Text("Deletar", color="#f5deb3", font_family="MinhaFonte", size=30),
                            width=180, height=70,  
                            on_click=deletar
                        ),
                        ft.CupertinoButton(
                            content=ft.Text("Editar", color="#f5deb3", font_family="MinhaFonte", size=30),
                            width=180, height=70,  
                            on_click=editar
                        ),
                    ],
                    alignment=ft.MainAxisAlignment.SPACE_BETWEEN, 
                    spacing=20  
                ),

                ft.Container(height=20), 
       
                mensagem_api,

                ft.CupertinoButton(
                    content=ft.Text("Voltar", color="#ed8200", font_family="MinhaFonte", size=20), width=150, height=55, 
                    on_click=on_menu
                 ),

            ],
            alignment=ft.MainAxisAlignment.CENTER,
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            spacing=15,  
        ),
        width=500,
        height=750,  
        padding=20,
        border_radius=ft.border_radius.all(50),
        alignment=ft.alignment.center,
        image_src=image,
        image_fit=ft.ImageFit.COVER,
    )
