import requests
import flet as ft
from session import session

def listUsuario_page(on_menu):

    image = "C:/Users/João Vitor/IdeaProjects/Symple-Door/FrontEnd/Imagens/LoginRegistro.png"

    headers = {"Authorization": "Bearer " + session.user_data.get("token", "")}
    
    try:
        response = requests.get(f"{session.user_data['url']}/credenciais/todos", headers=headers)
        
        response.raise_for_status()  
        data = response.json()
        
    except requests.exceptions.RequestException as e:
        data = []
        print(f"Erro ao buscar usuários: {e}")

    columns = [
        ft.DataColumn(ft.Text("idCredencial")),
        ft.DataColumn(ft.Text("Nome")),
    ]

    rows = [
        ft.DataRow(
            cells=[
                ft.DataCell(ft.Text(credencial["idCredencial"])),
                ft.DataCell(ft.Text(credencial["nome"])),
            ]
        ) for credencial in data
    ]

    data_table = ft.DataTable(columns=columns, rows=rows, border=ft.border.all(1))

    return ft.Container(
    content=ft.Column(
        [ 
            ft.Text(
                value="Lista de Usuarios", 
                color="#ed8200", 
                font_family="MinhaFonte", 
                size=60
            ),

            ft.Container(height=20),  

       
            ft.Column(
                [data_table], 
                scroll=ft.ScrollMode.ALWAYS, 
                height=300, 
            ),

            ft.Container(height=20),  

            # Botão Voltar
            ft.CupertinoButton(
                content=ft.Text("Voltar", color="#ed8200", font_family="MinhaFonte", size=20),
                width=200,  
                height=60,  
                on_click=on_menu
            ),

        ],
        alignment=ft.MainAxisAlignment.START,  
        horizontal_alignment=ft.CrossAxisAlignment.CENTER,
        spacing=20,  
    ),
    width=500,
    height=700,
    padding=20,
    border_radius=ft.border_radius.all(50),
    alignment=ft.alignment.center,
    image_src=image,
    image_fit=ft.ImageFit.COVER,
)